package com.example.zachary.minizhihu;



import android.app.Activity;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

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
    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    public static final ZhihuAPI service = retrofit.create(ZhihuAPI.class);


    //http://news-at.zhihu.com/api/4/start-image/1080*1776
    public static void GetWelcomeImage(final MainActivity activity){
        Call<WelcomeImageBean> repos = service.welcome("start-image","720*1184");
        repos.enqueue(new Callback<WelcomeImageBean>() {
            @Override
            public void onResponse(Response<WelcomeImageBean> response, Retrofit retrofit) {
                if (response.body() != null) {
                    WelcomeImageBean info = response.body();
//                    List<MenuBean.ThemeEntity> stories = info.others;
//                        TitleFragment fragTemp = (TitleFragment)fragment;
                    activity.onInfoChanged(info);
                }
            }
            @Override
            public void onFailure(Throwable t) {
            }
        });

    }




    public static void GetMenu(final BaseFragment fragment){
        Call<MenuBean> repos = service.menus("themes");
        repos.enqueue(new Callback<MenuBean>() {
            @Override
            public void onResponse(Response<MenuBean> response, Retrofit retrofit) {
                if (response.body() != null) {
                    MenuBean info = response.body();
//                    List<MenuBean.ThemeEntity> stories = info.others;
//                        TitleFragment fragTemp = (TitleFragment)fragment;
                    fragment.onInfoChanged(info.others);
                }
            }
            @Override
            public void onFailure(Throwable t) {
            }
        });

    }

    //get latest news
    public static void GetTitle(final int state, final BaseFragment fragment,final List<? extends BaseFragment> fragmentList){
        if(state == PULLDOWN){
            Call<TitleBean> repos = service.titles("news", "latest");
            repos.enqueue(new Callback<TitleBean>() {
                @Override
                public void onResponse(Response<TitleBean> response, Retrofit retrofit) {
                    if (response.body() != null) {
                        TitleBean info = response.body();
                        date = Integer.parseInt(info.date);
//                        List<TitleBean.StoriesEntity> stories = info.stories;
//                        TitleFragment fragTemp = (TitleFragment)fragment;
                        fragment.onInfoChanged(info.stories, state);
                        for(int i=0;i<fragmentList.size();i++){
//                            BaseFragment fragTemp = (BaseFragment)fragmentList.get(i);
//                            ViewFragment vf =(ViewFragment)fragTemp;
                            fragmentList.get(i).onInfoChanged(info.top_stories.get(i));
                        }
                    }
                }
                @Override
                public void onFailure(Throwable t) {
                }
            });
        }else if(state == PULLUP) {
            Call<TitleBean> repos = service.titles("news", "before", date-- + "");         //because  the date of json which ZhiHu returned is the day before that day
            repos.enqueue(new Callback<TitleBean>() {
                @Override
                public void onResponse(Response<TitleBean> response, Retrofit retrofit) {
                    if (response.body() != null) {
                        TitleBean info = response.body();
//                        List<TitleBean.StoriesEntity> stories = info.stories;
//                        TitleFragment fragTemp = (TitleFragment) fragment;
                        fragment.onInfoChanged(info.stories, state);
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

    //get theme news
    public static void GetThemeTitle(final int selectedId, final BaseFragment fragment){
        Call<ThemeBean> repos = service.themes("theme", selectedId+"");
        repos.enqueue(new Callback<ThemeBean>() {
            @Override
            public void onResponse(Response<ThemeBean> response, Retrofit retrofit) {
                if (response.body() != null) {
                    ThemeBean info = response.body();
//                        List<TitleBean.StoriesEntity> stories = info.stories;
//                        TitleFragment fragTemp = (TitleFragment)fragment;
                    fragment.onInfoChanged(info.stories, GetInfoUtil.PULLDOWN);
                    fragment.onInfoChanged(info.image);

                }
            }
            @Override
            public void onFailure(Throwable t) {
            }
        });

    }

    //get extra info of news
    public static void GetExtraInfo(final int selectedId, final BaseFragment fragment){
        Call<ExtraInfoBean> repos = service.extraInfo("story-extra", selectedId + "");
        repos.enqueue(new Callback<ExtraInfoBean>() {
            @Override
            public void onResponse(Response<ExtraInfoBean> response, Retrofit retrofit) {
                if (response.body() != null) {
                    ExtraInfoBean info = response.body();
                    fragment.onInfoChanged(info);
                }
            }
            @Override
            public void onFailure(Throwable t) {
            }
        });
    }

    public static void GetLongComment(final int selectedId, final BaseFragment fragment){
        Call<CommentBean> repos = service.comment("story", selectedId+"","short-comments");
        repos.enqueue(new Callback<CommentBean>() {
            @Override
            public void onResponse(Response<CommentBean> response, Retrofit retrofit) {
                if (response.body() != null) {
                    CommentBean info = response.body();
                    fragment.onInfoChanged(info.comments);
                }
            }
            @Override
            public void onFailure(Throwable t) {
            }
        });
    }







//    public static void GetImage(final List<? extends BaseFragment> fragmentList){
//        Call<TitleBean> repos = service.titles("news", "latest");
//        repos.enqueue(new Callback<TitleBean>() {
//            @Override
//            public void onResponse(Response<TitleBean> response, Retrofit retrofit) {
//                if (response.body() != null) {
//                    TitleBean info = response.body();
////                    List<TitleBean.TopStoryEntity> topStoryEntities = info.top_stories;
//                    for(int i=0;i<fragmentList.size();i++){
////                        BaseFragment fragTemp = (BaseFragment);
//                        fragmentList.get(i).onInfoChanged(info.top_stories.get(i));
//                    }
//                }
//            }
//            @Override
//            public void onFailure(Throwable t) {
//            }
//        });
//    }




}
