package com.skyblue.taskgenius.model;

import android.content.ContentValues;
import android.database.Cursor;

import com.skyblue.taskgenius.common.Task;
import com.skyblue.taskgenius.db.DataInstance;
import com.skyblue.taskgenius.model.interfaces.IData;
import com.skyblue.taskgenius.model.interfaces.ITaskData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Toshiba on 11/9/2015.
 */
public class TaskData implements ITaskData {

    private DataInstance dataConnection;

    public TaskData(DataInstance dataConnection) {
        this.dataConnection = dataConnection;
    }

    @Override
    public void AddTask(Task task) {
        dataConnection.getDb().insert("Task", null, task.toContentValues());
    }

    @Override
    public void RemoveTask(String id) {

    }

    @Override
    public List<Task> getTaskList() {
        List<Task> taskList = new ArrayList<Task>();

        String[] columns = new String[]{"id", "title", "description", "remind" , "date", "time"};
        //SELECT id, todo FROM todo
        //db.rawQuery("SELECT _id, todo FROM todo;", null);
        Cursor cursor = dataConnection.getDb().query("Task", columns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            int indexId = cursor.getColumnIndex("id");
            int indexTitle = cursor.getColumnIndex("title");
            int indexDescription = cursor.getColumnIndex("description");

            Task singletask = new Task();
            //singletask.setId(cursor.getInt(indexId));
            singletask.setTitle(cursor.getString(indexTitle));
            singletask.setDescription(cursor.getString(indexDescription));

            taskList.add(singletask);
            cursor.moveToNext();
        }
        return taskList;
    }

}
