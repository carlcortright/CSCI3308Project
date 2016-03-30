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

    public SportsQuoteDataSource(Context context){
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException{
        dbHelper.databaseExist();
        database = dbHelper.getReadableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

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

    public SportsQuote getRandomSportsQuote(){
        SportsQuote eQuote = new SportsQuote();

        Cursor cursor = database.query(DatabaseHelper.TABLE_SPORTS, allColumns, null, null, null, null, null);

        int max = cursor.getCount() - 1;

        int randNumber = randInt(1, max);

        int counter = 0;
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            if (counter == randNumber){
                eQuote = cursorToQuote(cursor);
            }
            cursor.moveToNext();
            counter++;
        }
        return eQuote;
    }

    public static int randInt(int min, int max) {

        // Usually this can be a field rather than a method variable
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    private SportsQuote cursorToQuote(Cursor cursor){
        SportsQuote sQuote = new SportsQuote();
        sQuote.setId(cursor.getInt(0));
        sQuote.setQuote(cursor.getString(1));
        sQuote.setAuthor(cursor.getString(2));
        return sQuote;
    }
}
