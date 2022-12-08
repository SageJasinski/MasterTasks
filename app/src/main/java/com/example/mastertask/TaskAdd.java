package com.example.mastertask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mastertask.database.TaskmasterData;

import java.util.Date;

public class TaskAdd extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taskadd);
        saveToDB();
    }

    TaskmasterData taskmasterData;
    Spinner taskSpinner;

    public void setupTypeSpinner(){
        taskSpinner.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                Tasks.TaskType.values()
        ));
    }

    public void  saveToDB(){
        Button save = findViewById(R.id.task_add_butt_input);
        save.setOnClickListener(v -> {
            Tasks tasks = new Tasks(
                    ((EditText)findViewById(R.id.add_task_input)).getText().toString(),
                    ((EditText)findViewById(R.id.task_add_description_input)).getText().toString(),
                    false,
                    new Date()
            );
            taskmasterData.taskDAO().insertTask(tasks);
            Toast.makeText(this, "New Task Added", Toast.LENGTH_SHORT).show();
        });
    }
}