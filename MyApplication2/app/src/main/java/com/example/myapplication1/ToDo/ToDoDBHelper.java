package com.example.myapplication1.ToDo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ToDoDBHelper extends SQLiteOpenHelper {

    Context context;
    private SQLiteDatabase toDoDB;

    //Database Table
    private static final String DatabaseName="toDoDatabase";
    private static final String TableName="toDoTable";
    private static final String ColumnId="ID";
    private static final String ColumnTask="TASK";
    private static final String ColumnStatus="STATUS";

    public ToDoDBHelper(@Nullable Context context) {
        super(context, DatabaseName, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase toDoDB) {
        //Database Table
        toDoDB.execSQL("CREATE TABLE IF NOT EXISTS " + TableName + "(ID INTEGER PRIMARY KEY AUTOINCREMENT , TASK TEXT , STATUS INTEGER)");}

    @Override
    public void onUpgrade(SQLiteDatabase toDoDB, int oldVersion, int newVersion) {
        toDoDB.execSQL("DROP TABLE IF EXISTS " + TableName);
        onCreate(toDoDB);
    }

    //method for inserting new task
    public void addTask(ToDoModel model){
        toDoDB = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ColumnTask,model.getTask());
        values.put(ColumnStatus,0);
        long result = toDoDB.insert(TableName,null, values);

        if (result == -1)
        {
            Toast.makeText(context,"Task Unsaved",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context,"Task Added Successfully",Toast.LENGTH_SHORT).show();
        }
    }

    //method for updating tasks
    public void updateTask(int id,String task){
        toDoDB = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ColumnTask, task);
        long result = toDoDB.update(TableName, values, "ID=?", new String[]{String.valueOf(id)});

        if (result == -1){
            Toast.makeText(context, "Task Update Failed", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Task Updated Successfully", Toast.LENGTH_SHORT).show();
        }
    }

    //method for updating status
    public void updateStatus(int id, int status){
        toDoDB = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ColumnStatus, status);
        toDoDB.update(TableName, values, "ID=?", new String[]{String.valueOf(id)});
    }

    //method for deleting task
    public void deleteTask(int id){
        toDoDB = this.getWritableDatabase();
        long result = toDoDB.delete(TableName,"ID=?", new String[]{String.valueOf(id)});

        if (result == -1){
            Toast.makeText(context, "Task Not Deleted", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Task Deleted Successfully", Toast.LENGTH_SHORT).show();
        }
    }

    //method for getting all tasks
    public List<ToDoModel> getAllTasks(){
        toDoDB = this.getWritableDatabase();
        Cursor cursor = null;

        List<ToDoModel> modelList = new ArrayList<>();

        toDoDB.beginTransaction();
        try{cursor = toDoDB.query(TableName, null, null, null,null, null, null);
            if (cursor!=null){
                if (cursor.moveToFirst()){
                    do{
                        ToDoModel task = new ToDoModel();
                        task.setId(cursor.getInt(cursor.getColumnIndex(ColumnId)));
                        task.setTask(cursor.getString(cursor.getColumnIndex(ColumnTask)));
                        task.setStatus(cursor.getInt(cursor.getColumnIndex(ColumnStatus)));
                        modelList.add(task);

                    }while (cursor.moveToNext());
                }
            }
        }finally {
            toDoDB.endTransaction();
            cursor.close();
        }
        return modelList;
    }
}
