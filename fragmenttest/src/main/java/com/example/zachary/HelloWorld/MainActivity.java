package com.example.zachary.HelloWorld;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView tv = (TextView) findViewById(R.id.tv_stack_val);


        Button btnLoadFrag1 = (Button)findViewById(R.id.btn_show_fragment1);
        btnLoadFrag1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                Fragment1 fragment1 = new Fragment1();
                transaction.add(R.id.fragment_container, fragment1, "fragment1");
                transaction.addToBackStack("fragment1");
                transaction.commit();


                int count = manager.getBackStackEntryCount();
                StringBuilder builder = new StringBuilder("回退栈内容为:\n");
                for (int i = --count;i>=0;i--){
                    FragmentManager.BackStackEntry entry= manager.getBackStackEntryAt(i);
                    builder.append(entry.getName()+"\n");
                }
                tv.setText(builder.toString());
            }
        });

        Button btnLoagFrag2 = (Button)findViewById(R.id.btn_show_fragment2);
        btnLoagFrag2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                Fragment2 fragment2 = new Fragment2();
                transaction.add(R.id.fragment_container, fragment2, "fragment2");
                transaction.addToBackStack("fragment2");
                transaction.commit();


                int count = manager.getBackStackEntryCount();
                StringBuilder builder = new StringBuilder("回退栈内容为:\n");
                for (int i = --count;i>=0;i--){
                    FragmentManager.BackStackEntry entry= manager.getBackStackEntryAt(i);
                    builder.append(entry.getName()+"\n");
                }
                tv.setText(builder.toString());
            }
        });

        Button button =(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                manager.popBackStack("fragment2",FragmentManager.POP_BACK_STACK_INCLUSIVE);


                int count = manager.getBackStackEntryCount();
                StringBuilder builder = new StringBuilder("回退栈内容为:\n");
                for (int i = --count;i>=0;i--){
                    FragmentManager.BackStackEntry entry= manager.getBackStackEntryAt(i);
                    builder.append(entry.getName()+"\n");
                }
                tv.setText(builder.toString());
            }
        });

    }
}
