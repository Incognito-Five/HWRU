package com.example.myapplication1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context) {
        super(context, "Users.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DBase) {
        DBase.execSQL("create Table users(username Text primary key, password Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DBase, int oldVersion, int newVersion) {
        DBase.execSQL("drop Table if exists users");
    }

    public Boolean insertData(String username, String password){
        SQLiteDatabase DBase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Username", username);
        contentValues.put("Password", password);
        long result = DBase.insert("users", null, contentValues);
        if(result == -1){
            return false;
        }
        else {
            return true;
        }
    }

    public Boolean checkUsername(String username){
        SQLiteDatabase DBase = this.getWritableDatabase();
        Cursor cursor = DBase.rawQuery("select * from users where username = ?", new String[] {username});
        if (cursor.getCount() > 0){
            return true;
        }
        else {
            return false;
        }
    }

    public Boolean checkUsernamePassword(String username, String password){
        SQLiteDatabase DBase = this.getWritableDatabase();
        Cursor cursor = DBase.rawQuery("select * from users where username = ? and password = ?", new String[] {username, password});
        if (cursor.getCount() > 0){
            return true;
        }
        else {
            return false;
        }
    }

    public Boolean updatepass(String username, String password){
        SQLiteDatabase DBase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Password", password);
        long result = DBase.update("users", contentValues, "username = ?", new String[] {username});
        if(result == -1){
            return false;
        }
        else {
            return true;
        }
    }

    public Boolean deleteData(String username){
        SQLiteDatabase DBase = this.getWritableDatabase();
        Cursor cursor = DBase.rawQuery("Select * from users where username = ?", new String[] {username});
        if (cursor.getCount() > 0){
            long result = DBase.delete("users", "username = ?", new String[] {username});
            if(result == -1){
                return false;
            }
            else {
                return true;
            }
        }
        else{
            return false;
        }
    }
}
