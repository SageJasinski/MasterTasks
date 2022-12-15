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
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Task;
import com.amplifyframework.datastore.generated.model.Team;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class TaskAdd extends AppCompatActivity {
//    TaskmasterData taskmasterData;

    public final static  String TAG = "TaskAdd";

    Spinner selectTeam;

    CompletableFuture<List<Team>> teamFuture = new CompletableFuture<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taskadd);
        selectTeam = findViewById(R.id.Team_selection);

//        Team newTeam = Team.builder().name("Dragon").email("sageJ@mail.mail").build();
//
//        Amplify.API.mutate(
//                ModelMutation.create(newTeam),
//                success ->{},
//                fail -> {}
//        );
//
//        saveToDB();
//    }

        Amplify.API.query(
                ModelQuery.list(Team.class),
                succes -> {
                    Log.i(TAG, "read Teams sucessfully");

                    ArrayList<String> teamNames = new ArrayList<>();
                    ArrayList<Team> teams = new ArrayList<>();

                    for (Team team : succes.getData()) {
                        teamNames.add(team.getName());
                        teams.add(team);
                    }
                    teamFuture.complete(teams);

                    runOnUiThread(()-> {
                    setTeamSpinner(teamNames);
                    });
                },
                fail -> {
                    teamFuture.complete(null);
                    Log.wtf(TAG, "error in readings Teams");
                }
        );
        saveToDB();
    }

    public void setTeamSpinner(ArrayList<String> teamNames){
        selectTeam.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, teamNames));
    }


//    public void pickTeam(ArrayList<String> teamNames){
//        selectTeam.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,));
//    }


    public void  saveToDB(){
        Button save = findViewById(R.id.task_add_butt_input);

        save.setOnClickListener(v -> {

            String stringTeam = selectTeam.getSelectedItem().toString();
            List<Team> teams = null;

            try{
                teams = teamFuture.get();
            }catch (InterruptedException | ExecutionException ie){
                Log.e(TAG, "getting team error");
            }
            Team selectedTeam = teams.stream().filter(team -> team.getName().equals(stringTeam)).findAny().orElseThrow(RuntimeException::new);

            Task newTask = Task.builder()
                    .title(((EditText)findViewById(R.id.add_task_input)).getText().toString())
                    .description(((EditText)findViewById(R.id.task_add_description_input)).getText().toString())
                    .team(selectedTeam)
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