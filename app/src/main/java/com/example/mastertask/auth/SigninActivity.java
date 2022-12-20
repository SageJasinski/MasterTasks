package com.example.mastertask.auth;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.core.Amplify;
import com.example.mastertask.MainActivity;
import com.example.mastertask.R;

public class SigninActivity extends AppCompatActivity {
    public static final String TAG = "SignInActivity";
    Intent callingIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        callingIntent = getIntent();

        setSignInForm();
    }

    public void setSignInForm(){
        findViewById(R.id.profile_login_input).setOnClickListener(v -> {
            String userEmail = ((EditText) findViewById(R.id.profile_username_input)).getText().toString();
            String userPassword = ((EditText) findViewById(R.id.Profile_password_input)).getText().toString();

            Amplify.Auth.signIn(
                    userEmail, userPassword,
                    success -> {
                        Log.i(TAG, "authentication checked");
                        Intent goToMain = new Intent(this, MainActivity.class);
                        startActivity(goToMain);
                        },
                    fail -> {Log.w(TAG, "authentication error");
                        runOnUiThread(() -> Toast.makeText(this, "Sign in failed!", Toast.LENGTH_SHORT).show());
                    }
            );
        });
    }

}
