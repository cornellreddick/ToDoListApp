package com.example.hw2_fix;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    final static public String TASKNAME_KEY = "Name: ";
    final static public String DATE_KEY = "Date: ";
    final static public String PRIORITY_KEY = "Priority: ";
    final static public String DATE_FORMAT = "MM/dd/yyyy";
    TextView numTasks, currentTask, taskDate, priorityStatus;
    public static ArrayList<Task> tasks;
    ListView lv;
    ArrayAdapter<Task> adapterTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        tasks = new ArrayList<>();
        tasks.add(new Task("Do Homework","02/07/2022",1));
        tasks.add(new Task("Name of Task 2","02/09/2022", 2));
        tasks.add(new Task("Name of Task 3","02/05/2022" , 1));
        tasks.add(new Task("Name of Task 4","02/03/2022", 3));
        tasks.add(new Task("Name of Task 5","02/02/2022" , 1));

        currentTask = findViewById(R.id.currentTask);
        currentTask.setText(String.valueOf(tasks.get(0).taskName));

        numTasks = findViewById(R.id.taskNumber);
        numTasks.setText(String.valueOf(tasks.size()));

        taskDate = findViewById(R.id.taskDate);
        taskDate.setText(tasks.get(0).getDate());

        priorityStatus = findViewById(R.id.priortyStatus);
        priorityStatus.setText(String.valueOf(tasks.get(0).getPriority()));

        Collections.sort(tasks, new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                return t1.getDate().compareTo(t2.getDate());
            }
        });

        Collections.sort(tasks, new Comparator<Task>() {
            public int compare(Task o1, Task o2) {
                if (o1.getDate() == null || o2.getDate() == null)
                    return 0;
                return o1.getDate().compareTo(o2.getDate());
            }
        });

        findViewById(R.id.viewTaskButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builderSingle = new AlertDialog.Builder(MainActivity.this);
                builderSingle.setTitle("Select Task");
                builderSingle.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                adapterTask = new ArrayAdapter<>(MainActivity.this, android.R.layout.select_dialog_item, android.R.id.text1, tasks);
                builderSingle.setAdapter(adapterTask, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        Task task = tasks.get(which);

                        String taskName = task.getTaskName();
                        String date = task.getDate();
                        int priority = task.getPriority();

                        Bundle b = new Bundle();
                        b.putString(TASKNAME_KEY, taskName);
                        b.putString(DATE_KEY, date);
                        b.putInt(PRIORITY_KEY, priority);

                        Intent intent = new Intent(MainActivity.this, TaskActivity.class);
                        intent.putExtras(b);
                        startActivity(intent);
                    }
                });
                builderSingle.show();
            }
        });

        findViewById(R.id.createTaskButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreateTaskActivity.class);
                startActivity(intent);
            }
        });



    }

    public String generateRandomDate(){
        Random r = new Random();
        Calendar c = java.util.Calendar.getInstance();
        c.set(Calendar.MONTH, Math.abs(r.nextInt()) % 12);
        c.set(Calendar.DAY_OF_MONTH, Math.abs(r.nextInt()) % 30);
        c.setLenient(true);
        return DATE_FORMAT.format(c.getTime().toString());
    }

}