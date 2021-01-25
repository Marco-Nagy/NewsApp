package com.example.news;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    private Activity activity;
    private ArrayList<Articles> articles;

    public CustomAdapter(Activity activity, ArrayList<Articles> articles) {
        this.activity = activity;
        this.articles = articles;
    }

    @Override
    public int getCount() {
        return articles.size();
    }

    @Override
    public Object getItem(int position) {
        return articles.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null)
            view = activity.getLayoutInflater().inflate(R.layout.list_item2, parent, false);
        TextView tvTitle = view.findViewById(R.id.tv);
        ImageView iv = view.findViewById(R.id.iv);
        tvTitle.setText(articles.get(position).getTitle().substring(0,29)+"...");
        Picasso
                .get()
                .load(articles.get(position).getUrlToImage())
                .placeholder(R.drawable.ic_photo)
                .error(R.drawable.ic_broken_image)
                .into(iv);
        return view;
    }
}
