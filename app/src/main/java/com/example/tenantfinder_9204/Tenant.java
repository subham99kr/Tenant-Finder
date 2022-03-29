package com.example.tenantfinder_9204;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Tenant extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenant);
    }
     public void tdenter(View view){
        Intent intent1 = new Intent(this, Input_Tenant.class);
        startActivity(intent1);}

    public void searchproperty(View view){
        Intent intent = new Intent(this, ShowProperties.class);
        startActivity(intent);}
}