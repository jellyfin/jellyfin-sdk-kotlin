package mediabrowser.apiinteraction.android.sync;

import android.accounts.Account;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.os.Bundle;

public class OnDemandSync {

    private Context context;

    public OnDemandSync(Context context) {
        this.context = context;
    }

    public void Run() {

        Account account = new SyncAccountManager().CreateSyncAccount(context);

        // Pass the settings flags by inserting them in a bundle
        Bundle settingsBundle = new Bundle();
        settingsBundle.putBoolean(
                ContentResolver.SYNC_EXTRAS_MANUAL, true);
        settingsBundle.putBoolean(
                ContentResolver.SYNC_EXTRAS_EXPEDITED, true);
        /*
         * Request the sync for the default account, authority, and
         * manual sync settings
         */
        ContentResolver.requestSync(account, SyncAccountManager.AUTHORITY, settingsBundle);
    }
}
