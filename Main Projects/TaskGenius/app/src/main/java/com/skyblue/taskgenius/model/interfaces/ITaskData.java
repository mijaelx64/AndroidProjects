package com.skyblue.taskgenius.model.interfaces;

import com.skyblue.taskgenius.common.Task;

import java.util.List;

/**
 * Created by Toshiba on 11/9/2015.
 */
public interface ITaskData {
    void AddTask(Task task);
    void RemoveTask(String id);
    List<Task> getTaskList();
}

