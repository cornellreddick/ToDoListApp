package com.example.hw2_fix;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class TaskActivity extends AppCompatActivity {

    TextView tvName, tvDate, tvPriority;
    final static public String NAME_KEY = "Name";
    Bundle bundle;
    ArrayList<Task> tasks;
    ArrayAdapter<Task> adapterTask;
    ListView lv;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        tvName = findViewById(R.id.textViewName);
        tvDate = findViewById(R.id.textViewDate);
        tvPriority = findViewById(R.id.textViewStatus);

        Bundle b = getIntent().getExtras();
        String taskName = b.getString(MainActivity.TASKNAME_KEY);
        String date = b.getString(MainActivity.DATE_KEY);
        int priority = b.getInt(MainActivity.PRIORITY_KEY);

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

                // lv.findViewById(R.id.view);
                adapterTask = new ArrayAdapter<>(TaskActivity.this, android.R.layout.select_dialog_item, android.R.id.text1, tasks);
                lv.setAdapter(adapterTask);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        Task task = tasks.get(position);
                        adapterTask.remove(task);
                        adapterTask.notifyDataSetChanged();
                    }
                });
                Intent intent = new Intent(TaskActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });

    }

}