package com.example.mastertask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserProfile extends AppCompatActivity {

    SharedPreferences preferences;
    public static final String USERNAME_TAG = "username";
    public static final String USERNAME_PHONE = "phone";
    public static final String USERNAME_ADDRESS = "address";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        saveValuesToSharedPreferences();
    }


    public void saveValuesToSharedPreferences(){
        SharedPreferences.Editor preferencesEdit = preferences.edit();

        // these need to be populated with the onclick listener
        Button btn = findViewById(R.id.profile_login_input);

        btn.setOnClickListener(v -> {

            String Username = ((EditText) findViewById(R.id.profile_username_input)).getText().toString();
            EditText phoneNumber = findViewById(R.id.Profile_phone_input);
            String phoneNumber_stringed = phoneNumber.toString();
            String Address = findViewById(R.id.profile_address_input).toString();

            preferencesEdit.putString(USERNAME_TAG, Username);
            preferencesEdit.putString(USERNAME_PHONE, phoneNumber_stringed);
            preferencesEdit.putString(USERNAME_ADDRESS, Address);

            preferencesEdit.apply();
            Toast.makeText(this, "Changes made", Toast.LENGTH_SHORT).show();
        });
    }

}