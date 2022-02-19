package com.example.hw2_fix;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class CreateTaskActivity extends AppCompatActivity {
    DatePickerDialog dp;
    TextView tvDate,  priority;
    int year;
    int month;
    int day;
    String userInput;
    ArrayList<Task> tasks;
    ListView lv;
    ArrayAdapter<Task> adapterTask;
    EditText editText;
    RadioGroup radioGroup;
    RadioButton radioButton;
    int radioButtonCheck;
    int results;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);


        editText = findViewById(R.id.createName);
        radioGroup = findViewById(R.id.radioGroup);
        tvDate = findViewById(R.id.testDAte);
        priority = findViewById(R.id.priority);

        findViewById(R.id.setDateButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                year = calendar.get(Calendar.YEAR);
                DatePickerDialog dp = new DatePickerDialog(CreateTaskActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                tvDate.setText(month + "/" + day + "/" + year);
                            }
                        },
                        2022,
                        2,
                        1);
                dp.show();
            }
        });

        findViewById(R.id.createSubmitButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int checked = radioGroup.getCheckedRadioButtonId();
                String value;
                int result = 0;
                if (checked == R.id.high) {
                    value = "3";
                    result = Integer.parseInt(value);
                } else if (checked == R.id.medium) {
                    value = "2";
                    result = Integer.parseInt(value);
                } else if (checked == R.id.low) {
                    value = "1";
                    result = Integer.parseInt(value);
                } else {
                    Toast.makeText(CreateTaskActivity.this, "Please Select a radio button!", Toast.LENGTH_SHORT).show();
                }

                if (tvDate.getText().toString().equals("")) {
                    Toast.makeText(CreateTaskActivity.this, "Please select date!!", Toast.LENGTH_SHORT).show();

                }
                else if ( editText.getText().toString().matches("")) {
                    Toast.makeText(CreateTaskActivity.this, "Please select Task name!!", Toast.LENGTH_SHORT).show();
                }else
                {
                    String date = tvDate.getText().toString();
                    String taskName = editText.getText().toString();

                    tasks.add(new Task(taskName,date, result));
                    Intent intent = new Intent(CreateTaskActivity.this, MainActivity.class);
                    startActivity(intent);
                }

            }
        });

       findViewById(R.id.createTaskCancelButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateTaskActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });








    }
}