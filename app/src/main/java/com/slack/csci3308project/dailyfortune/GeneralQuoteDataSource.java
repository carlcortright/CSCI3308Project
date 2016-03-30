package com.slack.csci3308project.dailyfortune;

/**
 * Created by luke on 3/24/16.
 */


import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class GeneralQuoteDataSource {
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    private String[] allColumns = {DatabaseHelper.GENERAL_COLUMN_ID, DatabaseHelper.GENERAL_QUOTE, DatabaseHelper.GENERAL_AUTHOR};

    public GeneralQuoteDataSource(Context context){
        dbHelper = new DatabaseHelper(context);
    }

     public void open() throws SQLException{
         dbHelper.databaseExist();
         database = dbHelper.getReadableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

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

    private GeneralQuote cursorToQuote(Cursor cursor){
        GeneralQuote gQuote = new GeneralQuote();
        gQuote.setId(cursor.getInt(0));
        gQuote.setQuote(cursor.getString(1));
        gQuote.setAuthor(cursor.getString(2));
        return gQuote;
    }
}