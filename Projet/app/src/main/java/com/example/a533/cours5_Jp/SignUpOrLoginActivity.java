package com.example.a533.cours5_Jp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SignUpOrLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_or_login);
        setListener();
    }

    private void setListener() {
        findViewById(R.id.button_go_to_sign_up).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendToSignUp();
            }
        });

        findViewById(R.id.button_go_to_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendToLogin();
            }
        });
    }

    private void sendToSignUp() {
        Intent sendUserToSignUpOrLogin = new Intent(this,SignUpActivity.class);
        startActivity(sendUserToSignUpOrLogin);
    }

    private void sendToLogin() {
        Intent sendUserToSignUpOrLogin = new Intent(this,LoginActivity.class);
        startActivity(sendUserToSignUpOrLogin);
    }
}
