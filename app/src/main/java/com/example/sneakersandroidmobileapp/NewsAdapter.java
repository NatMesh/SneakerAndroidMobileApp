package com.example.sneakersandroidmobileapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder>  {
    //Create a var that will contain our ArrayList of type NewsItem
    private ArrayList<NewsItem> newsItemArrayList;

    public static class NewsViewHolder extends RecyclerView.ViewHolder{
        //we declare our controls that will hold info for our newsItem
        public ImageView newsImageView;
        public TextView newsTitle;
        public TextView newsLink;
        public TextView newsPubDate;
        //NOTE MAYBE NEED TO ADD LINK TO CUSTOM LAYOUT IF WE CANT HAVE IT SET TO NAVIGATE VIA A CLICK EVENT


        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            //initialize all our controls via the itemView property so we can pass values to them
            newsImageView = itemView.findViewById(R.id.newsImage);
            newsTitle = itemView.findViewById(R.id.newsTitle);
            newsLink = itemView.findViewById(R.id.newsLink);
            newsPubDate = itemView.findViewById(R.id.newsPubDate);
        }
    }

    //To pass the data from our array to our custom layour stored in the view object we need to create a constructor that takes in an ArrayList of type NewsItem
    public NewsAdapter(ArrayList<NewsItem> newsItemArrayList){
        //this now holds all the newsItems fetched from the RssFeed passed in via our constructor which we are in
        this.newsItemArrayList = newsItemArrayList;

    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Here we pass our custom layout news_item.xml which allows us to have multiple items in a recyclerview(similar to listview)
        //we do this by creating a View object to hold our news_item.xml layout file
        View newsView = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
        //Then we pass that view to our NewsViewHolder object
        NewsViewHolder newsViewHolder = new NewsViewHolder(newsView);
        return newsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        //we create a NewsItem object and pass it the position we are current at so it can be stored and we can pull data from that object
        //so it can be set to our layout
        //NOTE we do not need a for loop here because the getItemCount() method below will execute everything
        // however number of items we tell it to in this case newsItemArrayList.size() times.
        NewsItem currentNewsItem = newsItemArrayList.get(position);

        //we call our NewsViewHolder object named holder which holds all of the views on our layout(image, textview, etc)
        // and then set them by using the values in our newsItem object by calling the specific get methods
        //To bring in an image to our app from a url use PICASSO which makes it very simple
        Picasso.get().load(currentNewsItem.getImage()).fit().centerInside().into(holder.newsImageView);
        //set Title
        holder.newsTitle.setText(currentNewsItem.getTitle());
        holder.newsLink.setText(currentNewsItem.getLink());
        //Set published date
        holder.newsPubDate.setText(currentNewsItem.getPubDate().substring(0, 16));
    }

    @Override
    public int getItemCount() {
        return newsItemArrayList.size();
    }
}
