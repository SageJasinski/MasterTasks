package com.example.mastertask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserProfile extends AppCompatActivity {

    SharedPreferences preferences;
    public static final String USERNAME_TAG = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        saveValuesToSharedPreferences();
    }


    public void saveValuesToSharedPreferences(){
        SharedPreferences.Editor preferencesEdit = preferences.edit();
        // these need to be populated with the onclick listener
        Button btn = UserProfile.this.findViewById(R.id.profile_login_input);

        btn.setOnClickListener(v -> {
            String Username = ((EditText) findViewById(R.id.profile_username_input)).getText().toString();
            preferencesEdit.putString(USERNAME_TAG, Username);

            preferencesEdit.apply();

            Toast.makeText(this, "Changes made", Toast.LENGTH_SHORT).show();
        });
    }

}