package com.skyblue.taskgenius;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.skyblue.taskgenius.common.Task;
import com.skyblue.taskgenius.db.DataInstance;
import com.skyblue.taskgenius.model.TaskData;

public class AddTaskActivity extends AppCompatActivity {

    private TaskData taskData;

    private EditText editTextTitle;
    private EditText editTextDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        taskData = new TaskData(new DataInstance(this.getApplicationContext()));
        editTextTitle = (EditText) findViewById(R.id.editTextTitle);
        editTextDescription = (EditText) findViewById(R.id.editTextDescription);
    }

    public void onSaveButtonClick(View view){
        Task task = new Task();
        task.setTitle(editTextTitle.getText().toString());
        task.setDescription(editTextDescription.getText().toString());
        task.setDate("");
        task.setRemind(true);
        task.setTime("");

        taskData.AddTask(task);
    }

    public void onCancelButtonClick(View view){

    }
}
