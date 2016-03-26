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

    private EducationalQuote cursorToQuote(Cursor cursor){
        EducationalQuote eQuote = new EducationalQuote();
        eQuote.setId(cursor.getInt(0));
        eQuote.setQuote(cursor.getString(1));
        eQuote.setAuthor(cursor.getString(2));
        return eQuote;
    }
}
