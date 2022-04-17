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

    DatabaseReference databaseO;
    AdapterODB adapterODB;
    ArrayList<OwnersRDB> listO;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_properties);



        RecyclerView recyclerView=findViewById(R.id.recyclerViewST);
        databaseO= FirebaseDatabase.getInstance().getReference("OWNERS");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);


        listO = new ArrayList<>();
        adapterODB = new AdapterODB(this,listO);
        recyclerView.setAdapter(adapterODB);




        databaseO.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    OwnersRDB ownersRDB = dataSnapshot.getValue(OwnersRDB.class);
                    listO.add(ownersRDB);
                }
                adapterODB.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}