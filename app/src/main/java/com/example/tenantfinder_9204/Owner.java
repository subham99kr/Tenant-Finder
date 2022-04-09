package com.example.tenantfinder_9204;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Owner extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner);
    }
    public void viewTenants(View view){
        Intent intent = new Intent(this, TenantsAvailable.class);
        startActivity(intent);}
    public void addProperty(View view){
        Intent intent = new Intent(this, InputPropertyDetails.class);
        startActivity(intent);
    }

}