package com.example.myapplication1.Timetable;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class TimetableDBHelper extends SQLiteOpenHelper {


    public TimetableDBHelper(Context context) {
        super(context, "Courses.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase coursedb) {
        coursedb.execSQL("create Table CourseDetails(course_name TEXT primary key, course_code TEXT, days TEXT, start_time TEXT, end_time TEXT, professor TEXT, location TEXT, description TEXT)");
     }

    @Override
    public void onUpgrade(SQLiteDatabase coursedb, int i, int i1) {
        coursedb.execSQL("drop Table if exists CourseDetails");
    }

    public Boolean AddCourseData(String course_name, String course_code, String days, String start_time, String end_time, String professor, String location, String description) {
        SQLiteDatabase coursedb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("course_name", course_name);
        contentValues.put("course_code", course_code);
        contentValues.put("days", days);
        contentValues.put("start_time", start_time);
        contentValues.put("end_time", end_time);
        contentValues.put("professor", professor);
        contentValues.put("location", location);
        contentValues.put("description", description);
        long result = coursedb.insert("CourseDetails", null, contentValues);
        return result != -1;
    }

    public Boolean UpdateCourseData(String course_name, String course_code, String days, String start_time, String end_time, String professor, String location, String description) {
        SQLiteDatabase coursedb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("course_code", course_code);
        contentValues.put("days", days);
        contentValues.put("start_time", start_time);
        contentValues.put("end_time", end_time);
        contentValues.put("professor", professor);
        contentValues.put("location", location);
        contentValues.put("description", description);
        Cursor cursor = coursedb.rawQuery("Select * from CourseDetails where course_name = ?", new String[]{course_name});
        if (cursor.getCount() > 0) {
            long result = coursedb.update("CourseDetails", contentValues, "course_name=?", new String[]{course_name});
            return result != -1;
        } else {
            return false;
        }
        
    }

    public Boolean DeleteCourseData(String course_name) {
        SQLiteDatabase coursedb = this.getWritableDatabase();
        Cursor cursor = coursedb.rawQuery("Select * from CourseDetails where course_name = ?", new String[]{course_name});
        if (cursor.getCount() > 0) {
            long result = coursedb.delete("CourseDetails", "course_name=?", new String[]{course_name});
            return result != -1;
        } else {
            return false;
        }
    }

    public Cursor getMonday() {
        String query = "SELECT * FROM ( SELECT * FROM CourseDetails ORDER BY CASE WHEN start_time like '%AM' then 1 when start_time like '%12:00 PM%' then 2 else start_time end) WHERE days LIKE '%Monday%'" ;
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = null;
        if (database != null) {
            cursor = database.rawQuery(query, null);
        }
        return cursor;
    }

    public Cursor getTuesday() {
        String query = "SELECT * FROM ( SELECT * FROM CourseDetails ORDER BY CASE WHEN start_time like '%AM' then 1 when start_time like '%12:00 PM%' then 2 else start_time end) WHERE days LIKE '%Tuesday%'";
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = null;
        if (database != null) {
            cursor = database.rawQuery(query, null);
        }
        return cursor;
    }

    public Cursor getWednesday() {
        String query = "SELECT * FROM ( SELECT * FROM CourseDetails ORDER BY CASE WHEN start_time like '%AM' then 1 when start_time like '%12:00 PM%' then 2 else start_time end) WHERE days LIKE '%Wednesday%'";
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = null;
        if (database != null) {
            cursor = database.rawQuery(query, null);
        }
        return cursor;
    }

    public Cursor getThursday() {
        String query = "SELECT * FROM ( SELECT * FROM CourseDetails ORDER BY CASE WHEN start_time like '%AM' then 1 when start_time like '%12:00 PM%' then 2 else start_time end) WHERE days LIKE '%Thursday%'";
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = null;
        if (database != null) {
            cursor = database.rawQuery(query, null);
        }
        return cursor;
    }

    public Cursor getFriday() {
        String query = "SELECT * FROM ( SELECT * FROM CourseDetails ORDER BY CASE WHEN start_time like '%AM' then 1 when start_time like '%12:00 PM%' then 2 else start_time end) WHERE days LIKE '%Friday%'";
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = null;
        if (database != null) {
            cursor = database.rawQuery(query, null);
        }
        return cursor;
    }

    public Cursor getSaturday() {
        String query = "SELECT * FROM ( SELECT * FROM CourseDetails ORDER BY CASE WHEN start_time like '%AM' then 1 when start_time like '%12:00 PM%' then 2 else start_time end) WHERE days LIKE '%Saturday%'";
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = null;
        if (database != null) {
            cursor = database.rawQuery(query, null);
        }
        return cursor;
    }

    public Cursor getSunday() {
        String query = "SELECT * FROM ( SELECT * FROM CourseDetails ORDER BY CASE WHEN start_time like '%AM' then 1 when start_time like '%12:00 PM%' then 2 else start_time end) WHERE days LIKE '%Sunday%'";
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = null;
        if (database != null) {
            cursor = database.rawQuery(query, null);
        }
        return cursor;
    }
}