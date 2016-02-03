package mediabrowser.apiinteraction.android.sync;

import android.accounts.Account;
import android.content.ContentResolver;
import android.content.Context;
import android.os.Bundle;

public class PeriodicSync {

    private Context context;

    public PeriodicSync(Context context) {
        this.context = context;
    }

    public void Create(long syncIntervalSeconds) {

        AuthenticatorService.CreateSyncAccount(context);

        Account account = AuthenticatorService.GetAccount();

        // Pass the settings flags by inserting them in a bundle
        Bundle settingsBundle = new Bundle();

        /*
         * Turn on periodic syncing
         */
        ContentResolver.addPeriodicSync(
                account,
                AuthenticatorService.AUTHORITY,
                settingsBundle,
                syncIntervalSeconds);

    }

    public void Create() {

        // Default to hourly
        Create(3600);
    }
}
