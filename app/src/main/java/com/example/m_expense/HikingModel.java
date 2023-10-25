package com.example.m_expense;

public class HikingModel {
    private String Id, Name, Destination, Date, Length, Level, Choice, Description;

    public HikingModel(String hikingId, String hikingName, String hikingDestination, String hikingDate, String hikingLength, String hikingLevel, String parkingChoice, String hikingDescription) {
        this.Id = hikingId;
        this.Name = hikingName;
        this.Destination = hikingDestination;
        this.Date = hikingDate;
        this.Length = hikingLength;
        this.Level = hikingLevel;
        this.Choice = parkingChoice;
        this.Description = hikingDescription;
    }

    public String getHikingId() {
        return Id;
    }

    public void setHikingId() {
        this.Id = Id;
    }

    public String getHikingName() {
        return Name;
    }

    public void setHikingName(String hikingName) {
        this.Name = hikingName;
    }

    public String getHikingDestination() {
        return Destination;
    }

    public void setHikingDestination(String hikingDestination) {
        this.Destination = hikingDestination;
    }

    public String getHikingDate() {
        return Date;
    }

    public void setHikingDate(String hikingDate) {
        this.Date = hikingDate;
    }

    public String getHikingLength() {
        return Length;
    }

    public void setHikingLength(String hikingLength) {
        this.Length = hikingLength;
    }

    public String getHikingLevel() {
        return Level;
    }

    public void setHikingLevel(String hikingLevel) {
        this.Level = hikingLevel;
    }

    public String getParkingChoice() {
        return Choice;
    }

    public void setParkingChoice(String parkingChoice) {
        this.Choice = parkingChoice;
    }

    public String getHikingDescription() {
        return Description;
    }

    public void setHikingDescription(String hikingDescription) {
        this.Description = hikingDescription;
    }
}
