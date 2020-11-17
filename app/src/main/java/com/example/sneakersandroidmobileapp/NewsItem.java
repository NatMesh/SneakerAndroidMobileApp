package com.example.sneakersandroidmobileapp;

public class NewsItem {
    private String image;
    private String title;
    private String description;
    private String pubDate;
    private String link;

    public NewsItem(String image, String title, String description, String pubDate, String link) {
        this.image = image;
        this.title = title;
        this.description = description;
        this.pubDate = pubDate;
        this.link = link;
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPubDate() {
        return pubDate;
    }

    public String getLink() {
        return link;
    }


}
