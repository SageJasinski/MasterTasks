package com.example.mastertask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Task;

import java.util.Date;

public class TaskAdd extends AppCompatActivity {
//    TaskmasterData taskmasterData;

    public final static  String TAG = "TaskAdd";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taskadd);
//        taskmasterData = Room.databaseBuilder(getApplicationContext(), TaskmasterData.class, "task_database").allowMainThreadQueries().build();
        saveToDB();
    }



//    public void setupTypeSpinner(){
//        taskSpinner.setAdapter(new ArrayAdapter<>(
//                this,
//                android.R.layout.simple_spinner_item,
////                Tasks.TaskType.values()
//        ));
//    }

    public void  saveToDB(){
        Button save = findViewById(R.id.task_add_butt_input);

        save.setOnClickListener(v -> {
            Task newTask = Task.builder()
                    .title(((EditText)findViewById(R.id.add_task_input)).getText().toString())
                    .description(((EditText)findViewById(R.id.task_add_description_input)).getText().toString())
                    .build();

            Amplify.API.mutate(
                    ModelMutation.create(newTask),
                    success -> Log.i(TAG, "saveToDB W.A.I."),
                    fail-> Log.w(TAG, "saveToDB error", fail)
            );

            Toast.makeText(this, "New Task Added", Toast.LENGTH_SHORT).show();
        });
    }
}