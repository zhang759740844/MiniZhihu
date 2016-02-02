package com.example.zachary.minizhihu;

import org.w3c.dom.Comment;

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

//
//    @GET("/api/4/news/{item}")
//    Call<TitleBean> contributors(@Path("item") String item);

    @GET("/api/4/{a}")
    Call<TitleBean> titles(@Path("a") String a);
    @GET("/api/4/{a}/{b}")
    Call<TitleBean> titles(@Path("a") String a,@Path("b") String b );
    @GET("/api/4/{a}/{b}/{c}")
    Call<TitleBean> titles(@Path("a") String a,@Path("b") String b ,@Path("c") String c);

    @GET("/api/4/{a}")
    Call<MenuBean> menus(@Path("a") String a);
    @GET("/api/4/{a}/{b}")
    Call<MenuBean> menus(@Path("a") String a,@Path("b") String b );
    @GET("/api/4/{a}/{b}/{c}")
    Call<MenuBean> menus(@Path("a") String a,@Path("b") String b ,@Path("c") String c);

    @GET("/api/4/{a}")
    Call<ThemeBean> themes(@Path("a") String a);
    @GET("/api/4/{a}/{b}")
    Call<ThemeBean> themes(@Path("a") String a,@Path("b") String b );
    @GET("/api/4/{a}/{b}/{c}")
    Call<ThemeBean> themes(@Path("a") String a,@Path("b") String b ,@Path("c") String c);

    @GET("/api/4/{a}")
    Call<ExtraInfoBean> extraInfo(@Path("a") String a);
    @GET("/api/4/{a}/{b}")
    Call<ExtraInfoBean> extraInfo(@Path("a") String a,@Path("b") String b );
    @GET("/api/4/{a}/{b}/{c}")
    Call<ExtraInfoBean> extraInfo(@Path("a") String a,@Path("b") String b ,@Path("c") String c);

    @GET("/api/4/{a}")
    Call<CommentBean> comment(@Path("a") String a);
    @GET("/api/4/{a}/{b}")
    Call<CommentBean> comment(@Path("a") String a,@Path("b") String b );
    @GET("/api/4/{a}/{b}/{c}")
    Call<CommentBean> comment(@Path("a") String a,@Path("b") String b ,@Path("c") String c);

    @GET("/api/4/{a}/{b}")
    Call<WelcomeImageBean> welcome(@Path("a") String a,@Path("b") String b );
}
