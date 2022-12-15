package com.example.mastertask.auth;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.options.AuthSignInOptions;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;
import com.example.mastertask.R;

public class SignUpActivity extends AppCompatActivity {
    public static final String TAG = "SignUpActivity";
    private static final String SIGNUP_EMAIL_TAG = "Signup_email_Tag";

    public void onCreate(Bundle savedInstence){
        super.onCreate(savedInstence);
        setContentView(R.layout.activity_signup);

        signUpForm();
    }

    public void signUpForm(){
        findViewById(R.id.SugnUp_Button).setOnClickListener(v ->{
            String userEmail = ((EditText)findViewById(R.id.Signup_email_input)).getText().toString();
            String userPassword = ((EditText) findViewById(R.id.Signup_password_input)).getText().toString();

            Amplify.Auth.signUp(
                userEmail,userPassword, AuthSignUpOptions.builder().userAttribute(AuthUserAttributeKey.email(), userEmail).build(),
                success -> {
                    Log.i(TAG, "SignUp sucess");
                    Intent goToVerifyActivity = new Intent(this, VerifySignUpActivity.class);
                    goToVerifyActivity.putExtra(SIGNUP_EMAIL_TAG, userEmail);
                    startActivity(goToVerifyActivity);
                },
                fail -> {
                    Log.w(TAG, "Sign up failed");
                    runOnUiThread(() -> Toast.makeText(this, "signUp Failed", Toast.LENGTH_SHORT).show());
                }
            );
        });
    }
}
