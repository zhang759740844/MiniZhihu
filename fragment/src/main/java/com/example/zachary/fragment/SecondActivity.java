package com.example.zachary.fragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by zachary on 15/12/23.
 */
public class SecondActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Button button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction;
                transaction = manager.beginTransaction();
                Toast.makeText(SecondActivity.this,"first fragment",Toast.LENGTH_SHORT).show();
                RightFragment rightFragment  = new RightFragment();
                transaction.add(R.id.framelayout, rightFragment);
                transaction.commit();
            }
        });
        Button button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction;
                transaction = manager.beginTransaction();
                Toast.makeText(SecondActivity.this,"second fragment",Toast.LENGTH_SHORT).show();
                AnotherFragment rightFragment2  = new AnotherFragment();
                transaction.add(R.id.framelayout, rightFragment2);
                transaction.commit();
            }
        });
    }

}
