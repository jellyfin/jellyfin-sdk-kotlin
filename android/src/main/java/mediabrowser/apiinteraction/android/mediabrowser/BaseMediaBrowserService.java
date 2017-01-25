package mediabrowser.apiinteraction.android.mediabrowser;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.MediaMetadata;
import android.media.browse.MediaBrowser;
import android.media.session.MediaSession;
import android.media.session.PlaybackState;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.service.media.MediaBrowserService;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import mediabrowser.apiinteraction.Response;
import mediabrowser.apiinteraction.android.GsonJsonSerializer;
import mediabrowser.apiinteraction.android.VolleyHttpClient;
import mediabrowser.apiinteraction.android.mediabrowser.utils.CarHelper;
import mediabrowser.apiinteraction.android.mediabrowser.utils.PackageValidator;
import mediabrowser.apiinteraction.android.mediabrowser.utils.QueueHelper;
import mediabrowser.model.dto.BaseItemDto;
import mediabrowser.model.dto.MediaSourceInfo;
import mediabrowser.model.logging.ILogger;
import mediabrowser.model.serialization.IJsonSerializer;
import mediabrowser.model.session.ClientCapabilities;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Luke on 7/4/2015.
 */
@TargetApi(21)
public abstract class BaseMediaBrowserService extends MediaBrowserService implements IPlaybackCallback {

    protected PackageValidator mPackageValidator;
    protected MediaProvider mMediaProvider;
    protected ILogger logger;
    protected IJsonSerializer jsonSerializer = new GsonJsonSerializer();
    protected MediaSession mSession;
    protected IPlayback mPlayback;
    protected IMediaRes mediaRes;

    // Indicates whether the service was started.
    private boolean mServiceStarted;
    private DelayedStopHandler mDelayedStopHandler = new DelayedStopHandler(this);

    // Delay stopSelf by using a handler.
    private static final int STOP_DELAY = 30000;

    // "Now playing" queue:
    protected List<MediaSession.QueueItem> mPlayingQueue;
    protected int mCurrentIndexOnQueue;

    protected abstract ILogger createLogger();
    protected abstract IPlayback createPlayback();
    protected abstract IMediaRes createMediaRes();
    public abstract Class getServiceClass();
    public abstract Class getAudioPlayerActivityClass();
    protected abstract VolleyHttpClient getHttpClient();

    //protected MediaNotificationManager mMediaNotificationManager;

    private Bitmap currentBitmap = null;

    @Override
    public void onCreate() {

        super.onCreate();

        IntentFilter filter = new IntentFilter();
        filter.setPriority(Integer.MAX_VALUE);
        filter.addAction(Constants.ACTION_PLAYPAUSE);
        filter.addAction(Constants.ACTION_NEXT);
        filter.addAction(Constants.ACTION_PREVIOUS);
        filter.addAction(Constants.ACTION_PLAY);
        filter.addAction(Constants.ACTION_PAUSE);
        filter.addAction(Constants.ACTION_UNPAUSE);
        filter.addAction(Constants.ACTION_STOP);
        filter.addAction(Constants.ACTION_SEEK);
        filter.addAction(Intent.ACTION_HEADSET_PLUG);
        filter.addAction(AudioManager.ACTION_AUDIO_BECOMING_NOISY);
        filter.addAction(Constants.SLEEP_INTENT);
        filter.addAction(Constants.INCOMING_CALL_INTENT);
        filter.addAction(Constants.CALL_ENDED_INTENT);

        logger = createLogger();
        mediaRes = createMediaRes();

        mPlayingQueue = new ArrayList<MediaSession.QueueItem>();
        mMediaProvider = new MediaProvider(logger);
        mPackageValidator = new PackageValidator(this, logger);

        // Start a new MediaSession
        mSession = new MediaSession(this, "MediaService");
        setSessionToken(mSession.getSessionToken());
        mSession.setCallback(new MediaSessionCallback(logger));
        mSession.setFlags(MediaSession.FLAG_HANDLES_MEDIA_BUTTONS | MediaSession.FLAG_HANDLES_TRANSPORT_CONTROLS);

        mPlayback = createPlayback();
        mPlayback.setState(PlaybackState.STATE_NONE);
        mPlayback.setCallback(this);

        /*Context context = getApplicationContext();
        Intent intent = new Intent(context, MusicPlayerActivity.class);
        PendingIntent pi = PendingIntent.getActivity(context, 99 *//*request code*//*,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        mSession.setSessionActivity(pi);*/

        Bundle extras = new Bundle();
        CarHelper.setSlotReservationFlags(extras, true, true, true);
        mSession.setExtras(extras);

        updatePlaybackState(null);

        //mMediaNotificationManager = new MediaNotificationManager(this, this, mediaRes);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (intent != null) {
            handleIntent(this, intent);
        }

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        logger.Debug("MediaService.onDestroy");

        // Service is being killed, so make sure we release our resources
        handleStopRequest(null, false);

        mDelayedStopHandler.removeCallbacksAndMessages(null);

        if (mSession != null){
            mSession.release();
        }
    }

    private void handleIntent(Context context, Intent intent) {

        String action = intent.getAction();
        if (action == null){
            return;
        }

        logger.Debug("MediaService.handleIntent action=%s", action);

        if (action.equalsIgnoreCase(Constants.ACTION_PREVIOUS)) {
            handlePreviousTrackRequest();
        } else if (action.equalsIgnoreCase(Constants.ACTION_NEXT)) {
            handleNextTrackRequest();
        }

            /*
             * Incoming Call : Pause if VLC is playing audio or video.
             */
        if (action.equalsIgnoreCase(Constants.INCOMING_CALL_INTENT)) {
            handlePauseRequest();
        }

            /*
             * Call ended : Play only if VLC was playing.
             */
        if (action.equalsIgnoreCase(Constants.CALL_ENDED_INTENT) && mPlayback.getState() == PlaybackState.STATE_PAUSED) {
            handleUnpauseRequest();
        }

        // skip all headsets events if there is a call
        TelephonyManager telManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (telManager != null && telManager.getCallState() != TelephonyManager.CALL_STATE_IDLE)
            return;

            /*
             * Remote / headset control events
             */
        if (action.equalsIgnoreCase(Constants.ACTION_PLAYPAUSE)) {
            if (mPlayback.getState() == PlaybackState.STATE_PLAYING)
                handlePauseRequest();
            else if (mPlayback.getState() == PlaybackState.STATE_PAUSED)
                handleUnpauseRequest();
        } else if (action.equalsIgnoreCase(Constants.ACTION_UNPAUSE)) {
            handleUnpauseRequest();
        } else if (action.equalsIgnoreCase(Constants.ACTION_PAUSE)) {
            handlePauseRequest();
        } else if (action.equalsIgnoreCase(Constants.ACTION_STOP)) {
            handleStopRequest(null, intent.getBooleanExtra("stopService", false));

        } else if (action.equalsIgnoreCase(Constants.ACTION_PLAY)) {
            play(context, intent);
        }
        else if (action.equalsIgnoreCase(Constants.ACTION_SEEK)) {
            handleSeekRequest(intent.getLongExtra("position", 0));
        }

            /*
             * Sleep
             */
        if (action.equalsIgnoreCase(Constants.SLEEP_INTENT)) {
            handleStopRequest(null, true);
        }
    }

    @Override
    public BrowserRoot onGetRoot(String clientPackageName, int clientUid, Bundle rootHints) {

        logger.Debug("OnGetRoot: clientPackageName=" + clientPackageName, "; clientUid=" + clientUid + " ; rootHints=", rootHints);

        // To ensure you are not allowing any arbitrary app to browse your app's contents, you
        // need to check the origin:
        if (!mPackageValidator.isCallerAllowed(this, clientPackageName, clientUid)) {
            // If the request comes from an untrusted package, return null. No further calls will
            // be made to other media browsing methods.
            logger.Warn("OnGetRoot: IGNORING request from untrusted package " + clientPackageName);
            return null;
        }

        //noinspection StatementWithEmptyBody
        if (CarHelper.isValidCarPackage(clientPackageName)) {
            // Optional: if your app needs to adapt ads, media library or anything else that
            // needs to run differently when connected to the car, this is where you should handle
            // it.
        }

        return new BrowserRoot(MediaProvider.MEDIA_ID_ROOT, null);
    }

    @Override
    public void onLoadChildren(final String parentMediaId, final Result<List<MediaBrowser.MediaItem>> result) {

        if (!mMediaProvider.isInitialized()) {
            // Use result.detach to allow calling result.sendResult from another thread:
            result.detach();

            mMediaProvider.retrieveMediaAsync(new MediaProvider.Callback() {
                @Override
                public void onMusicCatalogReady(boolean success) {
                    if (success) {
                        loadChildrenImpl(parentMediaId, result);
                    } else {
                        updatePlaybackState("nometadata");
                        result.sendResult(Collections.<MediaBrowser.MediaItem>emptyList());
                    }
                }
            });

        } else {
            // If our media catalog is already loaded/cached, load them into result immediately
            loadChildrenImpl(parentMediaId, result);
        }
    }

    /**
     * Actual implementation of onLoadChildren that assumes that MediaProvider is already
     * initialized.
     */
    private void loadChildrenImpl(final String parentMediaId,
                                  final Result<List<MediaBrowser.MediaItem>> result) {

        logger.Debug("OnLoadChildren: parentMediaId=", parentMediaId);

        List<MediaBrowser.MediaItem> mediaItems = new ArrayList<MediaBrowser.MediaItem>();

        if (MediaProvider.MEDIA_ID_ROOT.equals(parentMediaId)) {
            logger.Debug("OnLoadChildren.ROOT");

        } else {
            logger.Warn("Skipping unmatched parentMediaId: ", parentMediaId);
        }
        logger.Debug("OnLoadChildren sending ", mediaItems.size(),
                " results for ", parentMediaId);

        result.sendResult(mediaItems);
    }

    /**
     * On current media completed.
     */
    public void onPlaybackCompletion() {
        // The media player finished playing the current song, so we go ahead
        // and start the next.
        if (mPlayingQueue != null && !mPlayingQueue.isEmpty()) {
            // In this sample, we restart the playing queue when it gets to the end:
            mCurrentIndexOnQueue++;
            if (mCurrentIndexOnQueue >= mPlayingQueue.size()) {
                handleStopRequest(null, true);
                return;
                //mCurrentIndexOnQueue = 0;
            }
            handlePlayRequest();
        } else {
            // If there is nothing to play, we stop and release the resources:
            handleStopRequest(null, true);
        }
    }

    @Override
    public void onPlaybackStatusChanged(int state) {
        updatePlaybackState(null);
    }

    @Override
    public void onPlaybackError(String error) {
        updatePlaybackState(error);
    }

    private void play(Context context, Intent intent) {

        String path = intent.getStringExtra("path");
        String itemJson = intent.getStringExtra("item");
        String mediaSourceJson = intent.getStringExtra("mediaSource");
        final String posterUrl = intent.getStringExtra("posterUrl");

        final BaseItemDto item = jsonSerializer.DeserializeFromString(itemJson, BaseItemDto.class);
        final MediaSourceInfo mediaSource = jsonSerializer.DeserializeFromString(mediaSourceJson, MediaSourceInfo.class);

        // The mediaId used here is not the unique mediaId. This one comes from the
        // MediaBrowser, and is actually a "hierarchy-aware mediaID": a concatenation of
        // the hierarchy in MediaBrowser and the actual unique mediaId. This is necessary
        // so we can build the correct playing queue, based on where the track was
        // selected from.
        mPlayingQueue = QueueHelper.getPlayingQueue(item, mediaSource, path, posterUrl, mMediaProvider, logger);
        mSession.setQueue(mPlayingQueue);

        String mediaId = item.getId();

        mSession.setQueueTitle(item.getName());

        if (mPlayingQueue != null && !mPlayingQueue.isEmpty()) {
            // set the current index on queue from the media Id:
            mCurrentIndexOnQueue = QueueHelper.getMusicIndexOnQueue(mPlayingQueue, mediaId);

            if (mCurrentIndexOnQueue < 0) {
                logger.Error("playFromMediaId: media ID ", mediaId,
                        " could not be found on queue. Ignoring.");
            } else {
                // play the media
                handlePlayRequest();
            }
        }
    }

    protected void updatePlaybackState(String error) {

        int state = mPlayback.getState();

        //logger.Debug("updatePlaybackState, playback state=" + state);
        long position = PlaybackState.PLAYBACK_POSITION_UNKNOWN;
        if (mPlayback != null) {
            position = mPlayback.getCurrentPositionMs();
        }

        PlaybackState.Builder stateBuilder = new PlaybackState.Builder()
                .setActions(getAvailableActions());

        setCustomAction(stateBuilder);
        // If there is an error message, send it to the playback state:
        if (error != null) {
            // Error states are really only supposed to be used for errors that cause playback to
            // stop unexpectedly and persist until the user takes action to fix it.
            stateBuilder.setErrorMessage(error);
            state = PlaybackState.STATE_ERROR;
        }
        stateBuilder.setState(state, position, 1.0f, SystemClock.elapsedRealtime());

        // Set the activeQueueItemId if the current index is valid.
        if (QueueHelper.isIndexPlayable(mCurrentIndexOnQueue, mPlayingQueue)) {
            MediaSession.QueueItem item = mPlayingQueue.get(mCurrentIndexOnQueue);
            stateBuilder.setActiveQueueItemId(item.getQueueId());
        }

        mSession.setPlaybackState(stateBuilder.build());

        //if (state == PlaybackState.STATE_PLAYING || state == PlaybackState.STATE_PAUSED) {
        //    mMediaNotificationManager.startNotification(currentBitmap);
        //}
    }

    private void updateMetadata() {

        if (!QueueHelper.isIndexPlayable(mCurrentIndexOnQueue, mPlayingQueue)) {
            logger.Error("Can't retrieve current metadata.");
            updatePlaybackState("nometadata");
            return;
        }
        final MediaSession.QueueItem queueItem = mPlayingQueue.get(mCurrentIndexOnQueue);
        String musicId = queueItem.getDescription().getMediaId();
        final MediaMetadata track = mMediaProvider.getMusic(musicId);
        final String trackId = track.getString(MediaMetadata.METADATA_KEY_MEDIA_ID);

        if (!musicId.equals(trackId)) {
            IllegalStateException e = new IllegalStateException("track ID should match musicId.");
            logger.Error("track ID should match musicId.",
                    " musicId=", musicId, " trackId=", trackId,
                    " mediaId from queueItem=", queueItem.getDescription().getMediaId(),
                    " title from queueItem=", queueItem.getDescription().getTitle(),
                    " mediaId from track=", track.getDescription().getMediaId(),
                    " title from track=", track.getDescription().getTitle(),
                    " source.hashcode from track=", track.getString(
                            MediaProvider.CUSTOM_METADATA_TRACK_SOURCE).hashCode(),
                    e);
            throw e;
        }
        logger.Debug("Updating metadata for MusicID= " + musicId);
        mSession.setMetadata(track);

        // Set the proper album artwork on the media session, so it can be shown in the
        // locked screen and in other places.
        if (track.getDescription().getIconBitmap() == null &&
                track.getDescription().getIconUri() != null) {
            String posterUrl = track.getDescription().getIconUri().toString();

            if (posterUrl != null && posterUrl.length() > 0) {
                getHttpClient().getBitmap(posterUrl, new Response<Bitmap>() {

                    @Override
                    public void onResponse(Bitmap bitmap) {
                        currentBitmap = bitmap;

                        MediaMetadata updatedTrack = new MediaMetadata.Builder(track)

                                // set high resolution bitmap in METADATA_KEY_ALBUM_ART. This is used, for
                                // example, on the lockscreen background when the media session is active.
                                .putBitmap(MediaMetadata.METADATA_KEY_ALBUM_ART, bitmap)

                                        // set small version of the album art in the DISPLAY_ICON. This is used on
                                        // the MediaDescription and thus it should be small to be serialized if
                                        // necessary..
                                        //.putBitmap(MediaMetadata.METADATA_KEY_DISPLAY_ICON, icon)

                                .build();

                        // If we are still playing the same music
                        String currentPlayingId = queueItem.getDescription().getMediaId();
                        if (trackId.equals(currentPlayingId)) {
                            mSession.setMetadata(updatedTrack);
                            updatePlaybackState(null);
                        }
                    }
                });
            }
        }
    }

    private void setCustomAction(PlaybackState.Builder stateBuilder) {
        MediaMetadata currentMusic = getCurrentPlayingMusic();

        if (currentMusic != null) {
            // Set appropriate "Favorite" icon on Custom action:
            String musicId = currentMusic.getString(MediaMetadata.METADATA_KEY_MEDIA_ID);
            int favoriteIcon = mediaRes.getFavoriteOffIcon();
            if (mMediaProvider.isFavorite(musicId)) {
                favoriteIcon = mediaRes.getFavoriteOnIcon();
            }

            //logger.Debug("updatePlaybackState, setting Favorite custom action of music ", musicId, " current favorite=", mMediaProvider.isFavorite(musicId));
            stateBuilder.addCustomAction(Constants.THUMBS_UP, getString(mediaRes.getFavoriteButtonString()), favoriteIcon);
        }
    }

    private long getAvailableActions() {
        long actions = PlaybackState.ACTION_PLAY | PlaybackState.ACTION_PLAY_FROM_MEDIA_ID |
                PlaybackState.ACTION_PLAY_FROM_SEARCH;
        if (mPlayingQueue == null || mPlayingQueue.isEmpty()) {
            return actions;
        }
        if (mPlayback.isPlaying()) {
            actions |= PlaybackState.ACTION_PAUSE;
        }
        //if (mCurrentIndexOnQueue > 0) {
        actions |= PlaybackState.ACTION_SKIP_TO_PREVIOUS;
        //}
        //if (mCurrentIndexOnQueue < mPlayingQueue.size() - 1) {
        actions |= PlaybackState.ACTION_SKIP_TO_NEXT;
        //}
        return actions;
    }

    /**
     * Handle a request to pause music
     */
    protected void handlePauseRequest() {

        logger.Debug("handlePauseRequest: mState=" + mPlayback.getState());
        mPlayback.pause();
        // reset the delayed stop handler.
        mDelayedStopHandler.removeCallbacksAndMessages(null);
        mDelayedStopHandler.sendEmptyMessageDelayed(0, STOP_DELAY);
    }

    protected void handlePlayRequest() {

        logger.Debug("handlePlayRequest: mState=" + mPlayback.getState());

        mDelayedStopHandler.removeCallbacksAndMessages(null);
        if (!mServiceStarted) {
            logger.Debug("Starting service");
            // The MusicService needs to keep running even after the calling MediaBrowser
            // is disconnected. Call startService(Intent) and then stopSelf(..) when we no longer
            // need to play media.
            startService(new Intent(getApplicationContext(), getServiceClass()));
            mServiceStarted = true;
        }

        if (!mSession.isActive()) {
            mSession.setActive(true);
        }

        if (QueueHelper.isIndexPlayable(mCurrentIndexOnQueue, mPlayingQueue)) {
            updateMetadata();
            mPlayback.play(mPlayingQueue.get(mCurrentIndexOnQueue));
        }
    }

    protected void handleSeekRequest(long positionMs) {

        logger.Debug("onSeekTo:", positionMs);
        mPlayback.seek(positionMs);
    }

    protected void handleUnpauseRequest() {

        mDelayedStopHandler.removeCallbacksAndMessages(null);
        if (!mServiceStarted) {
            logger.Debug("Starting service");
            // The MusicService needs to keep running even after the calling MediaBrowser
            // is disconnected. Call startService(Intent) and then stopSelf(..) when we no longer
            // need to play media.
            startService(new Intent(getApplicationContext(), getServiceClass()));
            mServiceStarted = true;
        }

        if (!mSession.isActive()) {
            mSession.setActive(true);
        }

        logger.Debug("handleUnpauseRequest: mState=" + mPlayback.getState());
        mPlayback.unPause();
        // reset the delayed stop handler.
    }

    protected void handleNextTrackRequest() {

        logger.Debug("skipToNext");
        mCurrentIndexOnQueue++;
        if (mPlayingQueue != null && mCurrentIndexOnQueue >= mPlayingQueue.size()) {
            // This sample's behavior: skipping to next when in last song returns to the
            // first song.
            mCurrentIndexOnQueue = 0;
        }
        if (QueueHelper.isIndexPlayable(mCurrentIndexOnQueue, mPlayingQueue)) {
            handlePlayRequest();
        } else {
            logger.Error("skipToNext: cannot skip to next. next Index=" +
                    mCurrentIndexOnQueue + " queue length=" +
                    (mPlayingQueue == null ? "null" : mPlayingQueue.size()));
            handleStopRequest("Cannot skip", true);
        }
    }

    protected void handlePreviousTrackRequest() {

        logger.Debug("skipToPrevious");
        mCurrentIndexOnQueue--;
        if (mPlayingQueue != null && mCurrentIndexOnQueue < 0) {
            // This sample's behavior: skipping to previous when in first song restarts the
            // first song.
            mCurrentIndexOnQueue = 0;
        }
        if (QueueHelper.isIndexPlayable(mCurrentIndexOnQueue, mPlayingQueue)) {
            handlePlayRequest();
        } else {
            logger.Error("skipToPrevious: cannot skip to previous. previous Index=" +
                    mCurrentIndexOnQueue + " queue length=" +
                    (mPlayingQueue == null ? "null" : mPlayingQueue.size()));
            handleStopRequest("Cannot skip", true);
        }
    }

    protected void handleStopRequest(String withError, boolean stopSelf) {

        logger.Debug("handleStopRequest: mState=" + mPlayback.getState() + " error=", withError);
        mPlayback.stop(true);
        // reset the delayed stop handler.
        mDelayedStopHandler.removeCallbacksAndMessages(null);
        mDelayedStopHandler.sendEmptyMessageDelayed(0, STOP_DELAY);

        updatePlaybackState(null);

        currentBitmap = null;

        updatePlaybackState(withError);

        if (stopSelf) {
            // service is no longer necessary. Will be started again if needed.
            logger.Debug("MediaService.stopSelf");
            stopSelf();
            mServiceStarted = false;
        }
    }

    private MediaMetadata getCurrentPlayingMusic() {

        if (QueueHelper.isIndexPlayable(mCurrentIndexOnQueue, mPlayingQueue)) {
            MediaSession.QueueItem item = mPlayingQueue.get(mCurrentIndexOnQueue);
            if (item != null) {
                //logger.Debug("getCurrentPlayingMusic for musicId=", item.getDescription().getMediaId());

                return mMediaProvider.getMusic(item.getDescription().getMediaId());
            }
        }
        return null;
    }

    private final class MediaSessionCallback extends MediaSession.Callback {

        private ILogger logger;

        private MediaSessionCallback(ILogger logger) {
            this.logger = logger;
        }

        @Override
        public void onPlay() {
            logger.Debug("play");

            if (mPlayback.getState() == PlaybackState.STATE_PAUSED){
                handleUnpauseRequest();
                return;
            }

            if (mPlayingQueue != null && !mPlayingQueue.isEmpty()) {
                handlePlayRequest();
            }
        }

        @Override
        public void onSkipToQueueItem(long queueId) {

            logger.Debug("OnSkipToQueueItem:" + queueId);

            if (mPlayingQueue != null && !mPlayingQueue.isEmpty()) {
                // set the current index on queue from the music Id:
                mCurrentIndexOnQueue = QueueHelper.getMusicIndexOnQueue(mPlayingQueue, queueId);
                //play the music
                handlePlayRequest();
            }
        }

        @Override
        public void onSeekTo(long position) {
            handleSeekRequest(position);
        }

        @Override
        public void onPlayFromMediaId(String mediaId, Bundle extras) {
            logger.Debug("playFromMediaId mediaId:", mediaId, "  extras=", extras);

            // The mediaId used here is not the unique musicId. This one comes from the
            // MediaBrowser, and is actually a "hierarchy-aware mediaID": a concatenation of
            // the hierarchy in MediaBrowser and the actual unique musicID. This is necessary
            // so we can build the correct playing queue, based on where the track was
            // selected from.
            mPlayingQueue = QueueHelper.getPlayingQueue(mediaId, mMediaProvider, logger);
            mSession.setQueue(mPlayingQueue);
            //String queueTitle = getString(R.string.browse_musics_by_genre_subtitle,
            //MediaIDHelper.extractBrowseCategoryValueFromMediaID(mediaId));
            //mSession.setQueueTitle(queueTitle);

            if (mPlayingQueue != null && !mPlayingQueue.isEmpty()) {
                // set the current index on queue from the media Id:
                mCurrentIndexOnQueue = QueueHelper.getMusicIndexOnQueue(mPlayingQueue, mediaId);

                if (mCurrentIndexOnQueue < 0) {
                    logger.Error("playFromMediaId: media ID ", mediaId,
                            " could not be found on queue. Ignoring.");
                } else {
                    // play the music
                    handlePlayRequest();
                }
            }
        }

        @Override
        public void onPause() {
            logger.Debug("pause. current state=" + mPlayback.getState());
            handlePauseRequest();
        }

        @Override
        public void onStop() {
            logger.Debug("stop. current state=" + mPlayback.getState());
            handleStopRequest(null, true);
        }

        @Override
        public void onSkipToNext() {
            handleNextTrackRequest();
        }

        @Override
        public void onSkipToPrevious() {
            handlePreviousTrackRequest();
        }

        @Override
        public void onCustomAction(String action, Bundle extras) {

            if (Constants.THUMBS_UP.equals(action)) {
                logger.Info("onCustomAction: favorite for current track");
                MediaMetadata track = getCurrentPlayingMusic();
                if (track != null) {
                    String musicId = track.getString(MediaMetadata.METADATA_KEY_MEDIA_ID);
                    mMediaProvider.setFavorite(musicId, !mMediaProvider.isFavorite(musicId));
                }
                // playback state needs to be updated because the "Favorite" icon on the
                // custom action will change to reflect the new favorite state.
                updatePlaybackState(null);
            } else {
                logger.Error("Unsupported action: %", action);
            }
        }

        @Override
        public void onPlayFromSearch(String query, Bundle extras) {

            logger.Debug("playFromSearch  query=", query);

            if (TextUtils.isEmpty(query)) {
                // A generic search like "Play music" sends an empty query
                // and it's expected that we start playing something. What will be played depends
                // on the app: favorite playlist, "I'm feeling lucky", most recent, etc.
                mPlayingQueue = new ArrayList<MediaSession.QueueItem>();
            } else {
                mPlayingQueue = QueueHelper.getPlayingQueueFromSearch(query, mMediaProvider, logger);
            }

            logger.Debug("playFromSearch  playqueue.length=" + mPlayingQueue.size());
            mSession.setQueue(mPlayingQueue);

            if (mPlayingQueue != null && !mPlayingQueue.isEmpty()) {
                // immediately start playing from the beginning of the search results
                mCurrentIndexOnQueue = 0;

                handlePlayRequest();
            } else {
                // if nothing was found, we need to warn the user and stop playing
                handleStopRequest("nosearchresults", true);
            }
        }
    }

    /**
     * A simple handler that stops the service if playback is not active (playing)
     */
    private static class DelayedStopHandler extends Handler {
        private final WeakReference<BaseMediaBrowserService> mWeakReference;

        private DelayedStopHandler(BaseMediaBrowserService service) {
            mWeakReference = new WeakReference<BaseMediaBrowserService>(service);
        }

        @Override
        public void handleMessage(Message msg) {
            BaseMediaBrowserService service = mWeakReference.get();
            if (service != null && service.mPlayback != null) {
                if (service.mPlayback.isPlaying()) {
                    service.logger.Debug("Ignoring delayed stop since the media player is in use.");
                    return;
                }
                service.logger.Debug("Stopping service with delay handler.");
                service.stopSelf();
                service.mServiceStarted = false;
            }
        }
    }
}
