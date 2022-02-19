package com.example.hw2_fix;

import java.io.Serializable;
import java.util.Calendar;

public class Task implements Serializable {
    String taskName;
    String date;
    int priority;



    public Task(String taskName, String date, int priority) {
        this.taskName = taskName;
        this.date = date;
        this.priority = priority;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setDate(Calendar datetime) { this.date = date; }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getDate() { return date; }

    public int getPriority() {
        return priority;
    }





    @Override
    public String toString() {
        return taskName;
    }
}
