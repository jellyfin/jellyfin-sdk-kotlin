// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
package org.jellyfin.sdk.api.client.extensions

import org.jellyfin.sdk.api.client.ApiClient
import org.jellyfin.sdk.api.operations.ActivityLogApi
import org.jellyfin.sdk.api.operations.ApiKeyApi
import org.jellyfin.sdk.api.operations.ArtistsApi
import org.jellyfin.sdk.api.operations.AudioApi
import org.jellyfin.sdk.api.operations.BrandingApi
import org.jellyfin.sdk.api.operations.ChannelsApi
import org.jellyfin.sdk.api.operations.CollectionApi
import org.jellyfin.sdk.api.operations.ConfigurationApi
import org.jellyfin.sdk.api.operations.DashboardApi
import org.jellyfin.sdk.api.operations.DevicesApi
import org.jellyfin.sdk.api.operations.DisplayPreferencesApi
import org.jellyfin.sdk.api.operations.DlnaApi
import org.jellyfin.sdk.api.operations.DlnaServerApi
import org.jellyfin.sdk.api.operations.DynamicHlsApi
import org.jellyfin.sdk.api.operations.EnvironmentApi
import org.jellyfin.sdk.api.operations.FilterApi
import org.jellyfin.sdk.api.operations.GenresApi
import org.jellyfin.sdk.api.operations.HlsSegmentApi
import org.jellyfin.sdk.api.operations.ImageApi
import org.jellyfin.sdk.api.operations.ImageByNameApi
import org.jellyfin.sdk.api.operations.InstantMixApi
import org.jellyfin.sdk.api.operations.ItemLookupApi
import org.jellyfin.sdk.api.operations.ItemRefreshApi
import org.jellyfin.sdk.api.operations.ItemUpdateApi
import org.jellyfin.sdk.api.operations.ItemsApi
import org.jellyfin.sdk.api.operations.LibraryApi
import org.jellyfin.sdk.api.operations.LibraryStructureApi
import org.jellyfin.sdk.api.operations.LiveTvApi
import org.jellyfin.sdk.api.operations.LocalizationApi
import org.jellyfin.sdk.api.operations.MediaInfoApi
import org.jellyfin.sdk.api.operations.MoviesApi
import org.jellyfin.sdk.api.operations.MusicGenresApi
import org.jellyfin.sdk.api.operations.NotificationsApi
import org.jellyfin.sdk.api.operations.PackageApi
import org.jellyfin.sdk.api.operations.PersonsApi
import org.jellyfin.sdk.api.operations.PlayStateApi
import org.jellyfin.sdk.api.operations.PlaylistsApi
import org.jellyfin.sdk.api.operations.PluginsApi
import org.jellyfin.sdk.api.operations.QuickConnectApi
import org.jellyfin.sdk.api.operations.RemoteImageApi
import org.jellyfin.sdk.api.operations.ScheduledTasksApi
import org.jellyfin.sdk.api.operations.SearchApi
import org.jellyfin.sdk.api.operations.SessionApi
import org.jellyfin.sdk.api.operations.StartupApi
import org.jellyfin.sdk.api.operations.StudiosApi
import org.jellyfin.sdk.api.operations.SubtitleApi
import org.jellyfin.sdk.api.operations.SuggestionsApi
import org.jellyfin.sdk.api.operations.SyncPlayApi
import org.jellyfin.sdk.api.operations.SystemApi
import org.jellyfin.sdk.api.operations.TimeSyncApi
import org.jellyfin.sdk.api.operations.TrailersApi
import org.jellyfin.sdk.api.operations.TvShowsApi
import org.jellyfin.sdk.api.operations.UniversalAudioApi
import org.jellyfin.sdk.api.operations.UserApi
import org.jellyfin.sdk.api.operations.UserLibraryApi
import org.jellyfin.sdk.api.operations.UserViewsApi
import org.jellyfin.sdk.api.operations.VideoAttachmentsApi
import org.jellyfin.sdk.api.operations.VideoHlsApi
import org.jellyfin.sdk.api.operations.VideosApi
import org.jellyfin.sdk.api.operations.YearsApi

public val ApiClient.activityLogApi: ActivityLogApi
	get() = getOrCreateApi { ActivityLogApi(it) }

public val ApiClient.apiKeyApi: ApiKeyApi
	get() = getOrCreateApi { ApiKeyApi(it) }

public val ApiClient.artistsApi: ArtistsApi
	get() = getOrCreateApi { ArtistsApi(it) }

public val ApiClient.audioApi: AudioApi
	get() = getOrCreateApi { AudioApi(it) }

public val ApiClient.brandingApi: BrandingApi
	get() = getOrCreateApi { BrandingApi(it) }

public val ApiClient.channelsApi: ChannelsApi
	get() = getOrCreateApi { ChannelsApi(it) }

public val ApiClient.collectionApi: CollectionApi
	get() = getOrCreateApi { CollectionApi(it) }

public val ApiClient.configurationApi: ConfigurationApi
	get() = getOrCreateApi { ConfigurationApi(it) }

public val ApiClient.dashboardApi: DashboardApi
	get() = getOrCreateApi { DashboardApi(it) }

public val ApiClient.devicesApi: DevicesApi
	get() = getOrCreateApi { DevicesApi(it) }

public val ApiClient.displayPreferencesApi: DisplayPreferencesApi
	get() = getOrCreateApi { DisplayPreferencesApi(it) }

public val ApiClient.dlnaApi: DlnaApi
	get() = getOrCreateApi { DlnaApi(it) }

public val ApiClient.dlnaServerApi: DlnaServerApi
	get() = getOrCreateApi { DlnaServerApi(it) }

public val ApiClient.dynamicHlsApi: DynamicHlsApi
	get() = getOrCreateApi { DynamicHlsApi(it) }

public val ApiClient.environmentApi: EnvironmentApi
	get() = getOrCreateApi { EnvironmentApi(it) }

public val ApiClient.filterApi: FilterApi
	get() = getOrCreateApi { FilterApi(it) }

public val ApiClient.genresApi: GenresApi
	get() = getOrCreateApi { GenresApi(it) }

public val ApiClient.hlsSegmentApi: HlsSegmentApi
	get() = getOrCreateApi { HlsSegmentApi(it) }

public val ApiClient.imageApi: ImageApi
	get() = getOrCreateApi { ImageApi(it) }

public val ApiClient.imageByNameApi: ImageByNameApi
	get() = getOrCreateApi { ImageByNameApi(it) }

public val ApiClient.instantMixApi: InstantMixApi
	get() = getOrCreateApi { InstantMixApi(it) }

public val ApiClient.itemLookupApi: ItemLookupApi
	get() = getOrCreateApi { ItemLookupApi(it) }

public val ApiClient.itemRefreshApi: ItemRefreshApi
	get() = getOrCreateApi { ItemRefreshApi(it) }

public val ApiClient.itemsApi: ItemsApi
	get() = getOrCreateApi { ItemsApi(it) }

public val ApiClient.libraryApi: LibraryApi
	get() = getOrCreateApi { LibraryApi(it) }

public val ApiClient.itemUpdateApi: ItemUpdateApi
	get() = getOrCreateApi { ItemUpdateApi(it) }

public val ApiClient.libraryStructureApi: LibraryStructureApi
	get() = getOrCreateApi { LibraryStructureApi(it) }

public val ApiClient.liveTvApi: LiveTvApi
	get() = getOrCreateApi { LiveTvApi(it) }

public val ApiClient.localizationApi: LocalizationApi
	get() = getOrCreateApi { LocalizationApi(it) }

public val ApiClient.mediaInfoApi: MediaInfoApi
	get() = getOrCreateApi { MediaInfoApi(it) }

public val ApiClient.moviesApi: MoviesApi
	get() = getOrCreateApi { MoviesApi(it) }

public val ApiClient.musicGenresApi: MusicGenresApi
	get() = getOrCreateApi { MusicGenresApi(it) }

public val ApiClient.notificationsApi: NotificationsApi
	get() = getOrCreateApi { NotificationsApi(it) }

public val ApiClient.packageApi: PackageApi
	get() = getOrCreateApi { PackageApi(it) }

public val ApiClient.personsApi: PersonsApi
	get() = getOrCreateApi { PersonsApi(it) }

public val ApiClient.playlistsApi: PlaylistsApi
	get() = getOrCreateApi { PlaylistsApi(it) }

public val ApiClient.playStateApi: PlayStateApi
	get() = getOrCreateApi { PlayStateApi(it) }

public val ApiClient.pluginsApi: PluginsApi
	get() = getOrCreateApi { PluginsApi(it) }

public val ApiClient.quickConnectApi: QuickConnectApi
	get() = getOrCreateApi { QuickConnectApi(it) }

public val ApiClient.remoteImageApi: RemoteImageApi
	get() = getOrCreateApi { RemoteImageApi(it) }

public val ApiClient.scheduledTasksApi: ScheduledTasksApi
	get() = getOrCreateApi { ScheduledTasksApi(it) }

public val ApiClient.searchApi: SearchApi
	get() = getOrCreateApi { SearchApi(it) }

public val ApiClient.sessionApi: SessionApi
	get() = getOrCreateApi { SessionApi(it) }

public val ApiClient.startupApi: StartupApi
	get() = getOrCreateApi { StartupApi(it) }

public val ApiClient.studiosApi: StudiosApi
	get() = getOrCreateApi { StudiosApi(it) }

public val ApiClient.subtitleApi: SubtitleApi
	get() = getOrCreateApi { SubtitleApi(it) }

public val ApiClient.suggestionsApi: SuggestionsApi
	get() = getOrCreateApi { SuggestionsApi(it) }

public val ApiClient.syncPlayApi: SyncPlayApi
	get() = getOrCreateApi { SyncPlayApi(it) }

public val ApiClient.systemApi: SystemApi
	get() = getOrCreateApi { SystemApi(it) }

public val ApiClient.timeSyncApi: TimeSyncApi
	get() = getOrCreateApi { TimeSyncApi(it) }

public val ApiClient.trailersApi: TrailersApi
	get() = getOrCreateApi { TrailersApi(it) }

public val ApiClient.tvShowsApi: TvShowsApi
	get() = getOrCreateApi { TvShowsApi(it) }

public val ApiClient.universalAudioApi: UniversalAudioApi
	get() = getOrCreateApi { UniversalAudioApi(it) }

public val ApiClient.userApi: UserApi
	get() = getOrCreateApi { UserApi(it) }

public val ApiClient.userLibraryApi: UserLibraryApi
	get() = getOrCreateApi { UserLibraryApi(it) }

public val ApiClient.userViewsApi: UserViewsApi
	get() = getOrCreateApi { UserViewsApi(it) }

public val ApiClient.videoAttachmentsApi: VideoAttachmentsApi
	get() = getOrCreateApi { VideoAttachmentsApi(it) }

public val ApiClient.videoHlsApi: VideoHlsApi
	get() = getOrCreateApi { VideoHlsApi(it) }

public val ApiClient.videosApi: VideosApi
	get() = getOrCreateApi { VideosApi(it) }

public val ApiClient.yearsApi: YearsApi
	get() = getOrCreateApi { YearsApi(it) }
