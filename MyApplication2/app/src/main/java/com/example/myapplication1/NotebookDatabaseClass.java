package com.example.myapplication1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class NotebookDatabaseClass extends SQLiteOpenHelper {

    Context context;
    private static final String DatabaseName="MyNotebook";
    private static final int DatabaseVersion=1;

    //Database Table
    private static final String TableName="mynotebook";
    private static final String ColumnId="id"; //primary key
    private static final String ColumnTitle="title"; //stores title of the notes
    private static final String ColumnContents="content";//stores contents of the notes

    public NotebookDatabaseClass(@Nullable Context context) {
        super(context, DatabaseName, null, DatabaseVersion);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Database Table
        String query= "CREATE TABLE " + TableName +
                " (" + ColumnId + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ColumnTitle + " TEXT, " +
                ColumnContents + " TEXT);";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TableName);
        onCreate(db);
    }

    //method for adding new data
    void addNotes(String title, String content)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues cv=new ContentValues();

        cv.put(ColumnTitle,title);
        cv.put(ColumnContents,content);

        long resultValue = db.insert(TableName,null,cv);

        if (resultValue == -1)
        {
            Toast.makeText(context,"Note Unsaved",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context,"Note Added Successfully",Toast.LENGTH_SHORT).show();
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

    //method for updating notes from the database
    void updateNotes(String title, String content, String id){
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(ColumnTitle,title);
        cv.put(ColumnContents,content);

        long result = database.update(TableName, cv, "id=?", new String[]{id});
        if (result == -1){
            Toast.makeText(context, "Note Update Failed", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Note Updated Successfully", Toast.LENGTH_SHORT).show();
        }
    }

    //method for deleting a single note from the database
    public void deleteSingleItem(String id){
        SQLiteDatabase database = this.getWritableDatabase();

        long result = database.delete(TableName, "id=?", new String[]{id});
        if (result == -1){
            Toast.makeText(context, "Note Not Deleted", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Note Deleted Successfully", Toast.LENGTH_SHORT).show();
        }
    }
}
