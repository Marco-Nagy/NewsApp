package com.example.news;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CallableInterface {
    @GET("v2/top-headlines?country=eg&category=general&apiKey=a552a4a2da2c4805865c9a9c85056383")
    Call<NewsModel> getData();
}