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

public class ShowProperties extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference database;
    AdapterODB adapterODB;
    ArrayList<OwnersRDB> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_properties);

        recyclerView = findViewById(R.id.recyclerViewT);
        database= FirebaseDatabase.getInstance().getReference("OWNERS");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        adapterODB = new AdapterODB(this,list);
        recyclerView.setAdapter(adapterODB);


        database.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    OwnersRDB ownersRDB = dataSnapshot.getValue(OwnersRDB.class);
                    list.add(ownersRDB);
                }
                adapterODB.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}