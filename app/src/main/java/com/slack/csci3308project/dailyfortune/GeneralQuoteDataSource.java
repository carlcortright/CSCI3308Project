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

public class GeneralQuoteDataSource {
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    private String[] allColumns = {DatabaseHelper.GENERAL_COLUMN_ID, DatabaseHelper.GENERAL_QUOTE, DatabaseHelper.GENERAL_AUTHOR};

    /**
     * Construct this class by setting the construct to context.
     * @param context The context of the main application.
     */
    public GeneralQuoteDataSource(Context context){
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
    public List<GeneralQuote> getAllGeneralQuotes(){
        List<GeneralQuote> generalQuotes = new ArrayList<GeneralQuote>();

        Cursor cursor = database.query(DatabaseHelper.TABLE_GENERAL, allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            GeneralQuote gQuote = cursorToQuote(cursor);
            generalQuotes.add(gQuote);
            cursor.moveToNext();
        }
        cursor.close();
        return generalQuotes;

    }

    /**
     * @return A random General quote
     */
    public GeneralQuote getRandomGeneralQuote(){
        GeneralQuote gQuote = new GeneralQuote();

        Cursor cursor = database.query(DatabaseHelper.TABLE_GENERAL, allColumns, null, null, null, null, null);

        int max = cursor.getCount() - 1;

        int randNumber = randInt(1, max);

        int counter = 0;
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            if (counter == randNumber){
                gQuote = cursorToQuote(cursor);
            }
            cursor.moveToNext();
            counter++;
        }
        return gQuote;
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
    private GeneralQuote cursorToQuote(Cursor cursor){
        GeneralQuote gQuote = new GeneralQuote();
        gQuote.setId(cursor.getInt(0));
        gQuote.setQuote(cursor.getString(1));
        gQuote.setAuthor(cursor.getString(2));
        return gQuote;
    }
}
