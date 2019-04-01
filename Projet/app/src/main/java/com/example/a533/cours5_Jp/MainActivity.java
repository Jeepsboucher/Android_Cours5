package com.example.a533.cours5_Jp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a533.cours5_Jp.notification.NotificationService;
import com.example.a533.cours5_Jp.notification.model.ImportantMessageModel;
import com.example.a533.cours5_Jp.notification.model.MessageModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseFirestore database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = FirebaseFirestore.getInstance();
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
        } else {
            startService();
        }
    }


    private void sendUserToSignUpOrLoginActivity() {
        Intent sendUserToSignUpOrLogin = new Intent(this,SignUpOrLoginActivity.class);
        startActivity(sendUserToSignUpOrLogin);
    }

    private void startService() {
        Intent serviceIntent = new Intent(this, NotificationService.class);
        ContextCompat.startForegroundService(this,serviceIntent);
    }

    private void setListener() {
        findViewById(R.id.button_sendMessage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });

        findViewById(R.id.button_importantMessage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendImportantMessage();
            }
        });

        findViewById(R.id.button_log_out).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                sendUserToSignUpOrLoginActivity();
            }
        });
    }

    private void sendMessage() {
        EditText editTextMessage = findViewById(R.id.editText_message);
        MessageModel messageModel = new MessageModel(editTextMessage.getText().toString(), auth.getCurrentUser().getEmail());
        database.collection("Notification").add(messageModel).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),"Message sent", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void sendImportantMessage() {
        EditText editTextMessage = findViewById(R.id.editText_message);
        ImportantMessageModel messageModel = new ImportantMessageModel(editTextMessage.getText().toString(), auth.getCurrentUser().getEmail());
        database.collection("ImportantMessage").add(messageModel).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),"Message sent", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
