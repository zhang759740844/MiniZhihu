package com.example.zachary.viewpager;

import java.util.ArrayList;

import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {
    private ViewPager mPager;
    private ArrayList<Fragment> fragmentList;
    private ImageView image;
    private TextView view1, view2, view3, view4;
    private int currIndex;//当前页卡编号
    private int bmpW;//横线图片宽度
    private int offset;//图片移动的偏移量



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        InitViewPager();
    }
    /*
     * 初始化ViewPager
     */
    public void InitViewPager(){
        mPager = (ViewPager)findViewById(R.id.viewpager);
        fragmentList = new ArrayList<Fragment>();
        Fragment secondFragment = TestFragment.newInstance("this is second fragment");
        Fragment thirdFragment = TestFragment.newInstance("this is third fragment");
        Fragment fourthFragment = TestFragment.newInstance("this is fourth fragment");

        fragmentList.add(secondFragment);
        fragmentList.add(thirdFragment);
        fragmentList.add(fourthFragment);

        //给ViewPager设置适配器
        mPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentList));
        mPager.setCurrentItem(0);//设置当前显示标签页为第一页
        mPager.addOnPageChangeListener(new MyOnPageChangeListener());//页面变化时的监听器

    }


    private class MyOnPageChangeListener implements OnPageChangeListener{
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        // 当page变更的时候，而不是点击时调用
        public void onPageSelected(int arg0) {
            Toast.makeText(MainActivity.this, "您选择了第"+"个页卡", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }


}