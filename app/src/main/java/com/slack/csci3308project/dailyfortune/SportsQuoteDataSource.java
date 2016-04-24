package com.slack.csci3308project.dailyfortune;

/**
 * Created by luke on 3/24/16.
 */


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class SportsQuoteDataSource {
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    private String[] allColumns = {DatabaseHelper.SPORTS_COLUMN_ID, DatabaseHelper.SPORTS_QUOTE, DatabaseHelper.SPORTS_AUTHOR};

    /**
     * Construct this class by setting the construct to context.
     * @param context The context of the main application.
     */
    public SportsQuoteDataSource(Context context){
        dbHelper = new DatabaseHelper(context);
    }

    /**
     * Open a connection to the database if it exists
     * @throws SQLException If the database cannot be opened
     */
    public void open() throws SQLException{
        dbHelper.databaseExist();
        database = dbHelper.getReadableDatabase();
    }

    /**
     * Close the database
     */
    public void close(){
        dbHelper.close();
    }

    /**
     * @return A List containing EducationalQuotes for all of our educational quotes
     */
    public List<SportsQuote> getAllSportsQuotes(){
        List<SportsQuote> sportsQuotes = new ArrayList<SportsQuote>();

        Cursor cursor = database.query(DatabaseHelper.TABLE_SPORTS, allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            SportsQuote sQuote = cursorToQuote(cursor);
            sportsQuotes.add(sQuote);
            cursor.moveToNext();
        }
        cursor.close();
        return sportsQuotes;

    }

    /**
     * @return A random SportsQuote
     */
    public SportsQuote getRandomSportsQuote(){
        SportsQuote sQuote = new SportsQuote();

        Cursor cursor = database.query(DatabaseHelper.TABLE_SPORTS, allColumns, null, null, null, null, null);

        int max = cursor.getCount() - 1;

        int randNumber = randInt(1, max);

        int counter = 0;
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            if (counter == randNumber){
                sQuote = cursorToQuote(cursor);
            }
            cursor.moveToNext();
            counter++;
        }
        return sQuote;
    }

    /**
     * Return a random number between min and max
     * @param min the min number possible to return
     * @param max the max number possible to return
     * @return A number between min and max.
     */
    public static int randInt(int min, int max) {

        // Usually this can be a field rather than a method variable
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    /**
     * Set the cursor to a new quote.
     * @param cursor the cursor to set
     * @return The quote the cursor is at
     * @throws SQLException If we cannot get the quote
     */
    private SportsQuote cursorToQuote(Cursor cursor){
        SportsQuote sQuote = new SportsQuote();
        sQuote.setId(cursor.getInt(0));
        sQuote.setQuote(cursor.getString(1));
        sQuote.setAuthor(cursor.getString(2));
        return sQuote;
    }
}
