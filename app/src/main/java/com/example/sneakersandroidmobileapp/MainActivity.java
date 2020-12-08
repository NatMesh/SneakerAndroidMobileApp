package com.example.sneakersandroidmobileapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DataBaseHelper dataBaseHelper;
    private GridView gridViewTrendingShoes;
    private SneakerAdapter sneakerAdapte;
    private ArrayList<SneakerModel> sneakerTrendingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parseJson();
        //Call our DataBaseHelper object
        dataBaseHelper = new DataBaseHelper(MainActivity.this);

        gridViewTrendingShoes = findViewById(R.id.gridViewTrendingShoes);

        sneakerTrendingList = dataBaseHelper.getTrendingSneakers();

        sneakerAdapte = new SneakerAdapter(MainActivity.this, sneakerTrendingList);

        gridViewTrendingShoes.setAdapter(sneakerAdapte);

        //click event for our grid view when an item is clicked
        gridViewTrendingShoes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Based on the item clicked we get the object so we can use that data on another activity
                SneakerModel selectedSneaker = sneakerTrendingList.get(i);

                Intent intent = new Intent(MainActivity.this, SneakerDetailsActivity.class);
                intent.putExtra("sneakerId", selectedSneaker.getId());
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

        ///////////////////////////////////////////////////////



        //declare and grab our bottom navigation view
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        //This will get the menu from our nav bar
        Menu menu = bottomNavigationView.getMenu();
        //from our bottom menu it will get the first element at index 0 from it aka our home buttom
        MenuItem menuItem = menu.getItem(0);
        //This will then set it to check giving it that highlighted effect of being selected
        menuItem.setChecked(true);


        //Create an event for our navigation items to tell which one is clicked and then execute some code dependant on the item
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //We use a switch statement to check which one is clicked
                switch (item.getItemId()){
                    case R.id.nav_home:
                        //
                        break;
                    case R.id.nav_favourites:
                        //This will navigate from our main activity aka home to our favourites activity when we click on the favourite
                        //heart button in the bottom nav
                        Intent intentFavourite = new Intent(MainActivity.this, FavouriteActivity.class);
                        startActivity(intentFavourite);
                        break;
                    case R.id.nav_search:
                        //This will navigate from our main activity aka home to our search activity when we click on the search
                        //magnifying glass button in the bottom nav
                        Intent intentSearch = new Intent(MainActivity.this, SearchActivity.class);
                        startActivity(intentSearch);
                        break;
                    case R.id.nav_news:
                        //This will navigate from our main activity aka home to our news activity when we click on the news
                        //trending button in the bottom nav
                        Intent intentNews = new Intent(MainActivity.this, NewsActivity.class);
                        startActivity(intentNews);
                        break;
                    case R.id.nav_profile:
                        //This will navigate from our main activity aka home to our profile activity when we click on the profile
                        //person button in the bottom nav
                        Intent intentProfile = new Intent(MainActivity.this, ProfileActivity.class);
                        startActivity(intentProfile);
                        break;
                }

                return false;
            }
        });
    }

    private void parseJson() {
        //We create a String to store our json
        String json;

        try{
            //grabs the inputstream we will be reading from
            InputStream inputStream = getAssets().open("GoatSneakers.json");
            //figures out the size of our document
            int size = inputStream.available();
            //we create an array of bytes based on the size of the file we are reading
            byte[] buffer = new byte[size];

            inputStream.read(buffer);
            inputStream.close();

            //
            json = new String(buffer, "UTF-8");

            //Creates a json object based on the data we read the local json file
            JSONObject jsonObject = new JSONObject(json);
            //grabs the array of key value pairs from our json object
            JSONArray jsonArray = jsonObject.getJSONArray("sneakers");


            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject sneaker = jsonArray.getJSONObject(i);
                SneakerModel sneakerModel = new SneakerModel();

                sneakerModel.setId(-1);
                sneakerModel.setBrand(sneaker.getString("brand_name"));
                sneakerModel.setCategory(sneaker.getJSONArray("category").get(0).toString());
                sneakerModel.setMainColour(sneaker.getString("color"));
                sneakerModel.setDesigner(sneaker.getString("designer"));
                sneakerModel.setColourWay(sneaker.getString("details"));
                sneakerModel.setGender(sneaker.getJSONArray("gender").get(0).toString());
                sneakerModel.setGridPicture(sneaker.getString("grid_picture_url"));
                sneakerModel.setMainPicture(sneaker.getString("main_picture_url"));;
                sneakerModel.setMidsole(sneaker.getString("midsole"));
                sneakerModel.setName(sneaker.getString("name"));
                sneakerModel.setNickName(sneaker.getString("nickname"));
                sneakerModel.setReleaseDate(sneaker.getString("release_date"));
                sneakerModel.setPriceCents(sneaker.getInt("retail_price_cents"));
                sneakerModel.setShoeStory(sneaker.getString("story_html"));
                sneakerModel.setUpperMaterial(sneaker.getString("upper_material"));

                //this will add all the sneakers from our json file to our SNEAKER_TABLE
                //dataBaseHelper.addSneaker(sneakerModel);
                Log.d("test sneaker", sneakerModel.getName() + "    " + jsonArray.length());
            }

        } catch (IOException e)
        {
            e.printStackTrace();
        } catch(JSONException e)
        {
            e.printStackTrace();
        }
    }


}