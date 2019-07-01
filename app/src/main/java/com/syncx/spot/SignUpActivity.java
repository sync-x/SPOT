package com.syncx.spot;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null){
            // add snackbar
            startActivity(new Intent(SignUpActivity.this,MainActivity.class));
        }
    }

    public void goToLogin(View view){
        startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
    }

    public void signUp(View view){
        TextInputEditText signupet = findViewById(R.id.email);
        TextInputEditText passwordet = findViewById(R.id.password);
        String email = signupet.getText().toString();
        String password = passwordet.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            // add snackbar
                            startActivity(new Intent(SignUpActivity.this,MainActivity.class));
                        } else {
                            // add snackbar
                        }
                    }
                });
    }
}