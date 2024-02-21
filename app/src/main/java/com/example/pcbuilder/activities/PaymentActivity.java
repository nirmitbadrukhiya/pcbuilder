package com.example.pcbuilder.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.TextView;

import com.example.pcbuilder.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class PaymentActivity extends AppCompatActivity {

    TextView subTotal,discount,shipping,total;
    FirebaseAuth auth;
    FirebaseFirestore firestore;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        firestore= FirebaseFirestore.getInstance();
        auth= FirebaseAuth.getInstance();
        toolbar = findViewById(R.id.payment_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        subTotal=findViewById(R.id.sub_total);
        discount=findViewById(R.id.discount);
        shipping=findViewById(R.id.shipping);
        total=findViewById(R.id.total_amt);
    }
}