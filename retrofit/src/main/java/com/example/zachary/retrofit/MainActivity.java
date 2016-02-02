package com.example.zachary.retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.IOException;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

//    String baseUrl = "https://api.github.com";
    String baseUrl="http://news-at.zhihu.com";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void get() throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GitHub service = retrofit.create(GitHub.class);
//        Call<List<Contributor>> repos = service.contributors("square", "retrofit");
        Call<Information> repos = service.contributors();
        repos.enqueue(new Callback<Information>() {
            @Override
            public void onResponse(Response<Information> response, Retrofit retrofit) {
                if(response.body()!=null){
                    Information info = response.body();
                    for(Information.Story story:info.stories){
                        Log.d("zhk-MainActivity", story.id+"+"+story.title);
                    }
                } else {
                    Log.e("zhk-MainActivity", "onResponse: body==null");
              }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
}
