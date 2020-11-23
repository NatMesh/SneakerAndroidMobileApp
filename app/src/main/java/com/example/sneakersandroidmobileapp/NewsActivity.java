package com.example.sneakersandroidmobileapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class NewsActivity extends AppCompatActivity {
    //ArrayList to store our NewsItems for sneaker news
    ArrayList<NewsItem> newsItems;

    //declare our controls
    private RecyclerView newsRecyclerView;
    private RecyclerView.Adapter newsAdapter; //This is the bridge between our data and the recycler view.
    private RecyclerView.LayoutManager newsLayoutManager; //aligns the items from our arraylist on the layout

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        newsItems = new ArrayList<>();

        //creates an anon object for RssTask for it to execute and run our AsyncTask to gather RSSFEED data
        new RssTask(){}.execute();

        //declare and grab our bottom navigation view
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        //This will get the menu from our nav bar
        Menu menu = bottomNavigationView.getMenu();
        //from our bottom menu it will get the fourth element at index 3 from it aka our news buttom
        MenuItem menuItem = menu.getItem(3);
        //This will then set it to check giving it that highlighted effect of being selected
        menuItem.setChecked(true);

        //Create an event for our navigation items to tell which one is clicked and then execute some code dependant on the item
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //We use a switch statement to check which one is clicked
                switch (item.getItemId()){
                    case R.id.nav_home:
                        //This will navigate from our news activity to our home activity when we click on the home
                        //house button in the bottom nav
                        Intent intentHome = new Intent(NewsActivity.this, MainActivity.class);
                        startActivity(intentHome);
                        break;
                    case R.id.nav_favourites:
                        //This will navigate from our news activity to our favourites activity when we click on the favourite
                        //heart button in the bottom nav
                        Intent intentFavourite = new Intent(NewsActivity.this, FavouriteActivity.class);
                        startActivity(intentFavourite);
                        break;
                    case R.id.nav_search:
                        //This will navigate from our news activity to our search activity when we click on the search
                        //magnifying glass button in the bottom nav
                        Intent intentSearch = new Intent(NewsActivity.this, SearchActivity.class);
                        startActivity(intentSearch);
                        break;
                    case R.id.nav_news:
                        //NOTE MIGHT WANT TO UNCOMMENT IN ORDER FOR USERS TO BE ABLE TO REFRESH RSS FEED
//                        //This will navigate from our news activity to our news activity when we click on the news
//                        //trending button in the bottom nav
//                        Intent intentNews = new Intent(NewsActivity.this, NewsActivity.class);
//                        startActivity(intentNews);
                        break;
                    case R.id.nav_profile:
                        //This will navigate from our news activity to our profile activity when we click on the profile
                        //person button in the bottom nav
                        Intent intentProfile = new Intent(NewsActivity.this, ProfileActivity.class);
                        startActivity(intentProfile);
                        break;
                }

                return false;
            }
        });
    }

    //nested multi-threading (asynchronous processing) example
    class RssTask extends AsyncTask {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d("NM", "onPreExecute");
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            Log.d("NM", "onPostExecute");
            //STILL NEEDS IMPLEMENTING!!!!
            newsRecyclerView = findViewById(R.id.recyclerView);
            newsRecyclerView.setHasFixedSize(true);
            newsLayoutManager = new LinearLayoutManager(NewsActivity.this);
            newsAdapter = new NewsAdapter(newsItems);

            newsRecyclerView.setLayoutManager(newsLayoutManager);
            newsRecyclerView.setAdapter(newsAdapter);

            //This is for testing to see if data populated our arraylist
            for(NewsItem newsItem : newsItems){
                Log.d("test", "test " + newsItem);
            }
        }

        //the main method of the async task - does not have access to the UI thread
        //this is what happens in the background
        @Override
        protected Object doInBackground(Object[] objects) {
            Log.d("NM", "doInBackground");

            //11th
            //2nd
            //Use SAXParser to parse sneaker news feed
            //The factory is used to create the parser
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            SAXParser saxParser = null;
            try {
                saxParser = saxParserFactory.newSAXParser();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            }

            //3rd
            URL url = null;
            try {
                url = new URL("https://solecollector.com/feeds/generator/e/r/1.rss");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            //4th
            //open the HTTP connection to the web server (i.e. HTTP GET request)
            //creates a client server connection from our android app(client) to the server
            InputStream inputStream = null;
            try {
                inputStream = url.openStream();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //5th
            SneakerNewsHandler sneakerNewsHandler = new SneakerNewsHandler();
            try {
                saxParser.parse(inputStream, sneakerNewsHandler);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    //This class will handle RSS data from solecollectors RSS feed
    //since all the xml items are structured the same I created a custom class to create objects for them
    class SneakerNewsHandler extends DefaultHandler{
        //Declare our NewsItem object
        NewsItem newsItem;

        //flags to keep track of what element we are in
        boolean inItem, inTitle, inDescription, inPubDate, inLink;

        //StringBuilder to build strings in the characters() event function
        //that will be used to set xml data to our instance variables
        StringBuilder stringBuilder;

        //Stores our imageUrl
        String imageUrl;

        //event triggers at the start of the RRS feed and instantiates our ArrayList of NewsItem
        @Override
        public void startDocument() throws SAXException {
            super.startDocument();
            Log.d("NM", "startDocument()");
            newsItems = new ArrayList<NewsItem>();
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);
            Log.d("NM", "startElement() " + qName);
            //Determine what element you are in
            if (qName.equals("item")) {
                inItem = true;
                //create a new NewsItem object when we have found an item tag
                newsItem = new NewsItem();
            } else if (inItem && qName.equals("media:content")){
                //since our image url is stored as an attribute we need to call attributes which is a collection of attributes in our xml element
                //then use .getValue() and pass it the attribute name to get the image link we desire
                imageUrl = attributes.getValue("url");
                //since our image is given to us in an XML attribute we can simple just set it here once we get it
                newsItem.setImage(imageUrl);
            } else if (inItem && qName.equals("title")) {
                inTitle = true;
                stringBuilder = new StringBuilder();
            } else if (inItem && qName.equals("description")) {
                inDescription = true;
                stringBuilder = new StringBuilder();
            } else if (inItem && qName.equals("link")) {
                inLink = true;
                stringBuilder = new StringBuilder();
            } else if (inItem && qName.equals("pubDate")) {
                inPubDate = true;
                stringBuilder = new StringBuilder();
            }
        }

        //CONTINUE IMPLEMENTING characters method
        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            super.characters(ch, start, length);
            String s = new String(ch, start, length);
            Log.d("NM", "characters(): " + s);
            //for our string image url pulled from the attribute
            //Log.d("NM", "imageUrl: " + imageUrl);

            //parse date we want based on boolean flags
            if (inTitle && inItem) {
                //build our title string and set it to our NewsItem object
                String title = stringBuilder.append(ch, start, length).toString();
                newsItem.setTitle(title);
            } else if (inDescription && inItem) {
                //build our description string and set it to our NewsItem object
                String description = stringBuilder.append(ch, start, length).toString();
                newsItem.setDescription(description);
            } else if (inLink && inItem) {
                //build our description string and set it to our NewsItem object
                String link = stringBuilder.append(ch, start, length).toString();
                newsItem.setLink(link);
                Log.d("ABC", newsItem.getLink());
            } else if (inPubDate && inItem) {
                //build our description string and set it to our NewsItem object
                String pubDate = stringBuilder.append(ch, start, length).toString();
                newsItem.setPubDate(pubDate);
                Log.d("pubDate", newsItem.getPubDate());
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            super.endElement(uri, localName, qName);
            Log.d("NM", "endElement(): " + qName);
            if (qName.equals("item")) {
                inItem = false;
                //add the NewsItem object to ArrayList<NewsItems> at the end of the item element
                newsItems.add(newsItem);
            } else if (inItem && qName.equals("title")) {
                inTitle = false;
            } else if (inItem && qName.equals("description")) {
                inDescription = false;
            } else if (inItem && qName.equals("link")) {
                inLink = false;
            } else if (inItem && qName.equals("pubDate")) {
                inPubDate = false;
            }
        }

        //Event triggers at the end of the RSS feed
        @Override
        public void endDocument() throws SAXException {
            super.endDocument();
            Log.d("NM", "endDocument()");
            //set to be used as a breakpoint for testing
            int i = 0;

        }
    }
}
