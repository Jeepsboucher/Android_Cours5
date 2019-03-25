package com.example.a533.cours5_Jp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth = FirebaseAuth.getInstance();
        setListener();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = auth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
        if(currentUser == null) {

            sendUserToSignUpOrLoginActivity();
        }
    }


    private void sendUserToSignUpOrLoginActivity() {
        Intent sendUserToSignUpOrLogin = new Intent(this,SignUpOrLoginActivity.class);
        startActivity(sendUserToSignUpOrLogin);
    }

    private void setListener() {
        findViewById(R.id.button_log_out).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                sendUserToSignUpOrLoginActivity();
            }
        });
    }
}
