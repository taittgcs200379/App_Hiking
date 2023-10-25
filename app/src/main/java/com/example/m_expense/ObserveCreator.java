package com.example.m_expense;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
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
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.util.Locale;

public class ObserveCreator extends AppCompatActivity {
    String id,id2,oTitle, oDate,oTime,oComment;

    public boolean SendData, EditMode;
    private static final String[] Observation={"Sightings of animals", "Types of vegetation", "Weather conditions","Conditions of the trails"};
    AutoCompleteTextView ObservationTitle,ObservationDate, ObservationTime;
    EditText ObservationComment;
    Button dateButton,timeButton;
    int hour, minute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observe_creator);

        dateButton=findViewById(R.id.observeDate);
        timeButton=findViewById(R.id.time_button);
        ObservationTitle=findViewById(R.id.observation_creator);
        ObservationDate=findViewById(R.id.observation_date);
        ObservationTime=findViewById(R.id.time);
        ObservationComment=findViewById(R.id.comment);

        Intent t=getIntent();
        SendData= t.getBooleanExtra("SendData",false);
        Intent edit=getIntent();
        EditMode=edit.getBooleanExtra("EditMode",false);

        if(SendData){
            id2=t.getStringExtra("hike_id");
            oDate=t.getStringExtra("date");

            ObservationDate.setText(oDate);
        }
        if(EditMode){
            id=edit.getStringExtra("observeId");
            id2=edit.getStringExtra("observeId2");
            oTitle=edit.getStringExtra("name");
            oDate=edit.getStringExtra("date");
            oTime=edit.getStringExtra("time");
            oComment=edit.getStringExtra("comment");

            ObservationTitle.setText(oTitle);
            ObservationDate.setText(oDate);
            ObservationTime.setText(oTime);
            ObservationComment.setText(oComment);

        }

        AutoCompleteTextView pObservation= (AutoCompleteTextView)findViewById(R.id.observation_creator);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,Observation);
        dataAdapter.setDropDownViewResource((android.R.layout.simple_dropdown_item_1line));
        pObservation.setAdapter(dataAdapter);
        pObservation.setThreshold(256);
        findViewById(R.id.observation_creator).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                pObservation.showDropDown();
            }
        });
        dateButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });
        timeButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePickerDialog();
            }
        });

    }
    public void handleButtonClick(View view){

        EditText observeName= (EditText)findViewById(R.id.observation_creator);
        if(TextUtils.isEmpty(observeName.getText().toString())){
            Toast t= Toast.makeText(this, "you must choose observation", Toast.LENGTH_SHORT);
            t.show();
            return;
        }
       AutoCompleteTextView observeDate= (AutoCompleteTextView)findViewById(R.id.observation_date);
        if(TextUtils.isEmpty(observeDate.getText().toString())){
            Toast t= Toast.makeText(this, "you must choose date", Toast.LENGTH_SHORT);
            t.show();
            return;
        }
        AutoCompleteTextView observeTime= (AutoCompleteTextView)findViewById(R.id.time);
        if(TextUtils.isEmpty(observeTime.getText().toString())){
            Toast t= Toast.makeText(this, "you must choose time", Toast.LENGTH_SHORT);
            t.show();
            return;
        }
        else{
            displayAlert();
            saveDetails();
        }
    }
    public void displayAlert(){
        EditText oName = (EditText)findViewById(R.id.observation_creator);
        String name= oName.getText().toString();
        AutoCompleteTextView oDate= (AutoCompleteTextView) findViewById(R.id.observation_date);
        String date= oDate.getText().toString();
        AutoCompleteTextView oTime= (AutoCompleteTextView) findViewById(R.id.time);
        String time= oTime.getText().toString();
        EditText oComment= (EditText)findViewById(R.id.comment);
        String comment= oComment.getText().toString();

        new AlertDialog.Builder(this)
                .setTitle("Details of order")
                .setMessage("Details enter:\n" +
                        name+"\n"+
                        date+"\n"+
                        time+"\n"+
                        comment+"\n"
                ).setNeutralButton("back", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();


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
    private void  saveDetails(){
        DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());

        AutoCompleteTextView observeOption =(AutoCompleteTextView) findViewById(R.id.observation_creator);
        AutoCompleteTextView observeDate =(AutoCompleteTextView) findViewById(R.id.observation_date);
        AutoCompleteTextView observeTime =(AutoCompleteTextView) findViewById(R.id.time);
        EditText observeComment=(EditText) findViewById(R.id.comment);


        String name=observeOption.getText().toString();
        String date=observeDate.getText().toString();
        String time= observeTime.getText().toString();
        String comment= observeComment.getText().toString();
        if(SendData){

            dbHelper.insertObservationDetails(""+id2,name,date,time,comment);
            Toast.makeText(this, "Observation has been created" , Toast.LENGTH_LONG).show();
        }
        else if(EditMode){
            dbHelper.updateObservationDetails(""+id,""+id2,name,date,time,comment);
            Toast.makeText(this, "Observation has been updated " , Toast.LENGTH_LONG).show();
        }



    }
    private void showDatePickerDialog(){
        MaterialDatePicker materialDatePicker = MaterialDatePicker.Builder.datePicker().build();
        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                ObservationDate.setText("" + materialDatePicker.getHeaderText());

            }
        });
        materialDatePicker.show(getSupportFragmentManager(), "TAG");
    }

    private void showTimePickerDialog(){
        TimePickerDialog.OnTimeSetListener onTimeSetListener= new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectHour, int selectMinute) {
                hour = selectHour;
                minute=selectMinute;
                ObservationTime.setText(String.format(Locale.getDefault(),"%02d:%02d",hour,minute));

            }
        };
        TimePickerDialog timePickerDialog=new TimePickerDialog(this, onTimeSetListener,hour,minute,true);

        timePickerDialog.show();
    }


}



