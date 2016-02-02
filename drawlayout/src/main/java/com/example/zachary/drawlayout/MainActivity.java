package com.example.zachary.drawlayout;

import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity
{

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        initView();
        initEvents();

    }

    public void OpenLeftMenu(View view)
    {
        mDrawerLayout.openDrawer(Gravity.LEFT);
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED,
                Gravity.LEFT);
    }

    private void initEvents()
    {
        mDrawerLayout.setDrawerListener(new DrawerLayout.DrawerListener()
        {
            @Override
            public void onDrawerStateChanged(int newState)
            {

            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset)
            {


            }

            @Override
            public void onDrawerOpened(View drawerView)
            {
                Toast.makeText(MainActivity.this,"open",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawerClosed(View drawerView)
            {
                Toast.makeText(MainActivity.this,"close",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView()
    {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.id_drawerLayout);
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED,
                Gravity.RIGHT);
    }

}