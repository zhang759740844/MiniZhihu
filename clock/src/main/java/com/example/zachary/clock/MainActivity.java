package com.example.zachary.clock;

import android.app.Activity;
import android.app.Notification;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

//@Bind(R.id.contentWebView)
//WebView webView;

public class MainActivity extends FragmentActivity  {
//    @Bind(R.id.timePicker)
//    TimePicker timePick1;
//    @Bind(R.id.button_single)
//    Button single;
//    @Bind(R.id.button_repeat)
//    Button repeat;
//    @Bind(R.id.button_cancel)
//    Button cancel;



    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        single.setOnClickListener(this);
//        repeat.setOnClickListener(this);
//        cancel.setOnClickListener(this);

        //是否使用24小时制
//        timePick1.setIs24HourView(true);
//        TimeListener times = new TimeListener();
//        timePick1.setOnTimeChangedListener(times);
    }

//    @Override
//    public void onClick(View v) {
//        switch(v.getId()){
//            case R.id.button_cancel:
//                Intent intent = new Intent(MainActivity.this,MyService.class);
//                intent.putExtra("flag", MyService.STOP);
//                startService(intent);
//                Toast.makeText(MainActivity.this, "取消成功", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.button_single:
//                int h = timePick1.getCurrentHour();
//                int m = timePick1.getCurrentMinute();
//                Intent startService = new Intent(MainActivity.this,MyService.class);
//                startService.putExtra("hour",h);
//                startService.putExtra("minute", m);
//                startService.putExtra("flag", MyService.RUN);
//                startService(startService);
//                Toast.makeText(MainActivity.this, "单次闹铃设置成功", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.button_repeat:
//                int h_R = timePick1.getCurrentHour();
//                int m_R = timePick1.getCurrentMinute();
//                Intent startService_R = new Intent(MainActivity.this,MyService.class);
//                startService_R.putExtra("hour",h_R);
//                startService_R.putExtra("minute", m_R);
//                startService_R.putExtra("flag",MyService.RUN_REPEAT);
//                startService(startService_R);
//                Toast.makeText(MainActivity.this, "多次闹铃设置成功", Toast.LENGTH_SHORT).show();
//                break;
//            default:
//                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
//                break;
//        }
//    }

    class TimeListener implements TimePicker.OnTimeChangedListener {
        /**
         * view 当前选中TimePicker控件
         * hourOfDay 当前控件选中TimePicker 的小时
         * minute 当前选中控件TimePicker  的分钟
         */
        @Override
        public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
            // TODO Auto-generated method stub
        }

    }
}

