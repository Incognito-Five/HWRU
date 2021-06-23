package com.example.myapplication1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Users";
    Context mContext;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase DBase) {
        DBase.execSQL("create Table users (username Text primary key, password Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DBase, int oldVersion, int newVersion) {
        DBase.execSQL("drop Table if exists users");
    }

    public Boolean insertData(String username, String password) {
        SQLiteDatabase DBase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Username", username);
        contentValues.put("Password", password);
        long result = DBase.insert("users", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean checkUsername(String username) {
        SQLiteDatabase DBase = this.getWritableDatabase();
        Cursor cursor = DBase.rawQuery("select * from users where username = ?", new String[]{username});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean checkUsernamePassword(String username, String password) {
        SQLiteDatabase DBase = this.getWritableDatabase();
        Cursor cursor = DBase.rawQuery("select * from users where username = ? and password = ?", new String[]{username, password});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean updatepass(String username, String password) {
        SQLiteDatabase DBase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Password", password);
        long result = DBase.update("users", contentValues, "username = ?", new String[]{username});
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean deleteData(String username) {
        SQLiteDatabase DBase = this.getWritableDatabase();
        Cursor cursor = DBase.rawQuery("Select * from users where username = ?", new String[]{username});
        if (cursor.getCount() > 0) {
            long result = DBase.delete("users", "username = ?", new String[]{username});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Cursor getData() {
        SQLiteDatabase DBase = this.getWritableDatabase();
        Cursor cursor = DBase.rawQuery("Select * from users", null);
        return cursor;
    }

    public void backup(String outFileName) {

        //database path
        final String inFileName = mContext.getDatabasePath(DATABASE_NAME).toString();

        try {

            File dbFile = new File(inFileName);
            FileInputStream fis = new FileInputStream(dbFile);

            // Open the empty db as the output stream
            OutputStream output = new FileOutputStream(outFileName);

            // Transfer bytes from the input file to the output file
            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }

            // Close the streams
            output.flush();
            output.close();
            fis.close();

            Toast.makeText(mContext, "Backup Completed", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(mContext, "Unable to backup database. Retry", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public void importDB(String inFileName) {

        final String outFileName = mContext.getDatabasePath(DATABASE_NAME).toString();

        try {

            File dbFile = new File(inFileName);
            FileInputStream fis = new FileInputStream(dbFile);
            // Open the empty db as the output stream
            OutputStream output = new FileOutputStream(outFileName);
            // Transfer bytes from the input file to the output file
            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }

            // Close the streams
            output.flush();
            output.close();
            fis.close();

            Toast.makeText(mContext, "Import Completed", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(mContext, "Unable to import database. Retry", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
