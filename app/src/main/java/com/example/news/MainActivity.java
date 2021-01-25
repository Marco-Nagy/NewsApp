package com.example.news;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    //http://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=a552a4a2da2c4805865c9a9c85056383
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                Log.d("test", "Ad loaded");
            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                // Code to be executed when an ad request fails.
                Log.d("test", "" + adError.toString());
            }

        });

        ProgressBar pb = findViewById(R.id.pb);
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl("http://newsapi.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CallableInterface callable = retrofit.create(CallableInterface.class);
        Call<NewsModel> newsModelCall = callable.getData();
        newsModelCall.enqueue(new Callback<NewsModel>() {
            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                pb.setVisibility(View.INVISIBLE);
                NewsModel newsModel = response.body();
                Log.d("json", "data" + newsModel.getArticles().get(0).getUrlToImage());
                showListView(newsModel.getArticles());
            }

            @Override
            public void onFailure(Call<NewsModel> call, Throwable t) {
                pb.setVisibility(View.INVISIBLE);
                Log.d("json", "Error" + t.getMessage());

            }
        });
    }

    private void showListView(ArrayList<Articles> articles) {
        CustomAdapter adapter = new CustomAdapter(this, articles);
        ListView lv = findViewById(R.id.lv);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener((parent, view, position, id) -> {
            Uri link = Uri.parse(articles.get(position).getUrl());
            Intent i = new Intent(Intent.ACTION_VIEW, link);
            startActivity(i);
        });
    }
}
