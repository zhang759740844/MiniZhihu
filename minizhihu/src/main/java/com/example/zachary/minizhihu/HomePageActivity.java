package com.example.zachary.minizhihu;

import android.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;


public class HomePageActivity extends FragmentActivity {
    @Bind(R.id.drawerLayout)
    DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setTheme(R.style.MyThemeDefault);
        setContentView(R.layout.activity_zhihu);
        ButterKnife.bind(this);
        if (TitleFragment.dayOrNight) {
            setTheme(R.style.MyThemeDefault);
        }else{
            setTheme(R.style.MyThemeNight);
        }
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        TitleFragment titleFragment = new TitleFragment();
        transaction.replace(R.id.activityFrame, titleFragment);
        transaction.commit();
    }
    @Override
    public void onBackPressed() {
        FragmentManager manager = getSupportFragmentManager();
        BaseFragment fragment = (BaseFragment)manager.findFragmentById(R.id.activityFrame);
        if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)){
            mDrawerLayout.closeDrawers();
        }else if(fragment instanceof TitleFragment && ((TitleFragment) fragment).fragmentType == false) {
            TitleFragment homePage = new TitleFragment();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.activityFrame, homePage);
            transaction.commit();
        }else{
            super.onBackPressed();
        }


    }
}
