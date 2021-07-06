package com.example.myapplication1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TimetableDBHelper extends SQLiteOpenHelper {

    public TimetableDBHelper(Context context) {
        super(context, "Courses.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase coursedb) {
        coursedb.execSQL("create Table CourseDetails(course_name TEXT primary key, course_code TEXT, professor TEXT, start_time TEXT, end_time TEXT, description TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase coursedb, int i, int i1) {
        coursedb.execSQL("drop Table if exists CourseDetails");
    }

    public Boolean AddCourseData(String course_name, String course_code, String professor, String start_time, String end_time, String description) {
        SQLiteDatabase coursedb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("course_name", course_name);
        contentValues.put("course_code", course_code);
        contentValues.put("professor", professor);
        contentValues.put("start_time", start_time);
        contentValues.put("end_time", end_time);
        contentValues.put("description", description);
        long result = coursedb.insert("CourseDetails", null, contentValues);
        return result != -1;
    }

    public Boolean UpdateCourseData(String course_name, String course_code, String professor, String start_time, String end_time, String description) {
        SQLiteDatabase coursedb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("course_code", course_code);
        contentValues.put("professor", professor);
        contentValues.put("start_time", start_time);
        contentValues.put("end_time", end_time);
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

    public Cursor getdata() {
        SQLiteDatabase coursedb = this.getWritableDatabase();
        Cursor cursor = coursedb.rawQuery("Select * from CourseDetails", null);
        return cursor;
    }
}