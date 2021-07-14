package com.example.myapplication1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class BMIDatabase extends SQLiteOpenHelper {

    private Context context;
    private static final String DatabaseName="BMI.db";
    private static final int DatabaseVersion=1;

    //Databasetable
    private static final String TableName="db";
    private static final String ColumnId="bmiid";
    private static final String ColumnResult="bmiresult";
    private static final String ColumnCategory="bmicategory";


    public BMIDatabase (@Nullable Context context) {
        super(context, DatabaseName, null, DatabaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Database Table
        String query= "CREATE TABLE " + TableName +
                " (" + ColumnId + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ColumnResult + " FLOAT," +
                ColumnCategory + " TEXT);";
        db.execSQL(query);

    }

    /**
     * Called when the database needs to be upgraded. The implementation
     * should use this method to drop tables, add tables, or do anything else it
     * needs to upgrade to the new schema version.
     *
     * <p>
     * The SQLite ALTER TABLE documentation can be found
     * <a href="http://sqlite.org/lang_altertable.html">here</a>. If you add new columns
     * you can use ALTER TABLE to insert them into a live table. If you rename or remove columns
     * you can use ALTER TABLE to rename the old table, then create the new table and then
     * populate the new table with the contents of the old table.
     * </p><p>
     * This method executes within a transaction.  If an exception is thrown, all changes
     * will automatically be rolled back.
     * </p>
     *
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TableName);
        onCreate(db);
    }


    void addresult(float result, String category)//inclu
    {
        SQLiteDatabase db=this.getWritableDatabase();
        // get data from the activity and pass it thru the table
        ContentValues cv=new ContentValues();

        cv.put(ColumnResult,result);
        cv.put(ColumnCategory,category);

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

    Cursor readAllData() {

        //will get all the notes inside the table
        String query = "SELECT * FROM " + TableName;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor=null;
        if(db!=null)
        {
            cursor = db.rawQuery(query,null);
        }
        return cursor;
    }

    void deleteAll() {
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("DELETE FROM " + TableName);
    }
}
