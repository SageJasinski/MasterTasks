package com.example.mastertask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Task;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Task> taskModel;
    public static final String DATABASE_NAME = "task_database";
    private String TAG = "Mainactivity";
    Task_Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button)findViewById(R.id.button_addTask);
        Button locationBTN = (Button) findViewById(R.id.button_location);
        ImageButton imgBtn = findViewById(R.id.main_user_setting_btn);

        locationBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Location.class);
                startActivity(intent);
            }
        });

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

        Amplify.API.query(

        ModelQuery.list(Task.class),
        success ->{

            for(Task dataTask : success.getData()){
                taskModel.add(dataTask);
            }
            runOnUiThread(() -> adapter.notifyDataSetChanged());
        },
        failure -> { Log.w(TAG, "Failed to reed database");}
        );

    }

    public void setGreeting(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String username = preferences.getString(UserProfile.USERNAME_TAG, "No User");
        ((TextView)findViewById(R.id.main_username)).setText(username);
    }

    private void setTaskModel() {
        taskModel = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        adapter = new Task_Adapter(taskModel, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

}