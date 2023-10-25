package com.example.m_expense;

public class HikingModel {
    private String hikingId, hikingName, hikingDestination, hikingDate, hikingLength, hikingLevel, parkingChoice, hikingDescription;

    public HikingModel(String hikingId, String hikingName, String hikingDestination, String hikingDate, String hikingLength, String hikingLevel, String parkingChoice, String hikingDescription) {
        this.hikingId = hikingId;
        this.hikingName = hikingName;
        this.hikingDestination = hikingDestination;
        this.hikingDate = hikingDate;
        this.hikingLength = hikingLength;
        this.hikingLevel = hikingLevel;
        this.parkingChoice = parkingChoice;
        this.hikingDescription = hikingDescription;
    }

    public String getHikingId() {
        return hikingId;
    }

    public void setHikingId() {
        this.hikingId = hikingId;
    }

    public String getHikingName() {
        return hikingName;
    }

    public void setHikingName(String hikingName) {
        this.hikingName = hikingName;
    }

    public String getHikingDestination() {
        return hikingDestination;
    }

    public void setHikingDestination(String hikingDestination) {
        this.hikingDestination = hikingDestination;
    }

    public String getHikingDate() {
        return hikingDate;
    }

    public void setHikingDate(String hikingDate) {
        this.hikingDate = hikingDate;
    }

    public String getHikingLength() {
        return hikingLength;
    }

    public void setHikingLength(String hikingLength) {
        this.hikingLength = hikingLength;
    }

    public String getHikingLevel() {
        return hikingLevel;
    }

    public void setHikingLevel(String hikingLevel) {
        this.hikingLevel = hikingLevel;
    }

    public String getParkingChoice() {
        return parkingChoice;
    }

    public void setParkingChoice(String parkingChoice) {
        this.parkingChoice = parkingChoice;
    }

    public String getHikingDescription() {
        return hikingDescription;
    }

    public void setHikingDescription(String hikingDescription) {
        this.hikingDescription = hikingDescription;
    }
}
