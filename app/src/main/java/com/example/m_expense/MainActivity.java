package com.example.m_expense;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

public class MainActivity extends AppCompatActivity {


    public boolean Edit;

    String  updateId, updateName, updateDestination, updateDate, updateLength, updateLevel, updateChoice, updateDescription;
    EditText name, destination, description;
    AutoCompleteTextView date,length,level,choice;


    Button btDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name=findViewById(R.id.nameOftrip);
        destination=findViewById(R.id.Location);
        date=findViewById(R.id.date);
        btDate=findViewById(R.id.date_button);
        length=findViewById(R.id.length);
        level=findViewById(R.id.level);
        choice=findViewById(R.id.Parking);
        description=findViewById(R.id.Description);
        Intent edit=getIntent();
        Edit= edit.getBooleanExtra("Edit",false);

        if(Edit){
            updateId=edit.getStringExtra("id");
            updateName=edit.getStringExtra("name");
            updateDestination=edit.getStringExtra("destination");
            updateDate=edit.getStringExtra("date");
            updateLevel=edit.getStringExtra("level");
            updateLength=edit.getStringExtra("length");
            updateChoice=edit.getStringExtra("choice");
            updateDescription=edit.getStringExtra("description");


            name.setText(updateName);
            destination.setText(updateDestination);
            date.setText(updateDate);
            length.setText(updateLength);
            level.setText(updateLevel);
            choice.setText(updateChoice);
            description.setText(updateDescription);
        }

        btDate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });
    }

    private void showDatePickerDialog() {
        MaterialDatePicker materialDatePicker=MaterialDatePicker.Builder.datePicker().build();

        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                date.setText(""+materialDatePicker.getHeaderText());

            }
        });
        materialDatePicker.show(getSupportFragmentManager(),"TAG");

    }

    public void handleButtonClick(View v){
        EditText editText2= (EditText)findViewById(R.id.nameOftrip);

        if(TextUtils.isEmpty(editText2.getText().toString())){
            Toast t= Toast.makeText(this, "you must choose name of trip", Toast.LENGTH_SHORT);
            t.show();
            return;
        }
        EditText editText=(EditText)findViewById(R.id.Location);
        if(TextUtils.isEmpty(editText.getText().toString())){
            Toast t= Toast.makeText(this, "you must choose destination", Toast.LENGTH_SHORT);
            t.show();
            return;
        }

        AutoCompleteTextView d = (AutoCompleteTextView) findViewById(R.id.date);
        if(TextUtils.isEmpty(d.getText().toString())){
            Toast t= Toast.makeText(this, "you must choose date", Toast.LENGTH_SHORT);
            t.show();
            return;
        }
        AutoCompleteTextView l =(AutoCompleteTextView)findViewById((R.id.length));
        if(TextUtils.isEmpty(l.getText().toString())){
            Toast t= Toast.makeText(this, "you must choose length", Toast.LENGTH_SHORT);
            t.show();
            return;
        }
        AutoCompleteTextView level =(AutoCompleteTextView)findViewById((R.id.level));
        if(TextUtils.isEmpty(level.getText().toString())){
            Toast t= Toast.makeText(this, "you must choose level", Toast.LENGTH_SHORT);
            t.show();
            return;
        }

        AutoCompleteTextView rg=(AutoCompleteTextView) findViewById(R.id.Parking);
        if(TextUtils.isEmpty(level.getText().toString())){
            Toast t= Toast.makeText(this, "you must choose your option", Toast.LENGTH_SHORT);
            t.show();
            return;

        }
        else {
            displayAlert();
            saveDetails();
        }
    }
    public void displayAlert(){
        EditText Name = (EditText)findViewById(R.id.nameOftrip);
        String name= Name.getText().toString();
        EditText Location = (EditText)findViewById(R.id.Location);
        String location= Location.getText().toString();
        EditText Date = (EditText)findViewById(R.id.date);
        String date= Date.getText().toString();
        EditText Length = (EditText)findViewById(R.id.length);
        String length= Length.getText().toString();
        EditText Level = (EditText)findViewById(R.id.level);
        String levels= Level.getText().toString();
        AutoCompleteTextView Parking= (AutoCompleteTextView) findViewById(R.id.Parking);
        String parking= Parking.getText().toString();
        EditText Description = (EditText)findViewById(R.id.Description);
        String description= Description.getText().toString();

        new AlertDialog.Builder(this)
                .setTitle("Details ")
                .setMessage("Details enter:\n" +
                        name+"\n"+
                        location+"\n"+
                        date+"\n"+
                        length+"\n"+
                        levels+"\n"+
                        parking+"\n"+
                        description+"\n"
                ).setNeutralButton("back", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem list = menu.findItem(R.id.Hike_List);
        MenuItem creator =menu.findItem(R.id.Make_Hike);
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
    private void  saveDetails(){
        DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());
        EditText hikeName =(EditText) findViewById(R.id.nameOftrip);
        EditText hikeDestination=(EditText) findViewById(R.id.Location);
        AutoCompleteTextView hikeDate =(AutoCompleteTextView) findViewById(R.id.date);
        AutoCompleteTextView hikeLength =(AutoCompleteTextView) findViewById(R.id.length);
        AutoCompleteTextView hikeLevel =(AutoCompleteTextView) findViewById(R.id.level);
        AutoCompleteTextView parkingChoice =(AutoCompleteTextView) findViewById(R.id.Parking);
        EditText hikeDescription=(EditText) findViewById(R.id.Description);
        String name = hikeName.getText().toString();
        String destination =hikeDestination.getText().toString();
        String date = hikeDate.getText().toString();
        String length = hikeLength.getText().toString();
        String level = hikeLevel.getText().toString();
        String choice = parkingChoice.getText().toString();
        String description = hikeDescription.getText().toString();




            if(Edit){

                dbHelper.updateHikeDetails(""+updateId,name,destination,date,length,level,choice,description);
                Toast.makeText(this, "Hiking trip has been updated" , Toast.LENGTH_LONG).show();
            }
            else{
                long hikeId = dbHelper.insertHikeDetails(name,destination,date,length,level,choice,description);
                Toast.makeText(this, "Hiking trip has been created with: " , Toast.LENGTH_LONG).show();
            }


    }




}