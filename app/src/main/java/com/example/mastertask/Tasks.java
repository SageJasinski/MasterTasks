package com.example.mastertask;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

import kotlin.experimental.ExperimentalTypeInference;

@Entity
public class Tasks {
    @PrimaryKey(autoGenerate = true)
        Long id;
        String title;
        String description;
        boolean isComplete;
        Date dateAdded;
        private TaskType task;

    public void setTask(TaskType task) {
        this.task = task;
    }

    public TaskType getTask() {
        return task;
    }

    public Tasks(String title, String description, boolean isComplete) {
        this.title = title;
        this.description = description;
        this.isComplete = isComplete;
    }

    public Tasks(String title, String description, boolean isComplete, Date date) {
        this.title = title;
        this.description = description;
        this.isComplete = isComplete;
        this.dateAdded = date;
    }

    public String getTitle() {
        return title;
    }

    public enum TaskType{
        TITLE("title"),
        DESCRIPTION("description");

        private final String taskType;

        TaskType(String taskType){
            this.taskType = taskType;
        }

        public  String getTaskType(){
            return taskType;
        }
    }

    public String getDescription() {
        return description;
    }

    public boolean isComplete() {
        return isComplete;
    }
}
