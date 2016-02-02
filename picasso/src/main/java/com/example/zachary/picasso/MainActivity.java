package com.example.zachary.picasso;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;


public class MainActivity extends Activity implements View.OnClickListener {
    private ImageView imageView;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=(Button)findViewById(R.id.button);
        imageView=(ImageView)findViewById(R.id.view);
        button.setOnClickListener(this);
        Picasso.with(MainActivity.this).setIndicatorsEnabled(true);
        Picasso.with(MainActivity.this).load("http://i.imgur.com/DvpvklR.png").into(imageView);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:

                Picasso.with(MainActivity.this).load("http://i.imgur.com/DvpvklR.png").resize(50, 50).centerCrop() .into(imageView);

                Log.d("MainActivity","true");
                break;
        }
    }
}
