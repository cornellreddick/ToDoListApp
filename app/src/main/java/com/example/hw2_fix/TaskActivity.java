package com.example.hw2_fix;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class TaskActivity extends AppCompatActivity implements MainActivity.RemoveItem {

    TextView tvName, tvDate, tvPriority;
    final static public String NAME_KEY = "Name";
    final static public String DELETE_KEY = "delete";
    final static public String P_KEY = "deletePos";
    final static public String TASK_NAME = "taskName";
    final static public String TASK_DATE= "taskDate";
    final static public String TASK_PRIORITY = "prority";
    Bundle bundle;
    ArrayList<Task> tasks;
    ArrayAdapter<Task> adapterTask;
    ListView lv;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        setTitle("Display Task");
        tvName = findViewById(R.id.textViewName);
        tvDate = findViewById(R.id.textViewDate);
        tvPriority = findViewById(R.id.textViewStatus);

        Bundle b = getIntent().getExtras();
        String taskName = b.getString(MainActivity.TASKNAME_KEY);
        String date = b.getString(MainActivity.DATE_KEY);
        int priority = b.getInt(MainActivity.PRIORITY_KEY);
        int pos = b.getInt(MainActivity.POS_KEY);

        tvName.setText(MainActivity.TASKNAME_KEY + taskName);
        tvDate.setText(MainActivity.DATE_KEY + date);
        tvPriority.setText(MainActivity.PRIORITY_KEY + priority);

        findViewById(R.id.taskCancelButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TaskActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.taskDeleteButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = getIntent().getExtras().getInt(MainActivity.POS_KEY);
                int d = MainActivity.deletePos;
                MainActivity.tasks.remove(d);
                MainActivity.adapterTask.notifyDataSetChanged();
               Intent intent = new Intent(TaskActivity.this, MainActivity.class);
               startActivity(intent);
            }
        });

    }

    @Override
    public void getPos(int pos) {

    }

    @Override
    public void removeItem(int remove) {
    tasks.remove(remove);
    adapterTask.notifyDataSetChanged();
    }
}