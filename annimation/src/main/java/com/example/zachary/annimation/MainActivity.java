package com.example.zachary.annimation;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void rotateyAnimRun(final View view)
    {
        ObjectAnimator anim = ObjectAnimator//
                .ofFloat(view, "roationX", 1.0f, 0.5f)//
                .setDuration(500)//
                ;
        anim.start();
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float cVal = (Float) animation.getAnimatedValue();
                view.setAlpha(cVal-0.5f);
                view.setScaleX(cVal);
                view.setScaleY(cVal);
            }
        });


    }

}
