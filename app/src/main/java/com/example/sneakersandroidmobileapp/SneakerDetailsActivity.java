package com.example.sneakersandroidmobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class SneakerDetailsActivity extends AppCompatActivity {

    ImageView sneakerImage;
    TextView sneakerName;
    TextView sneakerBrand;
    TextView sneakerColourway;
    TextView sneakerRetailPrice;
    TextView sneakerReleaseDate;
    TextView sneakerStory;

    TextView colourway;

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

        colourway = findViewById(R.id.colourway);

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

            Picasso.get().load(sneakerMainImage).into(sneakerImage);
            sneakerName.setText(sneakerFullName);
            sneakerBrand.setText(sneakerBrandName);
            sneakerColourway.setText(sneakerColourwayDetail);
            sneakerRetailPrice.setText("$"+sneakerPriceCents);
            sneakerReleaseDate.setText(ReleaseDate);
            sneakerStory.setText(sneakerStoryFull);

            if(sneakerColourwayDetail.length() > 23){
                colourway.setLines(2);
            }
            else if(sneakerColourwayDetail.length() > 23 && sneakerColourwayDetail.length() < 100)
            {
                colourway.setLines(3);
            }

        }

    }
}

