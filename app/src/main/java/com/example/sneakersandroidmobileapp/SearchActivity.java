package com.example.sneakersandroidmobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private DataBaseHelper dataBaseHelper;
    private GridView sneakerGridview;
    //private RecyclerView mRecyclerView;
    private ArrayList<SneakerModel> sneakerList;
    private SneakerAdapter sneakerAdapter;
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //Call our DataBaseHelper object
        dataBaseHelper = new DataBaseHelper(SearchActivity.this);

        //instantiates our gridview
        sneakerGridview = findViewById(R.id.sneakerGridview);

        //set our list of sneakers based on what is in our database.
        sneakerList = dataBaseHelper.getAllSneakers();

        //initializes our custom array adapter class with our context set to this activity and an array list which fetches all
        //records from our sneakers table.
        sneakerAdapter = new SneakerAdapter(SearchActivity.this, sneakerList);

        //This sets our gridview to display the data pulled from
        sneakerGridview.setAdapter(sneakerAdapter);

        //click event for our grid view when an item is clicked
        sneakerGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Based on the item clicked we get the object so we can use that data on another activity
                SneakerModel selectedSneaker = sneakerList.get(i);

                Intent intent = new Intent(SearchActivity.this, SneakerDetailsActivity.class);
                intent.putExtra("sneakerImage", selectedSneaker.getMainPicture());
                intent.putExtra("sneakerName", selectedSneaker.getName());
                intent.putExtra("sneakerBrand", selectedSneaker.getBrand());
                intent.putExtra("sneakerColourway", selectedSneaker.getColourWay());
                intent.putExtra("sneakerRetailPrice", selectedSneaker.getPriceCents());
                intent.putExtra("sneakerReleaseDate", selectedSneaker.getReleaseDate());
                intent.putExtra("sneakerStory", selectedSneaker.getShoeStory());
                startActivity(intent);
            }
        });


        //declare and grab our bottom navigation view
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        //This will get the menu from our nav bar
        Menu menu = bottomNavigationView.getMenu();
        //from our bottom menu it will get the third element at index 2 from it aka our search buttom
        MenuItem menuItem = menu.getItem(2);
        //This will then set it to check giving it that highlighted effect of being selected
        menuItem.setChecked(true);

        //Create an event for our navigation items to tell which one is clicked and then execute some code dependant on the item
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //We use a switch statement to check which one is clicked
                switch (item.getItemId()){
                    case R.id.nav_home:
                        //This will navigate from our search activity to our home activity when we click on the home
                        //house button in the bottom nav
                        Intent intentHome = new Intent(SearchActivity.this, MainActivity.class);
                        startActivity(intentHome);
                        break;
                    case R.id.nav_favourites:
                        //This will navigate from our search activity to our favourites activity when we click on the favourite
                        //heart button in the bottom nav
                        Intent intentFavourite = new Intent(SearchActivity.this, FavouriteActivity.class);
                        startActivity(intentFavourite);
                        break;
                    case R.id.nav_search:
//                        //This will navigate from our profile activity to our search activity when we click on the search
//                        //magnifying glass button in the bottom nav
//                        Intent intentSearch = new Intent(ProfileActivity.this, SearchActivity.class);
//                        startActivity(intentSearch);
                        break;
                    case R.id.nav_news:
                        //This will navigate from our search activity to our news activity when we click on the news
                        //trending button in the bottom nav
                        Intent intentNews = new Intent(SearchActivity.this, NewsActivity.class);
                        startActivity(intentNews);
                        break;
                    case R.id.nav_profile:
                        //This will navigate from our search activity to our profile activity when we click on the profile
                        //person button in the bottom nav
                        Intent intentProfile = new Intent(SearchActivity.this, ProfileActivity.class);
                        startActivity(intentProfile);
                        break;
                }

                return false;
            }
        });
    }
}
