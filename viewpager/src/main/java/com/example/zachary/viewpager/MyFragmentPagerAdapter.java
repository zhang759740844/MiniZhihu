package com.example.zachary.viewpager;

/**
 * Created by zachary on 16/1/14.
 */
import java.util.ArrayList;

import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter{
    ArrayList<Fragment> list;
    public MyFragmentPagerAdapter(FragmentManager fm,ArrayList<Fragment> list) {
        super(fm);
        this.list = list;

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Fragment getItem(int arg0) {
        return list.get(arg0);
    }




}