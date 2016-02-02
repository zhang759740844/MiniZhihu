package com.example.zachary.clock;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by zachary on 16/1/12.
 */
public interface ZhihuAPI {
    //public interface GitHub {
//       @GET("/repos/{owner}/{repo}/contributors")
//       Call<List<Contributor>> contributors(@Path("owner") String owner, @Path("repo") String repo);
//    }
    @GET("/api/4/news/{item}")
    Call<TitleBean> contributors(@Path("item") String item);
    @GET("/api/4/news/{item}/{date}")
    Call<TitleBean> contributors(@Path("item") String item, @Path("date") String date);
}
