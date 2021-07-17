package com.example.myapplication1.Timetable;

public class TimetableModel {

    String courseName;
    String courseCode;
    String days;
    String startTime;
    String endTime;
    String professor;
    String roomLocation;
    String description;
    Boolean mon;
    Boolean tues;
    Boolean wed;
    Boolean thurs;
    Boolean fri;
    Boolean sat;
    Boolean sun;

    //empty constructor
    public TimetableModel(){}

    //used in Course Fragment
    public TimetableModel(String courseName, String courseCode, String days, String startTime, String endTime, String professor, String roomLocation, String description){
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.days = days;
        this.startTime = startTime;
        this.endTime = endTime;
        this.professor = professor;
        this.roomLocation = roomLocation;
        this.description = description;
    }
    //used in Schedule Fragment
    public TimetableModel(String courseName, String startTime, String endTime, String roomLocation) {
        this.courseName = courseName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.roomLocation = roomLocation;
    }

    //Getters and Setters
    public Boolean getMon() {
        return mon;
    }

    public void setMon(Boolean mon) {
        this.mon = mon;
    }

    public Boolean getTues() {
        return tues;
    }

    public void setTues(Boolean tues) {
        this.tues = tues;
    }

    public Boolean getWed() {
        return wed;
    }

    public void setWed(Boolean wed) {
        this.wed = wed;
    }

    public Boolean getThurs() {
        return thurs;
    }

    public void setThurs(Boolean thurs) {
        this.thurs = thurs;
    }

    public Boolean getFri() {
        return fri;
    }

    public void setFri(Boolean fri) {
        this.fri = fri;
    }

    public Boolean getSat() {
        return sat;
    }

    public void setSat(Boolean sat) {
        this.sat = sat;
    }

    public Boolean getSun() {
        return sun;
    }

    public void setSun(Boolean sun) {
        this.sun = sun;
    }
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
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

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getRoomLocation() {
        return roomLocation;
    }

    public void setRoomLocation(String roomLocation) {
        this.roomLocation = roomLocation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
