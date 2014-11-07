package mediabrowser.apiinteraction.android.sync;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class SyncAlarmStarter {

    public void Start(Context context){

        // Construct an intent that will execute the AlarmReceiver
        Intent intent = new Intent(context, SyncAlarmReceiver.class);
        // Create a PendingIntent to be triggered when the alarm goes off
        final PendingIntent pIntent = PendingIntent.getBroadcast(context, SyncAlarmReceiver.REQUEST_CODE,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Setup periodic alarm every 30 minutes
        long firstMillis = System.currentTimeMillis(); // first run of alarm is immediate
        int intervalMillis = 1800000; // 30 minutes
        AlarmManager alarm = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, firstMillis, intervalMillis, pIntent);
    }
}
