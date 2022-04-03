package com.example.tenantfinder_9204;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InputPropertyDetails extends AppCompatActivity {
    EditText ettName , ettPhone , ettDetails , ettAddress ;
    Button btnAdd;

    DatabaseReference DataBaseTO;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_tenant);
        ettName =  findViewById(R.id.ettName);
        ettAddress =  findViewById(R.id.ettAddress);
        ettPhone = findViewById(R.id.ettPhone);
        ettDetails = findViewById(R.id.ettDetails);
        btnAdd = findViewById(R.id.btnAdd);

        DataBaseTO = FirebaseDatabase.getInstance().getReference().child("OWNERS");
        btnAdd.setOnClickListener (view -> insertPropertyData());

    }
    private void insertPropertyData(){
        String name = ettName.getText().toString();
        String phone = ettPhone.getText().toString();
        String details = ettDetails.getText().toString();
        String address = ettAddress.getText().toString();
        OwnerDB owners  = new OwnerDB(name,phone,details,address);


        //use- so that inputs doesn't gets overwritten.
        DataBaseTO.push().setValue(owners);
        Toast.makeText(InputPropertyDetails.this, "Data Inserted", Toast.LENGTH_SHORT).show();
    }
}