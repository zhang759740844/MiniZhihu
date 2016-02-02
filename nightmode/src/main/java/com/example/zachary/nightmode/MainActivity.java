package com.example.zachary.nightmode;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private boolean blFlag = false;
    private ImageView ivBook;
    private Button btnSet;
    private Button btnGet;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTheme(R.style.MyThemeDefault);
        setContentView(R.layout.activity_main);
        btnSet = (Button) findViewById(R.id.btnSet);
        btnGet = (Button) findViewById(R.id.btnGet);
        ivBook = (ImageView) findViewById(R.id.ivBook);
        btnSet.setOnClickListener(new onClickListenerImp());
        btnGet.setOnClickListener(new onClickListenerImp());
        ivBook.setOnClickListener(new onClickListenerImp());

    }

    class onClickListenerImp implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            if (v == btnSet || v == ivBook) {
                if (blFlag) {
                    setTheme(R.style.MyThemeDefault);
                    blFlag = false;
                } else if (!blFlag) {
                    setTheme(R.style.MyThemeNight);
                    blFlag = true;
                }
                setContentView(R.layout.activity_main);
                btnSet = (Button) findViewById(R.id.btnSet);
                btnGet = (Button) findViewById(R.id.btnGet);
                ivBook = (ImageView) findViewById(R.id.ivBook);
                btnSet.setOnClickListener(new onClickListenerImp());
                btnGet.setOnClickListener(new onClickListenerImp());
                ivBook.setOnClickListener(new onClickListenerImp());
            } else if (v == btnGet) {
                Toast.makeText(MainActivity.this, "blFlag: " + blFlag,
                        Toast.LENGTH_SHORT).show();
            }
        }

    }

}