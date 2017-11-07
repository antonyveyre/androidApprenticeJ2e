package com.example.user.myapplication.model;

/**
 * Created by user on 06/11/17.
 */

public class Album {


    private String url;
    private String title;
    private String thumbnailUrl;


    public Album(String urlAlbum, String title) {
        this.url = urlAlbum;
        this.title = title;
    }

    public String getUrlAlbum() {
        return url;
    }

    public void setUrlAlbum(String _urlAlbum) {
        this.url = _urlAlbum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String _urlTitle) {
        this.title = _urlTitle;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

}
