package com.example.zachary.clock;

/**
 * Created by zachary on 15/12/28.
 */
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by zachary on 15/12/28.
 */
public class BroadcastBoot extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context,MyService.class);
        i.putExtra("flag",0);
//        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        这里错了，不能是application的context要是这个服务的class
//        SharedPreferences preferences = context.getSharedPreferences("data",Context.MODE_PRIVATE);
//        Boolean b =preferences.getBoolean("isExist", false);
//        int h = preferences.getInt("hour", 12);
//        int m = preferences.getInt("minute",30);
//        if(b) {
//            Toast.makeText(context, h + "+" + m, Toast.LENGTH_SHORT).show();
//        }else{
//            Toast.makeText(context, "no data", Toast.LENGTH_SHORT).show();
//        }

        context.startService(i);
    }
}
