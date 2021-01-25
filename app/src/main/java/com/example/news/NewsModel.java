package com.example.news;

import java.util.ArrayList;

public class NewsModel {
    private ArrayList<Articles> articles;
    public ArrayList<Articles>getArticles(){
        return articles;
    }


}
class Articles{
    private String title;
    private String url;
    private String urlToImage;

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public Articles(String title, String url, String urlToImage) {
        this.title = title;
        this.url = url;
        this.urlToImage = urlToImage;
    }
}
