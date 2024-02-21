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

public class LoginActivity extends AppCompatActivity {

    EditText emil,psw;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth=FirebaseAuth.getInstance();
        emil=findViewById(R.id.email);
        psw=findViewById(R.id.psw);

    }
    public void SignIn(View view){
        String email=emil.getText().toString();
        String pass=psw.getText().toString();

        if (TextUtils.isEmpty(email)){
            Toast.makeText(this,"Enter Email!",Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(pass)){
            Toast.makeText(this,"Enter Password!",Toast.LENGTH_SHORT).show();
        }
        if (pass.length()<6){
            Toast.makeText(this,"Password too short!",Toast.LENGTH_SHORT).show();
        }
        auth.signInWithEmailAndPassword(email,pass)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(LoginActivity.this,"Successfully Login",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        }else {
                            Toast.makeText(LoginActivity.this,"Logined Failed"+task.getException(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    public void SignUp(View view){
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }
}