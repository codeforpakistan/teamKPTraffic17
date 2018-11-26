package com.kptrafficpolice.trafficapp.fragments.ETest.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Belal on 1/27/2017.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    //Constants for Database scoresModel, table scoresModel, and column names

    public static final String DB_NAME = "ScoresDB";
    public static final String TABLE_NAME = "all_scores";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_Score = "score";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_USER_ID = "user_id";

    //database version
    private static final int DB_VERSION = 1;

    //Constructor
    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    //creating the database
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME
                + "(" + COLUMN_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_Score +
                " VARCHAR, " + COLUMN_DATE +
        " VARCHAR, " + COLUMN_USER_ID + " INTEGER);";
        db.execSQL(sql);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }


    public boolean addName(String name, String date, int userid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_Score, name);
        contentValues.put(COLUMN_DATE, date);
        contentValues.put(COLUMN_USER_ID, userid);

        db.insert(TABLE_NAME, null, contentValues);
        db.close();
        return true;
    }


    /*
    * this method will give us all the scoresModel stored in sqlite
    * */
    public Cursor getNames(int id) {

        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_USER_ID + " = "+id+" ;";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }

}
