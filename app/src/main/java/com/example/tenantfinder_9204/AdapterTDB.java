package com.example.tenantfinder_9204;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AdapterTDB extends RecyclerView.Adapter<AdapterTDB.MyViewHolder> {

    Context context;
    ArrayList<TenantsRDB> list;

    public AdapterTDB(Context context, ArrayList<TenantsRDB> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.tenants_ava_recycler,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getImageUrl()).circleCrop().into(holder.imageView);
        TenantsRDB tenantsRDB = list.get(position);
        holder.name.setText(tenantsRDB.getName());
        holder.phone.setText(tenantsRDB.getPhone());
        holder.details.setText(tenantsRDB.getDetails());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static  class  MyViewHolder extends  RecyclerView.ViewHolder{

        TextView name , phone, details;
        ImageView imageView;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.nameT);
            phone=itemView.findViewById(R.id.phoneT);
            details=itemView.findViewById(R.id.detailsT);
            imageView=itemView.findViewById(R.id.imageViewT);
        }
    }
}
