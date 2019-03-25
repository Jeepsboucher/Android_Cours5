package com.example.a533.cours5_Jp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();

        setContentView(R.layout.activity_login);
        setListener();
    }

    private void sendUserToMainActivity() {
        Intent sendUserToSignUpOrLogin = new Intent(this,MainActivity.class);
        startActivity(sendUserToSignUpOrLogin);
    }

    private void setListener() {
        findViewById(R.id.button_log_in).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logIn();
            }
        });
    }

    private void logIn() {
        EditText userEmail = findViewById(R.id.editText_log_in_email);
        EditText password = findViewById(R.id.editText_log_in_password);

        auth.signInWithEmailAndPassword(userEmail.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    sendUserToMainActivity();
                } else {
                    Toast.makeText(getApplicationContext(), "Failed to log in", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
