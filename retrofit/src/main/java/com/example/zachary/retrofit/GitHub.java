package com.example.zachary.retrofit;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Zhk on 2015/12/20.
 */
//public interface GitHub {
//       @GET("/repos/{owner}/{repo}/contributors")
//       Call<List<Contributor>> contributors(@Path("owner") String owner, @Path("repo") String repo);
//    }
public interface GitHub {
    @GET("/api/4/news/before/20160112")
    Call<Information> contributors();
}