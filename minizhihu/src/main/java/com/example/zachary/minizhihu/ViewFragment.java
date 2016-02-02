package com.example.zachary.minizhihu;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.leakcanary.RefWatcher;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zachary on 16/1/14.
 */
public class ViewFragment extends BaseFragment{
    @Bind(R.id.image_viewpager)
    ImageView image;
    @Bind(R.id.title_viewpager)
    TextView title;
    private String images;
    private int selectedId;
    private String content;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewpager, container, false);
        ButterKnife.bind(this, view);
        Picasso.with(getActivity()).load(images).into(image);
        title.setText(content);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = ExampleApplication.getRefWatcher(getActivity());
        refWatcher.watch(this);
    }

    @OnClick(R.id.image_viewpager)
    public void jumpToWebview(){
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        ContentFragment contentFragment = new ContentFragment();
        GetInfoUtil.GetExtraInfo(selectedId,contentFragment);
        Bundle args = new Bundle();
        args.putInt("selectedId", selectedId);
        contentFragment.setArguments(args);
        transaction.replace(R.id.activityFrame, contentFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onInfoChanged(Object Entity) {
        TitleBean.TopStoryEntity topStoryEntity = (TitleBean.TopStoryEntity)Entity;
        images = topStoryEntity.image;
        selectedId =topStoryEntity.id;
        content = topStoryEntity.title;
        if(image != null){
            Picasso.with(getActivity()).load(images).into(image);
            title.setText(content);
        }

    }
}
