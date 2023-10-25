package com.example.m_expense;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class DetailOfObservation extends AppCompatActivity {


    private AdapterObservation adapterObservation;
    public static String id;
    private DatabaseHelper databaseHelper;

    private TextView Title, detailName,detailDate, detailTime,  detailComment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_of_observation);
        databaseHelper=new DatabaseHelper(this);

        Intent detail= getIntent();
        id = detail.getStringExtra("observationId");

        Title=findViewById(R.id.title);
        detailName=findViewById(R.id.detailName);
        detailDate=findViewById(R.id.detailDate);
        detailTime=findViewById(R.id.detailTime);
        detailComment=findViewById(R.id.detailComment);

        loadDataById();
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
        String selectQuery=" SELECT * FROM "+ DatabaseHelper.OBSERVATION_TABLE_NAME + "  WHERE " + DatabaseHelper.OBSERVATION_ID + "=\""+ id +"\"";
        SQLiteDatabase db= databaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToNext()){
            do{

                String Name=""+cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.OBSERVATION_COLUMN_NAME));

                String Date=""+cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.OBSERVATION_COLUMN_DATE));
                String Time=""+cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.OBSERVATION_COLUMN_TIME));
                String Description=""+cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.OBSERVATION_COLUMN_DESCRIPTION));

                Title.setText(Name);
                detailName.setText(Name);
                detailDate.setText(Date);
                detailTime.setText(Time);
                detailComment.setText(Description);


            }while(cursor.moveToNext());
        }
        db.close();
    }
}
