package com.example.mastertask;

public class Tasks {
    String title;
    String description;
    boolean isComplete;

    public Tasks(String title, String description, boolean isComplete) {
        this.title = title;
        this.description = description;
        this.isComplete = isComplete;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isComplete() {
        return isComplete;
    }
}
