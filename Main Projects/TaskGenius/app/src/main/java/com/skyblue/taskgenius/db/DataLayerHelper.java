package com.skyblue.taskgenius.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataLayerHelper extends SQLiteOpenHelper {

    public DataLayerHelper(Context context) {
        super(context, "TaskGenius", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE 'Task' (" +
                "'id' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE," +
                "'title' TEXT," +
                "'description' TEXT," +
                "'remind' TEXT," +
                "'date' TEXT," +
                "'time' TEXT" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
