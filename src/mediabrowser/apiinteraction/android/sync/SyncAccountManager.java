package mediabrowser.apiinteraction.android.sync;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;

public class SyncAccountManager {

    // Constants
    // The authority for the sync adapter's content provider
    public static final String AUTHORITY = "com.mediabrowser.sync";
    // An account type, in the form of a domain name
    public static final String ACCOUNT_TYPE = "mediabrowser.tv";
    // The account name
    public static final String ACCOUNT = "syncaccount";
    // Instance fields
    Account mAccount;

    /**
     * Create a new dummy account for the sync adapter
     *
     * @param context The application context
     */
    public Account CreateSyncAccount(Context context) {
        // Create the account type and default account
        Account newAccount = new Account(
                ACCOUNT, ACCOUNT_TYPE);
        // Get an instance of the Android account manager
        AccountManager accountManager =
                (AccountManager) context.getSystemService(
                        Context.ACCOUNT_SERVICE);
        /*
         * Add the account and account type, no password or user data
         * If successful, return the Account object, otherwise report an error.
         */
        if (accountManager.addAccountExplicitly(newAccount, null, null)) {
            /*
             * If you don't set android:syncable="true" in
             * in your <provider> element in the manifest,
             * then call context.setIsSyncable(account, AUTHORITY, 1)
             * here.
             */
            //context.setIsSyncable(newAccount, AUTHORITY, 1);

        } else {
            /*
             * The account exists or some other error occurred. Log this, report it,
             * or handle it internally.
             */
        }

        return newAccount;
    }
}
