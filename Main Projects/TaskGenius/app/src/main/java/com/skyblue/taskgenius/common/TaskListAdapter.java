package com.skyblue.taskgenius.common;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.skyblue.taskgenius.R;

import java.util.List;

public class TaskListAdapter extends ArrayAdapter<Task> {

    private Context context;
    private List<Task> todoList;

    public TaskListAdapter(Context context, int resource, List<Task> objects) {
        super(context,  resource, objects);
        this.context = context;
        this.todoList = objects;
    }


    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.fragment_single_task, parent, false);

        TextView taskTitle = (TextView) rowView.findViewById(R.id.textViewTitle);
        TextView taskDescription = (TextView) rowView.findViewById(R.id.textViewDescription);

        taskTitle.setText(todoList.get(position).getTitle());
        taskDescription.setText(todoList.get(position).getDescription());
        return rowView;
    }

}
