package com.example.m_expense;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class DetailsOfHike extends AppCompatActivity {

    private RecyclerView observeRV;
    private AdapterObservation adapterObservation;
    public static String id;
    private DatabaseHelper databaseHelper;

    private TextView Title, detailName,detailDestination, detailDate, detailLength, detailLevel, detailChoice, detailDescription;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_of_hike);

        databaseHelper=new DatabaseHelper(this);
        observeRV=findViewById(R.id.observationRV);
        observeRV.setHasFixedSize(true);


        Intent detail= getIntent();
        id = detail.getStringExtra("hikingId");

        Title=findViewById(R.id.title);
        detailName=findViewById(R.id.detailName);
        detailDestination=findViewById(R.id.detailDestination);
        detailDate=findViewById(R.id.detailDate);
        detailLength=findViewById(R.id.detailLength);
        detailLevel=findViewById(R.id.detailLevel);
        detailChoice=findViewById(R.id.detailChoice);
        detailDescription=findViewById(R.id.detailDescription);

        loadDataById();
        loadData();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem list = menu.findItem(R.id.AccessList);
        MenuItem creator =menu.findItem(R.id.HikingCreator);
        list.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem menuItem) {
                Intent t = new Intent(getApplicationContext(),TripsDetails.class);
                startActivity(t);

                return false;
            }
        });
        creator.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem menuItem) {
                Intent c = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(c);

                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void loadDataById() {
        String selectQuery=" SELECT * FROM "+ DatabaseHelper.HIKE_TABLE_NAME + "  WHERE " + DatabaseHelper.HIKE_COLUMN_ID + "=\""+ id +"\"";
        SQLiteDatabase db= databaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToNext()){
            do{

                  String Name=""+cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.HIKE_COLUMN_NAME));
                  String Destination=""+cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.HIKE_COLUMN_DESTINATION));
                  String Date=""+cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.HIKE_COLUMN_DATE));
                  String Length=""+cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.HIKE_COLUMN_LENGTH));
                  String Level=""+cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.HIKE_COLUMN_LEVEL));
                  String Choice=""+cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.HIKE_COLUMN_CHOICE));
                  String Description=""+cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.HIKE_COLUMN_DESCRIPTION));

                  Title.setText(Name);
                  detailName.setText(Name);
                  detailDestination.setText(Destination);
                  detailDate.setText(Date);
                  detailLength.setText(Length);
                  detailLevel.setText(Level);
                  detailChoice.setText(Choice);
                  detailDescription.setText(Description);


            }while(cursor.moveToNext());
        }
        db.close();
    }
    private void loadData() {
        adapterObservation = new AdapterObservation(this, databaseHelper.getAllObservationData());
        observeRV.setAdapter(adapterObservation);

    }
    @Override
    protected void onResume(){
        super.onResume();
        loadData();
    }



}