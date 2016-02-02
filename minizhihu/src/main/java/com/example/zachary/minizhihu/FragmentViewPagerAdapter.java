package com.example.zachary.minizhihu;

/**
 * Created by zachary on 16/1/14.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;

public class FragmentViewPagerAdapter extends FragmentPagerAdapter{
    ArrayList<? extends BaseFragment> list;
    public FragmentViewPagerAdapter(FragmentManager fm, ArrayList<? extends BaseFragment> list) {
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

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }
}