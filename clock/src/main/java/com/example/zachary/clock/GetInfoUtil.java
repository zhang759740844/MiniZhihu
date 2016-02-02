package com.example.zachary.clock;


import android.support.v4.app.Fragment;
import android.util.Log;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by zachary on 16/1/13.
 */
public class GetInfoUtil {
    public static final String BASEURL="http://news-at.zhihu.com";
    public static final int PULLUP = 0; // import more
    public static final int PULLDOWN = 1; //refresh
//    public static final int VIEWPAGER = 2;
    public static int date;


    public static void GetTitle(final int state, final Fragment fragment){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ZhihuAPI service = retrofit.create(ZhihuAPI.class);
        if(state == PULLDOWN){
            Call<TitleBean> repos = service.contributors("latest");
            repos.enqueue(new Callback<TitleBean>() {
                @Override
                public void onResponse(Response<TitleBean> response, Retrofit retrofit) {
                    if (response.body() != null) {
                        TitleBean info = response.body();
                        date = Integer.parseInt(info.date);
                        List<TitleBean.StoriesEntity> stories = info.stories;
                        TitleFragment fragTemp = (TitleFragment)fragment;
                        fragTemp.onInfoChanged(stories, state);
                    }
                }
                @Override
                public void onFailure(Throwable t) {
                }
            });
        }else if(state == PULLUP) {
            Call<TitleBean> repos = service.contributors("before", date-- + "");         //because  the date of json which ZhiHu returned is the day before that day
            repos.enqueue(new Callback<TitleBean>() {
                @Override
                public void onResponse(Response<TitleBean> response, Retrofit retrofit) {
                    if (response.body() != null) {
                        TitleBean info = response.body();
                        List<TitleBean.StoriesEntity> stories = info.stories;
                        TitleFragment fragTemp = (TitleFragment) fragment;
                        fragTemp.onInfoChanged(stories, state);
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                }
            });
        }else{
            Log.d("monitor","gettitle error");
        }
    }





}
