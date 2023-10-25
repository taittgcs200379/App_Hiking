package com.example.m_expense;

public class ObservationModel {
    private String id, id2, observeChoice, date, time,comment;
    public ObservationModel(String observationId, String observationId2, String observationTitle,  String observationDate, String observationTime,  String observationComment) {
        this.id= observationId;
        this.id2=observationId2;
        this.observeChoice= observationTitle;
        this.date=observationDate;
        this.time=observationTime;
        this.comment=observationComment;
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
        return observeChoice;
    }

    public void setObserveChoice(String observeChoice) {
        this.observeChoice = observeChoice;
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
