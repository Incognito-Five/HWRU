package com.example.myapplication1.Timetable;

public class TimetableModel {

    String courseName;
    String startTime;
    String endTime;
    String roomLocation;

    public TimetableModel(String courseName, String startTime, String endTime, String roomLocation) {
        this.courseName = courseName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.roomLocation = roomLocation;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getRoomLocation() {
        return roomLocation;
    }

    public void setRoomLocation(String roomLocation) {
        this.roomLocation = roomLocation;
    }
}
