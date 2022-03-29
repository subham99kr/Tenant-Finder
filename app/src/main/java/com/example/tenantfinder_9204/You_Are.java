package com.example.tenantfinder_9204;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class You_Are extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_are);
    }
    public void tenantView(View view){
        Intent intent = new Intent(this, Tenant.class);
        startActivity(intent);

    }
    public void ownerView(View view) {
        Intent intent2 = new Intent(this, Owner.class);
        startActivity(intent2);
    }
}