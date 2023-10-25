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
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private static final String[] Length={"1000m", "2000m", "3000m"};
    private static final String[] Level={"beginner", "professional"};
    private static final String[] Choice={"Yes", "No"};

    public boolean EditMode;

    String  uId, uName, uDestination, uDate, uLength, uLevel, uChoice, uDescription;
    EditText name, destination, description;
    AutoCompleteTextView date,length,level,choice;


    Button btDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AutoCompleteTextView pLength= (AutoCompleteTextView)findViewById(R.id.length);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,Length);
        dataAdapter.setDropDownViewResource((android.R.layout.simple_dropdown_item_1line));
        pLength.setAdapter(dataAdapter);
        pLength.setThreshold(256);

        findViewById(R.id.length).setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view){
                pLength.showDropDown();
            }
        });


        AutoCompleteTextView pLevel= (AutoCompleteTextView)findViewById(R.id.level);
        ArrayAdapter<String> levelAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,Level);
        dataAdapter.setDropDownViewResource((android.R.layout.simple_dropdown_item_1line));
        pLevel.setAdapter(levelAdapter);
        pLevel.setThreshold(256);

        findViewById(R.id.level).setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view){
                pLevel.showDropDown();
            }
        });

        AutoCompleteTextView pChoice= (AutoCompleteTextView)findViewById(R.id.choice);
        ArrayAdapter<String> choiceAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,Choice);
        //dataAdapter.setDropDownViewResource((android.R.layout.simple_dropdown_item_1line));
        pChoice.setAdapter(choiceAdapter);
        pChoice.setThreshold(256);

        findViewById(R.id.choice).setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view){
                pChoice.showDropDown();
            }
        });


        name=findViewById(R.id.TripName);
        destination=findViewById(R.id.editTextText);
        date=findViewById(R.id.date);
        btDate=findViewById(R.id.date_button);
        length=findViewById(R.id.length);
        level=findViewById(R.id.level);
        choice=findViewById(R.id.choice);
        description=findViewById(R.id.editTextTextMultiLine);
        Intent edit=getIntent();
        EditMode= edit.getBooleanExtra("EditMode",false);

        if(EditMode){
            uId=edit.getStringExtra("hike_id");
            uName=edit.getStringExtra("name");
            uDestination=edit.getStringExtra("destination");
            uDate=edit.getStringExtra("date");
            uLevel=edit.getStringExtra("level");
            uLength=edit.getStringExtra("length");
            uChoice=edit.getStringExtra("choice");
            uDescription=edit.getStringExtra("description");


            name.setText(uName);
            destination.setText(uDestination);
            date.setText(uDate);
            length.setText(uLength);
            level.setText(uLevel);
            choice.setText(uChoice);
            description.setText(uDescription);
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
        EditText editText2= (EditText)findViewById(R.id.TripName);

        if(TextUtils.isEmpty(editText2.getText().toString())){
            Toast t= Toast.makeText(this, "you must choose name of trip", Toast.LENGTH_SHORT);
            t.show();
            return;
        }
        EditText editText=(EditText)findViewById(R.id.editTextText);
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

        AutoCompleteTextView rg=(AutoCompleteTextView) findViewById(R.id.choice);
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
        EditText mName = (EditText)findViewById(R.id.TripName);
        String name= mName.getText().toString();
        EditText mDestination = (EditText)findViewById(R.id.editTextText);
        String destination= mDestination.getText().toString();
        EditText eDate = (EditText)findViewById(R.id.date);
        String date= eDate.getText().toString();
        EditText eLength = (EditText)findViewById(R.id.length);
        String length= eLength.getText().toString();
        EditText eLevel = (EditText)findViewById(R.id.level);
        String levels= eLevel.getText().toString();
        AutoCompleteTextView eChoice= (AutoCompleteTextView) findViewById(R.id.choice);
        String choice= eChoice.getText().toString();
        EditText eDescription = (EditText)findViewById(R.id.editTextTextMultiLine);
        String description= eDescription.getText().toString();

        new AlertDialog.Builder(this)
                .setTitle("Details of order")
                .setMessage("Details enter:\n" +
                        name+"\n"+
                        destination+"\n"+
                        date+"\n"+
                        length+"\n"+
                        levels+"\n"+
                        choice+"\n"+
                        description+"\n"
                ).setNeutralButton("back", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();


    }
    private void  saveDetails(){
        DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());
        EditText hikeName =(EditText) findViewById(R.id.TripName);
        EditText hikeDestination=(EditText) findViewById(R.id.editTextText);
        AutoCompleteTextView hikeDate =(AutoCompleteTextView) findViewById(R.id.date);
        AutoCompleteTextView hikeLength =(AutoCompleteTextView) findViewById(R.id.length);
        AutoCompleteTextView hikeLevel =(AutoCompleteTextView) findViewById(R.id.level);
        AutoCompleteTextView parkingChoice =(AutoCompleteTextView) findViewById(R.id.choice);
        EditText hikeDescription=(EditText) findViewById(R.id.editTextTextMultiLine);


        String name = hikeName.getText().toString();
        String destination =hikeDestination.getText().toString();
        String date = hikeDate.getText().toString();
        String length = hikeLength.getText().toString();
        String level = hikeLevel.getText().toString();
        String choice = parkingChoice.getText().toString();
        String description = hikeDescription.getText().toString();




            if(EditMode){

                dbHelper.updateHikeDetails(""+uId,name,destination,date,length,level,choice,description);
                Toast.makeText(this, "Hiking trip has been updated" , Toast.LENGTH_LONG).show();
            }
            else{
                long hikeId = dbHelper.insertHikeDetails(name,destination,date,length,level,choice,description);
                Toast.makeText(this, "Hiking trip has been created with id: " + hikeId, Toast.LENGTH_LONG).show();
            }


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



}