package com.skyblue.taskgenius.common;

import android.content.ContentValues;

import com.skyblue.taskgenius.model.interfaces.IData;

public class Task extends TaskAbstract implements IData {

    @Override
    public ContentValues toContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", this.getTitle());
        contentValues.put("description", this.getDescription());
        contentValues.put("remind", this.getRemind());
        contentValues.put("time", this.getTime());
        contentValues.put("date",this.getDate());
        return contentValues;
    }
}
