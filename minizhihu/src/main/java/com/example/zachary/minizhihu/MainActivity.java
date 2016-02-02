package com.example.zachary.minizhihu;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zachary on 16/1/27.
 */
public class MainActivity extends Activity {
    @Bind(R.id.welcomepage)
    ImageView imageView;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        preferences=getSharedPreferences("user", Context.MODE_PRIVATE);
        String welcomePageURL = preferences.getString("welcomePageURL", "http:\\/\\/pic1.zhimg.com\\/80fb26ff0d65abb8a8d07519c6dfd96c.jpg");
        Picasso.with(this).load(welcomePageURL).into(imageView);
        GetInfoUtil.GetWelcomeImage(this);
        ObjectAnimator anim = ObjectAnimator//
                .ofFloat(imageView, "zhang", 1.0f, 1.15f)//
                .setDuration(3000)//
                ;
        anim.start();
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float cVal = (Float) animation.getAnimatedValue();
                imageView.setScaleX(cVal);
                imageView.setScaleY(cVal);
            }
        });
        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Intent intent = new Intent(MainActivity.this, HomePageActivity.class);
                startActivity(intent);
                MainActivity.this.finish();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

    }



    public void onInfoChanged(WelcomeImageBean info) {
        preferences=getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("welcomePageURL", info.img);
        editor.commit();

    }
}
