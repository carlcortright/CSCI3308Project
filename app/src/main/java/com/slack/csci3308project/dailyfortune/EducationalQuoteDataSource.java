package com.slack.csci3308project.dailyfortune;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * The source for our Educational Quotes
 */
public class EducationalQuoteDataSource {
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    private String[] allColumns = {DatabaseHelper.EDUCATIONAL_COLUMN_ID, DatabaseHelper.EDUCATIONAL_QUOTE, DatabaseHelper.EDUCATIONAL_AUTHOR};

    /**
     * Construct this class by setting the construct to context.
     * @param context The context of the main application.
     */
    public EducationalQuoteDataSource(Context context){
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
    public List<EducationalQuote> getAllEducationalQuotes(){
        List<EducationalQuote> educationalQuotes = new ArrayList<EducationalQuote>();

        Cursor cursor = database.query(DatabaseHelper.TABLE_EDUCATIONAL, allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            EducationalQuote eQuote = cursorToQuote(cursor);
            educationalQuotes.add(eQuote);
            cursor.moveToNext();
        }
        cursor.close();
        return educationalQuotes;

    }

    /**
     * @return A random EducationalQuote
     */
    public EducationalQuote getRandomEducationalQuote(){
        EducationalQuote eQuote = new EducationalQuote();

        Cursor cursor = database.query(DatabaseHelper.TABLE_EDUCATIONAL, allColumns, null, null, null, null, null);

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
    private EducationalQuote cursorToQuote(Cursor cursor) throws SQLException {
        EducationalQuote eQuote = new EducationalQuote();
        eQuote.setId(cursor.getInt(0));
        eQuote.setQuote(cursor.getString(1));
        eQuote.setAuthor(cursor.getString(2));
        return eQuote;
    }
}
