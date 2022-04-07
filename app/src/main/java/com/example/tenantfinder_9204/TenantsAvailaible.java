package com.example.tenantfinder_9204;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TenantsAvailaible extends AppCompatActivity {

   RecyclerView recyclerView;
   DatabaseReference database;
   AdapterTDB adapterTDB;
   ArrayList<TenantsRDB> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenants_availaible);

        recyclerView = findViewById(R.id.tenantsAvail);
        database= FirebaseDatabase.getInstance().getReference("TENANTS");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        adapterTDB = new AdapterTDB(this,list);
        recyclerView.setAdapter(adapterTDB);

        //call back method
        database.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    TenantsRDB tenantsRDB = dataSnapshot.getValue(TenantsRDB.class);
                    list.add(tenantsRDB);
                }
                adapterTDB.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}