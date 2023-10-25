package com.example.m_expense;

public class ObservationModel {
    private String id, id2, observe, date, time,comment;
    public ObservationModel(String Id, String Id2, String Title,  String Date, String Time,  String Comment) {
        this.id= Id;
        this.id2=Id2;
        this.observe= Title;
        this.date=Date;
        this.time=Time;
        this.comment=Comment;
    }

    public String getId() {return id;}

    public void setId(String id) {
        this.id = id;
    }

    public String getId2() {
        return id2;
    }

    public void setId2(String id2) {
        this.id2 = id2;
    }

    public String getObserveChoice() {
        return observe;
    }

    public void setObserveChoice(String observe) {
        this.observe = observe;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
