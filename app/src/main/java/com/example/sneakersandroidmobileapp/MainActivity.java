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
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //private DataBaseHelper dataBaseHelper;
    //private GridView sneakerGridview;
    //private RecyclerView mRecyclerView;
    //private SneakerAdapter sneakerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Call our DataBaseHelper object
        //dataBaseHelper = new DataBaseHelper(MainActivity.this);

        //instantiates our gridview
        //sneakerGridview = findViewById(R.id.sneakerGridview);

        //sneakerAdapter = new SneakerAdapter(MainActivity.this, dataBaseHelper.getAllSneakers());

        //sneakerGridview.setAdapter(sneakerAdapter);

        //This method parses our local json file and adds the objects to our sneakerList collection object
        parseJson();


        //Declare SneakerModel Object, NOTE TO SET ID TO -1 AND UNDERSTAND THAT ID WONT BE PERSISTED FROM OUR INSTANTIATION HERE BUT FROM OUR SQLITEDBHELPER
//        SneakerModel sneakerModel = new SneakerModel();

//        try{
//            //set instance var for our sneakerModel object
//            sneakerModel.setId(-1);
//            sneakerModel.setBrand("Nike");
//            sneakerModel.setCategory("Basketball");
//            sneakerModel.setMainColour("Black");
//            sneakerModel.setDesigner("Jason Petrie");
//            sneakerModel.setColourWay("Black/White-Gold");
//            sneakerModel.setGender("men");
//            sneakerModel.setGridPicture("https://image.goat.com/240/attachments/product_template_pictures/images/010/439/995/original/897648_007_101.png.png");
//            sneakerModel.setMainPicture("https://image.goat.com/crop/750/attachments/product_template_pictures/images/010/439/995/original/897648_007_101.png.png");;
//            sneakerModel.setMidsole("Air");
//            sneakerModel.setName("LeBron 15 'Equality' PE");
//            sneakerModel.setNickName("Equality");
//            sneakerModel.setReleaseDate("2018-03-03");
//            sneakerModel.setPriceCents(200000);
//            sneakerModel.setShoeStory("The LeBron 15 ‘Equality’ PE was worn by LeBron James’ during key games in the 2018 NBA season. As a special release benefiting " +
//                    "the Smithsonian National Museum of African American History and Culture, 400 pairs were distributed through a draw system (200 in black colorway, " +
//                    "200 in white colorway). This pack combines both EQUALITY shoes together, a strong message from Nike’s ‘Equality’ campaign to promote fairness and " +
//                    "respect they see in sport and translate them off the field.");
//            sneakerModel.setUpperMaterial("");
//
//            //Creates toast object for testing REMOVE LATER
//            Toast.makeText(MainActivity.this, "Object was successfully created", Toast.LENGTH_LONG).show();
//        } catch (Exception e){
//            Toast.makeText(MainActivity.this, "error in initializing sneakerModel object", Toast.LENGTH_LONG).show();
//        }




        //we try the insert method we created addSneaker() which returns a boolean for whether it was successful or not
        //boolean success = dataBaseHelper.addSneaker(sneakerModel);

        //tells us via toast
        //Toast.makeText(MainActivity.this, "Sucess= " + success, Toast.LENGTH_LONG).show();

        //shows us via toast all the sneakers in our db
        //Toast.makeText(MainActivity.this, allSneakers.toString(), Toast.LENGTH_SHORT).show();
        //sneakerArrayAdapter = new ArrayAdapter<SneakerModel>(MainActivity.this, android.R.layout.simple_list_item_1, dataBaseHelper.getAllSneakers());


//        lvSneakers.setAdapter(sneakerArrayAdapter);
//
//        //defines our click event for the listview
//        lvSneakers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                //we find the specific object clicked and store it
//                SneakerModel clickedSneaker = (SneakerModel) parent.getItemAtPosition(position);
//                //Once we have our chosen object to delete we can call our delete method
//                dataBaseHelper.deleteSneaker(clickedSneaker);
//                Toast.makeText(MainActivity.this, "Deleted " + clickedSneaker.getName(), Toast.LENGTH_SHORT).show();
//
//            }
//        });

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