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
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.util.Locale;

public class ObserveCreator extends AppCompatActivity {
    String id,Pid,observationTitle, observationDate,observationTime,observationComment;

    public boolean Send, Edit;

    AutoCompleteTextView Title,Date, Time;
    EditText ObservationComment;
    Button dateButton,timeButton;
    int hour, minute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observe_creator);

        dateButton=findViewById(R.id.observeDate);
        timeButton=findViewById(R.id.time_button);
        Title=findViewById(R.id.observation_creator);
        Date=findViewById(R.id.observation_date);
        Time=findViewById(R.id.time);
        ObservationComment=findViewById(R.id.comment);

        Intent t=getIntent();
        Send= t.getBooleanExtra("Save",false);
        Intent edit=getIntent();
        Edit=edit.getBooleanExtra("Edit",false);

        if(Send){
            Pid=t.getStringExtra("hike_id");
            observationDate=t.getStringExtra("date");

            Date.setText(observationDate);
        }
        if(Edit){
            id=edit.getStringExtra("Id");
            Pid=edit.getStringExtra("Id2");
            observationTitle=edit.getStringExtra("name");
            observationDate=edit.getStringExtra("date");
            observationTime=edit.getStringExtra("time");
            observationComment=edit.getStringExtra("comment");

            Title.setText(observationTitle);
            Date.setText(observationDate);
            Time.setText(observationTime);
            ObservationComment.setText(observationComment);

        }

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
            Toast t= Toast.makeText(this, " choose observation", Toast.LENGTH_SHORT);
            t.show();
            return;
        }
       AutoCompleteTextView observeDate= (AutoCompleteTextView)findViewById(R.id.observation_date);
        if(TextUtils.isEmpty(observeDate.getText().toString())){
            Toast t= Toast.makeText(this, "choose date", Toast.LENGTH_SHORT);
            t.show();
            return;
        }
        AutoCompleteTextView observeTime= (AutoCompleteTextView)findViewById(R.id.time);
        if(TextUtils.isEmpty(observeTime.getText().toString())){
            Toast t= Toast.makeText(this, " choose time", Toast.LENGTH_SHORT);
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
    private void showDatePickerDialog(){
        MaterialDatePicker materialDatePicker = MaterialDatePicker.Builder.datePicker().build();
        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                Date.setText("" + materialDatePicker.getHeaderText());

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
                Time.setText(String.format(Locale.getDefault(),"%02d:%02d",hour,minute));

            }
        };
        TimePickerDialog timePickerDialog=new TimePickerDialog(this, onTimeSetListener,hour,minute,true);

        timePickerDialog.show();
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
        if(Send){

            dbHelper.insertObservationDetails(""+Pid,name,date,time,comment);
            Toast.makeText(this, "Observation created" , Toast.LENGTH_LONG).show();
        }
        else if(Edit){
            dbHelper.updateObservationDetails(""+id,""+Pid,name,date,time,comment);
            Toast.makeText(this, "Observation updated " , Toast.LENGTH_LONG).show();
        }



    }



}



