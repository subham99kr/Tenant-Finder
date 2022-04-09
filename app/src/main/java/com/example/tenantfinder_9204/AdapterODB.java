package com.example.tenantfinder_9204;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterODB extends RecyclerView.Adapter<AdapterODB.MyViewHolder> {

    Context context;
    ArrayList<OwnersRDB> list;

    public AdapterODB(Context context, ArrayList<OwnersRDB> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.owners_ava_recycler,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        OwnersRDB ownersRDB = list.get(position);
        holder.name.setText(ownersRDB.getName());
        holder.phone.setText(ownersRDB.getPhone());
        holder.details.setText(ownersRDB.getDetails());
        holder.address.setText(ownersRDB.getAddress());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static  class  MyViewHolder extends  RecyclerView.ViewHolder{

        TextView name , phone, details, address;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.nameO);
            phone=itemView.findViewById(R.id.phoneO);
            details=itemView.findViewById(R.id.detailsO);
            address=itemView.findViewById(R.id.addressO);
        }
    }
}