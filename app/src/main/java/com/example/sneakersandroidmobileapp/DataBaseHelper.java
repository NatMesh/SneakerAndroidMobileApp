package com.example.sneakersandroidmobileapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
//Define static values for each column name in the database.

public class DataBaseHelper extends SQLiteOpenHelper {
    //Define static constant for each of our table names in the database.
    public static final String SNEAKER_TABLE = "SNEAKER_TABLE";
    //Define static constant for each of our column names in the database.
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_BRAND = "BRAND";
    public static final String COLUMN_CATEGORY = "CATEGORY";
    public static final String COLUMN_MAIN_COLOUR = "MAIN_COLOUR";
    public static final String COLUMN_DESIGNER = "DESIGNER";
    public static final String COLUMN_COLOUR_WAY = "COLOUR_WAY";
    public static final String COLUMN_GENDER = "GENDER";
    public static final String COLUMN_GRID_PICTURE = "GRID_PICTURE";
    public static final String COLUMN_MAIN_PICTURE = "MAIN_PICTURE";
    public static final String COLUMN_MIDSOLE = "MIDSOLE";
    public static final String COLUMN_NAME = "NAME";
    public static final String COLUMN_NICKNAME = "NICKNAME";
    public static final String COLUMN_RELEASE_DATE = "RELEASE_DATE";
    public static final String COLUMN_PRICE_CENTS = "PRICE_CENTS";
    public static final String COLUMN_SHOE_STORY = "SHOE_STORY";
    public static final String COLUMN_UPPER_MATERIAL = "UPPER_MATERIAL";


    //context: Context: to use for locating paths to the the database This value may be null.
    //name: String: of the database file, or null for an in-memory database This value may be null.
    //factory: 	SQLiteDatabase.CursorFactory: to use for creating cursor objects, or null for the default This value may be null.
    /*version: int: number of the database (starting at 1); if the database is older, onUpgrade(SQLiteDatabase, int, int) will be
      used to upgrade the database; if the database is newer, onDowngrade(SQLiteDatabase, int, int) will be used to downgrade the
      database
    */
    public DataBaseHelper(@Nullable Context context) {
        super(context, "sneakers.db", null, 1);
    }

    //This is called the first time a database is accessed. There should be code to create a new database.
    //The onCreate method is automatically called when the app requests or inputs new data. We need to create a new table inside this method.
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableStatement = "Create TABLE " + SNEAKER_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_BRAND + " STRING NOT NULL, " + COLUMN_CATEGORY + " STRING NOT NULL, " + COLUMN_MAIN_COLOUR + " STRING NOT NULL, " + COLUMN_DESIGNER + " STRING NOT NULL, " + COLUMN_COLOUR_WAY + " STRING NOT NULL, " + COLUMN_GENDER + " STRING NOT NULL, " +
                COLUMN_GRID_PICTURE + " TEXT NOT NULL, " + COLUMN_MAIN_PICTURE + " TEXT NOT NULL, " + COLUMN_MIDSOLE + " STRING NOT NULL, " + COLUMN_NAME + " STRING NOT NULL, " + COLUMN_NICKNAME + " STRING NOT NULL, " + COLUMN_RELEASE_DATE + " STRING NOT NULL, " +
                COLUMN_PRICE_CENTS + " INT NOT NULL, " + COLUMN_SHOE_STORY + " TEXT NOT NULL, " + COLUMN_UPPER_MATERIAL + " STRING NOT NULL)";
        sqLiteDatabase.execSQL(createTableStatement);
    }

    //This is called if the database version number changes. It prevents users apps from breaking when you change the database design.
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //Lets us insert a row into our SNEAKER_TABLE
    public boolean addSneaker(SneakerModel sneakerModel){
        //getWritableDatabase for insert/update/delete actions.
        //getReadableDatabase for select (read) actions.

        //grabs our one database(SNEAKER_TABLE) and lets us write to it
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        //Content values stores data in pairs acts like a key-value pair in json
        //example:
            //contentValues.put("name", value)
            //contentValue.getString("name)
        //creates ContentValues object
        ContentValues contentValues = new ContentValues();
        //populates object with key value pairs
        contentValues.put(COLUMN_BRAND, sneakerModel.getBrand());
        contentValues.put(COLUMN_CATEGORY, sneakerModel.getCategory());
        contentValues.put(COLUMN_MAIN_COLOUR, sneakerModel.getMainColour());
        contentValues.put(COLUMN_DESIGNER, sneakerModel.getDesigner());
        contentValues.put(COLUMN_COLOUR_WAY, sneakerModel.getColourWay());
        contentValues.put(COLUMN_GENDER, sneakerModel.getGender());
        contentValues.put(COLUMN_GRID_PICTURE, sneakerModel.getGridPicture());
        contentValues.put(COLUMN_BRAND, sneakerModel.getBrand());
        contentValues.put(COLUMN_MAIN_PICTURE, sneakerModel.getMainPicture());
        contentValues.put(COLUMN_MIDSOLE, sneakerModel.getMidsole());
        contentValues.put(COLUMN_NAME, sneakerModel.getName());
        contentValues.put(COLUMN_NICKNAME, sneakerModel.getNickName());
        contentValues.put(COLUMN_RELEASE_DATE, sneakerModel.getReleaseDate());
        contentValues.put(COLUMN_PRICE_CENTS, sneakerModel.getPriceCents());
        contentValues.put(COLUMN_SHOE_STORY, sneakerModel.getShoeStory());
        contentValues.put(COLUMN_UPPER_MATERIAL, sneakerModel.getUpperMaterial());

        //creates insert statement for our SNEAKER_TABLE
        //params:
            //table: String: the table to insert the row into
            /*nullColumnHack: String: optional; may be null. SQL doesn't allow inserting a completely empty
                              row without naming at least one column name. If your provided values is empty,
                              no column names are known and an empty row can't be inserted. If not set to null,
                              the nullColumnHack parameter provides the name of nullable column name to explicitly
                              insert a NULL into in the case where your values is empty.
            */
        //values: ContentValues: this map contains the initial column values for the row. The keys should be the column names and the values the column values
        //insert returns a long value with -1 meaning transaction was unsuccessful and 0 or positive number means successful transaction
        long insert = sqLiteDatabase.insert(SNEAKER_TABLE, null, contentValues);

        //return true or false based on success of insert
        if (insert == -1){
            return false;
        }
        else {
            return true;
        }
    }

    //gets all the records from our Sneakers table and returns it as a list for us to do as we please(display most likely)
    public ArrayList<SneakerModel> getAllSneakers(){
        ArrayList<SneakerModel> returnList = new ArrayList<>();

        //our query string
        String queryString = "SELECT * FROM " + SNEAKER_TABLE;

        //NOTE: getWritableDatabase locks the data file so other processes may not access it.
        //use getWritableDatabase only when you plan to insert, update, or delete records.

        //use getReadableDatabase when you plan to SELECT items from the database.
        SQLiteDatabase db = this.getReadableDatabase();

        //A Cursor is the result set from a SQL statement
        Cursor cursor = db.rawQuery(queryString, null);

        //checks if we got results
            //cursor.moveToFirst returns a true if there were items selected and false if no items were selected
        if(cursor.moveToFirst()){
            //loop through the cursor (result set) and create new sneaker objects. Put them into the return list.
            do{
                //stores the id number for our sneaker
                //it takes in a column index which is the order of the column it is pulling from
                int sneakerID = cursor.getInt(0);
                String sneakerBrand = cursor.getString(1);
                String sneakerCategory = cursor.getString(2);
                String sneakerMainColour = cursor.getString(3);
                String sneakerDesigner = cursor.getString(4);
                String sneakerColourWay = cursor.getString(5);
                String sneakerGender = cursor.getString(6);
                String sneakerGridPicture = cursor.getString(7);
                String sneakerMainPicture = cursor.getString(8);
                String sneakerMidsole = cursor.getString(9);
                String sneakerName = cursor.getString(10);
                String sneakerNickname = cursor.getString(11);
                String sneakerReleaseDate = cursor.getString(12);
                int sneakerPriceCents = cursor.getInt(13);
                String sneakerShoeStory = cursor.getString(14);
                String sneakerUpperMaterial = cursor.getString(15);

                SneakerModel sneakerModel = new SneakerModel(sneakerID, sneakerBrand, sneakerCategory, sneakerMainColour, sneakerDesigner, sneakerColourWay, sneakerGender,
                        sneakerGridPicture, sneakerMainPicture, sneakerMidsole, sneakerName, sneakerNickname, sneakerReleaseDate, sneakerPriceCents, sneakerShoeStory,
                        sneakerUpperMaterial);
                returnList.add(sneakerModel);

            } while(cursor.moveToNext());
        }
        else
        {
            //failure. do not add anything to the list.
        }

        //when finished writing or reading to the db make sure to close the cursor and the db when finished.
        cursor.close();
        db.close();
        return returnList;
    }

    //Deletes a record from our sneaker database
    //This method uses a DAO style. Data Access Object(DAO) is considered a best-practice in software design.
    public boolean deleteSneaker(SneakerModel sneakerModel){
        //find sneakerModel in the database. if it is found, delete it and return true.
        //if not found, return false
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + SNEAKER_TABLE + " WHERE " + COLUMN_ID + " = " + sneakerModel.getId();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()){
            return true;
        } else{
            return false;
        }
    }

    //DELETE ALL RECORDS from SNEAKER_TABLE
    public boolean deleteAllSneaker(){
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + SNEAKER_TABLE;

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()){
            return true;
        } else{
            return false;
        }
    }

    //Create a method to search based on object id mainly for the click purpose on our gridview

    //Create a method to work with a search bar and use a LIKE query to find sneakers based on user input
}
