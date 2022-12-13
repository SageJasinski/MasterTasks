package com.example.mastertask;

import android.app.Application;
import android.util.Log;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.core.Amplify;

public class MasterTaskAmplifyApp  extends Application {

    public final static String TAG = "MasterTaskapplication";

    @Override
    public void onCreate() {
        super.onCreate();
        try{
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.configure(getApplicationContext());
        } catch (AmplifyException ae){
            Log.wtf(TAG, "Error in MasterTaskAmplifyApp: " + ae.getMessage(), ae);
        }
    }

}
