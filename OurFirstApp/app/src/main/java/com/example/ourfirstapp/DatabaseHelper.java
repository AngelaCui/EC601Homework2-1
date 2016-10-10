package com.example.ourfirstapp;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by shantanubobhate on 10/7/16.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private  static final String DATABASE_NAME = "mydatabase";
    private static final String TABLE_NAME = "myname";
    private static final int DATABASE_VERSION = 1;
    private static final String UID = "_id";
    private static final String NAME = "Name";
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " VARCHAR(255));";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    private Context context;

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // NOTE: Make changes to avoid SQL Injection
        try{
            db.execSQL(CREATE_TABLE);
        }
        catch (SQLException ex)
        {
            Toast.makeText(context, ex.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL(DROP_TABLE);
            onCreate(db);
        }
        catch (SQLException ex) {
            Toast.makeText(context, ex.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public long insert
}
