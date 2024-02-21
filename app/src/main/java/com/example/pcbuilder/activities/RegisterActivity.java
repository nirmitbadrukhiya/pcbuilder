package com.example.pcbuilder.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pcbuilder.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    EditText nam,emil,pssw;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth=FirebaseAuth.getInstance();
        nam=findViewById(R.id.name);
        emil=findViewById(R.id.email);
        pssw=findViewById(R.id.pasw);

    }
    public void SignUp(View view){
        String name=nam.getText().toString();
        String email=emil.getText().toString();
        String pass=pssw.getText().toString();

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "Enter Name!", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(email)){
            Toast.makeText(this,"Enter Email!",Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(pass)){
            Toast.makeText(this,"Enter Password!",Toast.LENGTH_SHORT).show();
        }
        if (pass.length()<6){
            Toast.makeText(this,"Password too short!",Toast.LENGTH_SHORT).show();
        }
        auth.createUserWithEmailAndPassword(email,pass)
                .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this,"Successfully Register",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                        }else {
                            Toast.makeText(RegisterActivity.this,"Registration Failed"+task.getException(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    public void SignIn(View view){
        startActivity(new Intent(RegisterActivity.this, RegisterActivity.class));
    }
}

