package com.example.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity<TextView> extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;

    private Button buttonLogin;
    private Button buttonRegister;
    private EditText email;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);

        mAuth = FirebaseAuth.getInstance();

        buttonLogin = (Button) findViewById(R.id.LoginButton);
        buttonRegister = (Button) findViewById(R.id.RegisterButton);
        email = (EditText) findViewById(R.id.emailText);
        password = (EditText) findViewById((R.id.passwordText));

        buttonLogin.setOnClickListener(this);
        buttonRegister.setOnClickListener(this);
    }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }


    private void signIn() {
        String emailStr = email.getText().toString().trim();
        String passwordStr = password.getText().toString().trim();

        Task<AuthResult> authResultTask = mAuth.signInWithEmailAndPassword(emailStr, passwordStr)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(MainActivity.this, "Authentication successful.",
                                    Toast.LENGTH_SHORT).show();
                           // finish();
                            //startActivity(new Intent(getApplicationContext(), AssembleActivity.class));
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

    public void onClick(View view) {
        if (view == buttonLogin) {
            signIn();
        }
        if (view == buttonRegister) {
            //finish();
            //startActivity(new Intent(this, RegisterActivity.class));
        }

    }
}