package com.example.sneakersandroidmobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //declare and grab our bottom navigation view
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        //This will get the menu from our nav bar
        Menu menu = bottomNavigationView.getMenu();
        //from our bottom menu it will get the fifth element at index 4 from it aka our profile buttom
        MenuItem menuItem = menu.getItem(4);
        //This will then set it to check giving it that highlighted effect of being selected
        menuItem.setChecked(true);

        //Create an event for our navigation items to tell which one is clicked and then execute some code dependant on the item
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //We use a switch statement to check which one is clicked
                switch (item.getItemId()){
                    case R.id.nav_home:
                        //This will navigate from our profile activity to our home activity when we click on the home
                        //house button in the bottom nav
                        Intent intentHome = new Intent(ProfileActivity.this, MainActivity.class);
                        startActivity(intentHome);
                        break;
                    case R.id.nav_favourites:
                        //This will navigate from our profile activity to our favourites activity when we click on the favourite
                        //heart button in the bottom nav
                        Intent intentFavourite = new Intent(ProfileActivity.this, FavouriteActivity.class);
                        startActivity(intentFavourite);
                        break;
                    case R.id.nav_search:
                        //This will navigate from our profile activity to our search activity when we click on the search
                        //magnifying glass button in the bottom nav
                        Intent intentSearch = new Intent(ProfileActivity.this, SearchActivity.class);
                        startActivity(intentSearch);
                        break;
                    case R.id.nav_news:
                        //This will navigate from our profile activity to our news activity when we click on the news
                        //trending button in the bottom nav
                        Intent intentNews = new Intent(ProfileActivity.this, NewsActivity.class);
                        startActivity(intentNews);
                        break;
                    case R.id.nav_profile:
//                        //This will navigate from our news activity to our profile activity when we click on the profile
//                        //person button in the bottom nav
//                        Intent intentProfile = new Intent(NewsActivity.this, ProfileActivity.class);
//                        startActivity(intentProfile);
                        break;
                }

                return false;
            }
        });
    }

}
