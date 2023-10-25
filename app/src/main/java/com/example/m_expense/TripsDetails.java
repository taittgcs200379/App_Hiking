package com.example.m_expense;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;

public class TripsDetails extends AppCompatActivity {
    private RecyclerView hikingRV;
    private DatabaseHelper dbHelper;
    private AdapterHiking adapterHiking;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trips_details);

        dbHelper=new DatabaseHelper(this);

        hikingRV=findViewById(R.id.hikingRV);
        hikingRV.setHasFixedSize(true);

        loadData();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem list = menu.findItem(R.id.AccessList);
        MenuItem creator = menu.findItem(R.id.HikingCreator);
        MenuItem search = menu.findItem(R.id.SearchItem);
        SearchView searchView = (SearchView) search.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchHike(query);
                return true ;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchHike(newText);
                return true;
            }
        });


        search.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem menuItem) {
                Intent a = new Intent(getApplicationContext(),TripsDetails.class);
                startActivity(a);

                return false;
            }
        });

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

    private void searchHike(String query) {
        adapterHiking= new AdapterHiking(this,dbHelper.getSearchData(query));
        hikingRV.setAdapter(adapterHiking);

    }


    private void loadData() {
        adapterHiking = new AdapterHiking(this, dbHelper.getAllData());
        hikingRV.setAdapter(adapterHiking);

    }
    @Override
    protected void onResume(){
        super.onResume();
        loadData();
    }


}