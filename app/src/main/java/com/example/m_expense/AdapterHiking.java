package com.example.m_expense;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterHiking extends RecyclerView.Adapter<AdapterHiking.HikingViewHolder> {
    private Context context;
    private ArrayList<HikingModel> hikingList;
    private DatabaseHelper databaseHelper;

    public AdapterHiking(Context context, ArrayList<HikingModel> hikingList){
        this.context=context;
        this.hikingList=hikingList;
        databaseHelper= new DatabaseHelper(context);
    }
    @NonNull
    @Override
    public HikingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.hiking_item,parent,false);
        HikingViewHolder vh= new HikingViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull HikingViewHolder holder, int position) {
        HikingModel hikingModel= hikingList.get(position);

        String id= hikingModel.getHikingId();
        String name=hikingModel.getHikingName();
        String destination=hikingModel.getHikingDestination();
        String date=hikingModel.getHikingDate();
        String length=hikingModel.getHikingLength();
        String level=hikingModel.getHikingLevel();
        String choice=hikingModel.getParkingChoice();
        String description=hikingModel.getHikingDescription();

        holder.hikingName.setText(name);
        holder.hikingDestination.setText(destination);
        holder.hikingDate.setText(date);
        holder.hikingLevel.setText(level);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detail = new Intent(context, DetailsOfHike.class);
                detail.putExtra("hikingId",id);
                context.startActivity(detail);

            }
        });
        holder.editHiking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent edit = new Intent(context, MainActivity.class);
                edit.putExtra("hike_id",id);
                edit.putExtra("name",name);
                edit.putExtra("destination",destination);
                edit.putExtra("date",date);
                edit.putExtra("length",length);
                edit.putExtra("level",level);
                edit.putExtra("choice",choice);
                edit.putExtra("description",description);
                edit.putExtra("EditMode",true);
                context.startActivity(edit);
            }
        });
        holder.observeHiking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent t = new Intent(context,ObserveCreator.class);
                t.putExtra("hike_id",id);
                t.putExtra("date",date);
                t.putExtra("SendData",true);
                context.startActivity(t);
            }
        });
        holder.deleteHiking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseHelper.deleteHikeDetails(id);

                ((TripsDetails)context).onResume();
            }
        });

    }



    @Override
    public int getItemCount() {
        return hikingList.size();
    }

    class HikingViewHolder extends RecyclerView.ViewHolder{

        TextView hikingName, hikingDestination, hikingDate, hikingLevel;
        Button editHiking, deleteHiking, observeHiking;
        public HikingViewHolder(@NonNull View itemView) {
            super(itemView);

            hikingName=itemView.findViewById(R.id.hikingName);
            hikingDestination=itemView.findViewById(R.id.hikingDestination);
            hikingDate=itemView.findViewById(R.id.hikingDate);
            hikingLevel=itemView.findViewById(R.id.hikingLevel);
            editHiking=itemView.findViewById(R.id.editHiking);
            deleteHiking=itemView.findViewById(R.id.deleteHiking);
            observeHiking=itemView.findViewById(R.id.observeHiking);
        }
    }
}
