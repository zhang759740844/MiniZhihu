package com.example.zachary.clock;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class MyService extends Service {
    private static final int ANHOUR = 60*60*1000;
    private static final int AMINUTE = 60*1000;
    public static final int RUN = 1;
    public static final int BOOT = 0;
    public static final int SHUTDOWN = 2;
    public static final int STOP = 3;
    public static final int RUN_REPEAT = 4;
    private int hour;
    private int minute;
    private int flag;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Ringtone r;
    @Override
    public void onCreate() {
        super.onCreate();
        pref = getSharedPreferences("data", Context.MODE_PRIVATE);
        editor = pref.edit();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        flag = intent.getIntExtra("flag", 0);
        switch (flag){
            case BOOT:
                boolean isExist = pref.getBoolean("isExist",false);
                if(isExist){
                    hour = pref.getInt("hour",0);
                    minute = pref.getInt("minute",0);
                    Log.d("Boot", "Bootsuccess");
                    clocker();
                }
                break;
            case RUN_REPEAT:
            case RUN:
                hour = intent.getIntExtra("hour",0);
                minute = intent.getIntExtra("minute",0);
                Log.d("MyService", hour + " " + minute);
                editor.putInt("hour", hour);
                editor.putInt("minute", minute);
                editor.putBoolean("isExist", true);
                editor.commit();
//                int h = pref.getInt("hour",12);
//                int m = pref.getInt("minute",30);
//                Log.d("MyService",h+" "+m);
                clocker();

                break;
            case SHUTDOWN:
                Boolean b = pref.getBoolean("isExist",false);
                Log.d("MainActivity", b + "");
                editor.putBoolean("isExist", false);
                editor.commit();
                Boolean c = pref.getBoolean("isExist", false);
                Log.d("MainActivity", c + "");
                Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
                r = RingtoneManager.getRingtone(this, notification);
                r.play();
                Intent j = new Intent(this,MainActivity.class);
                j.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(j);
//                NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
//                PendingIntent pi = PendingIntent.getActivity(this, 0, j,PendingIntent.FLAG_UPDATE_CURRENT);
//                Notification.Builder builder = new Notification.Builder(this);
//                builder.setContentIntent(pi).setWhen(System.currentTimeMillis()).setAutoCancel(false).setContentTitle("title").setContentText("time is over");
//                manager.notify(1, builder.getNotification());
                Log.d("MainActivity","SHUTDOWN");
                break;
            case STOP:
                clocker();
                if(r != null){
                    r.stop();
                }

            default:
                break;
        }
        return super.onStartCommand(intent, flags, startId);
    }

    public void clocker(){
        Intent intent = new Intent(this,MyService.class);
        intent.putExtra("flag",SHUTDOWN);
        PendingIntent sender = PendingIntent.getService(this,
                0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        AlarmManager am = (AlarmManager)getSystemService(Context.ALARM_SERVICE);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        if(flag == RUN || flag == BOOT){
            if(calendar.getTimeInMillis()<new Date().getTime()){
                calendar.add(Calendar.DAY_OF_YEAR,1);
                Log.d("monitor2",""+calendar.get(Calendar.DAY_OF_MONTH));
            }
            am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);
        }else if(flag==RUN_REPEAT){
            am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY, sender);
        }else if(flag == STOP){
            am.cancel(sender);
        }



//
//        AlarmManager manager = (AlarmManager)getSystemService(ALARM_SERVICE);
//        long triggerAtTime = SystemClock.elapsedRealtime()+hour*ANHOUR+minute*AMINUTE;
//        Intent i = new Intent(this,SecondActivity.class);
//        PendingIntent pi =PendingIntent.getActivity(this,0,i,0);
//        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,triggerAtTime,pi);
        Log.d("MyService","zzz ");

    }
    @Override
    public void onDestroy() {




        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

}