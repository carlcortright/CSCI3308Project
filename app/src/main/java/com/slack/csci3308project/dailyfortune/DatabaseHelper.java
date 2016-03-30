package com.slack.csci3308project.dailyfortune;


import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String TABLE_GENERAL = "general";
    public static final String GENERAL_COLUMN_ID = "_id";
    public static final String GENERAL_QUOTE = "quote";
    public static final String GENERAL_AUTHOR = "author";

    public static final String TABLE_SPORTS = "sports";
    public static final String SPORTS_COLUMN_ID = "_id";
    public static final String SPORTS_QUOTE = "quote";
    public static final String SPORTS_AUTHOR = "author";

    public static final String TABLE_EDUCATIONAL = "educational";
    public static final String EDUCATIONAL_COLUMN_ID = "_id";
    public static final String EDUCATIONAL_QUOTE = "quote";
    public static final String EDUCATIONAL_AUTHOR = "author";

    private static String DB_PATH = "data/data/com.slack.csci3308project.dailyfortune/database";
    private static String DB_NAME = "quotes.db";
    private SQLiteDatabase myDatabase;
    private final Context myContext;


    public DatabaseHelper(Context context){
        super(context, DB_NAME, null, 1);
        this.myContext = context;
    }

    private void copyDatabase(File dbFile) throws IOException{
        InputStream is = myContext.getAssets().open(DB_NAME);
        OutputStream os = new FileOutputStream(dbFile);

        byte[] buffer = new byte[1024];
        while (is.read(buffer) > 0){
            os.write(buffer);
        }

        os.flush();
        os.close();
        is.close();
    }

    public void databaseExist() {
        File dbFile = myContext.getDatabasePath(DB_NAME);
       // dbFile.delete();
        if (!dbFile.exists()){
            try {
                copyDatabase(dbFile);
            } catch (IOException e){
                throw new RuntimeException("Error creating source database", e);
            }
        }


    }
    @Override
    public synchronized void close(){
        if (myDatabase != null){
            myDatabase.close();
        }

        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db){

    }

    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion){

    }
}
