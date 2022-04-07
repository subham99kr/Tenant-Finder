package com.example.tenantfinder_9204;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Input_Tenant extends AppCompatActivity {
    EditText etName , etPhone , etDetails ;
    Button btnInsertData;

    DatabaseReference DataBaseTO;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_tenant);
        etName =  findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);
        etDetails = findViewById(R.id.etDetails);
        btnInsertData = findViewById(R.id.btnInsertData);

        DataBaseTO = FirebaseDatabase.getInstance().getReference().child("TENANTS");
        btnInsertData.setOnClickListener (view -> insertTenantData());

    }
    private void insertTenantData(){
        String name = etName.getText().toString();
        String phone = etPhone.getText().toString();
        String details = etDetails.getText().toString();

        TenantDB tenants  = new TenantDB(name,phone,details);


        //use- so that inputs doesn't gets overwritten.
        DataBaseTO.push().setValue(tenants);
        Toast.makeText(Input_Tenant.this, "Data Inserted", Toast.LENGTH_SHORT).show();
    }
}