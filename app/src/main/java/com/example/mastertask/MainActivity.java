package com.example.mastertask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import com.amplifyframework.datastore.generated.model.Task;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayList<Task> taskModel = new ArrayList<>();
//    TaskmasterData taskmasterData;
    public static final String DATABASE_NAME = "task_database";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        taskmasterData = Room.databaseBuilder(
//                getApplicationContext(),
////                TaskmasterData.class,
//                DATABASE_NAME).fallbackToDestructiveMigration().allowMainThreadQueries().build();

        Button btn = (Button)findViewById(R.id.button_addTask);
        ImageButton imgBtn = findViewById(R.id.main_user_setting_btn);
//        RecyclerView recyclerView = findViewById(R.id.recyclerView);

//        setTaskModel();
//        Task_Adapter adapter = new Task_Adapter(this, taskModel);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TaskAdd.class);
                startActivity(intent);
            }
        });

        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UserProfile.class);
                startActivity(intent);
            }
        });

        setTaskModel();
    }

    @Override
    public void onResume(){
        super.onResume();
        setGreeting();
        setTaskModel();
    }

    public void setGreeting(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String username = preferences.getString(UserProfile.USERNAME_TAG, "No User");
        ((TextView)findViewById(R.id.main_username)).setText(username);
    }

    private void setTaskModel(){
        String[] taskTitle = getResources().getStringArray(R.array.default_tasks_titles);
        String[] taskDescription = getResources().getStringArray(R.array.default_tasks_description);

//        for (int i = 0; i < taskTitle.length; i++){
//            taskModel.add(new Task(taskTitle[i], taskDescription[i], false));
//        }
    }

//    private void setRecyclerView(){
//        List<Tasks> tasks = taskmasterData.taskDAO().findAll();
//
//        RecyclerView taskRecycled = findViewById(R.id.recyclerView);
//        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
//        taskRecycled.setLayoutManager(manager);
//
//        Task_Adapter adapter = new Task_Adapter(tasks, this);
//
//        taskRecycled.setAdapter(adapter);
//    }

}