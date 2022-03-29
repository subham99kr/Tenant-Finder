package com.example.tenantfinder_9204;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Input_Tenant extends AppCompatActivity {
    TextInputLayout nameT , numberT , DetailsT , saveT;

    FirebaseDatabase rootNode;
    DatabaseReference reference;



    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_tenant);
        nameT =  findViewById(R.id.NameTenant);
        numberT = findViewById(R.id.MobileNumberT);
        DetailsT = findViewById(R.id.DetailsTenant);
        saveT = findViewById(R.id.btnSave);

        saveT.setOnClickListener ( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference();


            }
        });

    }
}