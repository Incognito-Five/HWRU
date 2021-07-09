package com.example.myapplication1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class BMIDatabaseClass extends SQLiteOpenHelper {

    //initialize contents
    Context context;
    private static final String DatabaseName="MyBMI";
    private static final int DatabaseVersion=1;

    private static final String TableName="mybmi";
    private static final String ColumnId="id";
    private static final String ColumnBMIResult="bmiresult";
    private static final String ColumnBMICategory="bmicategory";

    public BMIDatabaseClass(@Nullable Context context) {
        super(context, DatabaseName, null, DatabaseVersion);
        this.context=context;
    }

    public BMIDatabaseClass(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String query="CREATE TABLE " + TableName +
        " (" + ColumnId + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ColumnBMIResult + " TEXT, " +
                ColumnBMICategory + " TEXT);";
        db.execSQL(query);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TableName);
        onCreate(db);
    }

    //method for adding new data
    void addBMI(String bmiresult, String bmicategory)
    {
        SQLiteDatabase db=this.getReadableDatabase();

        ContentValues cv=new ContentValues();

        cv.put(ColumnBMIResult,bmiresult);
        cv.put(ColumnBMICategory,bmicategory);

        long resultValue = db.insert(TableName,null,cv);

        if (resultValue == -1)
        {
            Toast.makeText(context,"BMI Unsaved",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context,"BMI Added Successfully",Toast.LENGTH_SHORT).show();
        }
    }

    //function to get all the notes from the database
    Cursor readAllData()
    {
        //will get all the notes inside the table
        String query = "SELECT * FROM " + TableName;
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor=null;
        if(database!=null)
        {
            cursor = database.rawQuery(query,null);
        }
        return cursor;
    }

    //method for deleting all notes from the database
    void deleteAllNotebooks(){
        SQLiteDatabase database = this.getWritableDatabase();
        String query = "DELETE FROM " + TableName;
        database.execSQL(query);
    }

    //method for deleting a single note from the database
    public void deleteSingleItem(String id){
        SQLiteDatabase database = this.getWritableDatabase();

        long result = database.delete(TableName, "id=?", new String[]{id});
        if (result == -1){
            Toast.makeText(context, "BMI Not Deleted", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "BMI Deleted Successfully", Toast.LENGTH_SHORT).show();
        }
    }
}
