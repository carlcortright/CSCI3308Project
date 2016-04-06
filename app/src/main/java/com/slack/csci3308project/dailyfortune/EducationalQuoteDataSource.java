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

public class EducationalQuoteDataSource {
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    private String[] allColumns = {DatabaseHelper.EDUCATIONAL_COLUMN_ID, DatabaseHelper.EDUCATIONAL_QUOTE, DatabaseHelper.EDUCATIONAL_AUTHOR};

    public EducationalQuoteDataSource(Context context){
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException{
        dbHelper.databaseExist();
        database = dbHelper.getReadableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

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


    public static int randInt(int min, int max) {

        // Usually this can be a field rather than a method variable
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }


    private EducationalQuote cursorToQuote(Cursor cursor) throws SQLException {
        EducationalQuote eQuote = new EducationalQuote();
        eQuote.setId(cursor.getInt(0));
        eQuote.setQuote(cursor.getString(1));
        eQuote.setAuthor(cursor.getString(2));
        return eQuote;
    }
}
