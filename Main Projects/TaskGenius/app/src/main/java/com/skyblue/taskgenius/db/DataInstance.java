package com.skyblue.taskgenius.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DataInstance {

    private SQLiteDatabase db;
    private DataLayerHelper dbHelper;

    public DataInstance(Context context) {
        dbHelper = new DataLayerHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public SQLiteDatabase getDb() {
        return db;
    }
}
