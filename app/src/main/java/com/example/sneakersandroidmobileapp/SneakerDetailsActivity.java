package com.example.sneakersandroidmobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.like.LikeButton;
import com.like.OnLikeListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SneakerDetailsActivity extends AppCompatActivity {

    ImageView sneakerImage;
    TextView sneakerName;
    TextView sneakerBrand;
    TextView sneakerColourway;
    TextView sneakerRetailPrice;
    TextView sneakerReleaseDate;
    TextView sneakerStory;
    SessionVariableManager sessionVariableManager;
    TextView colourway;
    LikeButton heart;
    DataBaseHelper dataBaseHelper;
    int sneakerId;
    int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sneaker_details);

        sneakerImage = findViewById(R.id.sneakerImage);
        sneakerName = findViewById(R.id.sneakerName);
        sneakerBrand = findViewById(R.id.sneakerBrand);
        sneakerColourway = findViewById(R.id.sneakerColourway);
        sneakerRetailPrice = findViewById(R.id.sneakerRetailPrice);
        sneakerReleaseDate = findViewById(R.id.sneakerReleaseDate);
        sneakerStory = findViewById(R.id.sneakerStory);
        dataBaseHelper = new DataBaseHelper(SneakerDetailsActivity.this);

        colourway = findViewById(R.id.colourway);

        sessionVariableManager = new SessionVariableManager(SneakerDetailsActivity.this);
        userId = sessionVariableManager.getSession();

        heart = findViewById(R.id.heart_button);

        if(userId != -1){
            //User is logged in
            heart.setVisibility(View.VISIBLE);

        } else{
            //user is NOT logged in
            heart.setVisibility(View.GONE);
        }

        //Store the intent sent to us from a click on SearchActivity
        Intent intent = getIntent();

        //set values base on clicked item from search activity
        if(intent.getExtras() != null){
            String sneakerMainImage = intent.getStringExtra("sneakerImage");
            String sneakerFullName = intent.getStringExtra("sneakerName");
            String sneakerBrandName = intent.getStringExtra("sneakerBrand");
            String sneakerColourwayDetail = intent.getStringExtra("sneakerColourway");
            int sneakerPriceCents = (intent.getIntExtra("sneakerRetailPrice", 0)) / 100;
            String ReleaseDate = intent.getStringExtra("sneakerReleaseDate");
            String sneakerStoryFull = intent.getStringExtra("sneakerStory");
            sneakerId = intent.getIntExtra("sneakerId", -1);

            Picasso.get().load(sneakerMainImage).into(sneakerImage);
            sneakerName.setText(sneakerFullName);
            sneakerBrand.setText(sneakerBrandName);
            sneakerColourway.setText(sneakerColourwayDetail);
            sneakerRetailPrice.setText("$"+sneakerPriceCents);
            sneakerReleaseDate.setText(ReleaseDate);
            sneakerStory.setText(sneakerStoryFull);

            //checks if this sneaker exists in favourites table associated with the user logged in
            if((dataBaseHelper.isFavourite(sneakerId, userId))){
                heart.setLiked(true);
            }
            else{
                heart.setLiked(false);
            }

            if(sneakerColourwayDetail.length() > 23){
                colourway.setLines(2);
            }
            else if(sneakerColourwayDetail.length() > 23 && sneakerColourwayDetail.length() < 100)
            {
                colourway.setLines(3);
            }

        }

        heart.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                //execute query to insert

                //heart.setEnabled(true);

                FavouriteModel favouriteModel = new FavouriteModel(-1,userId, sneakerId);
                dataBaseHelper.addFavourite(favouriteModel);
//                ArrayList<SneakerModel> favourites = dataBaseHelper.getAllFavourites(userId);
//
//                for(SneakerModel s: favourites){
//                    Log.d("favourites", s.toString() + "");
//                }
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                dataBaseHelper.deleteFavourite(sneakerId, userId);
            }
        });

    }


}

