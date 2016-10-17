package com.example.ourfirstapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shantanubobhate on 10/7/16.
 */

public class DatabaseAdapter {

    DatabaseHelper helper;

    public DatabaseAdapter(Context context)
    {
        helper = new DatabaseHelper(context);
    }

    public long insertData(String name, String password)
    {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.NAME, name);
        contentValues.put(DatabaseHelper.PASSWORD, password);
        long id = db.insert(DatabaseHelper.TABLE_NAME, null, contentValues);
        return id;
    }

    public ArrayList<String> getAllData()
    {
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] columns = { DatabaseHelper.UID, DatabaseHelper.NAME, DatabaseHelper.PASSWORD };
        Cursor cursor = db.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);

        StringBuffer stringBuffer = new StringBuffer();
        ArrayList<String> data = new ArrayList<>();
        while(cursor.moveToNext()) {
            int index1 = cursor.getColumnIndex(DatabaseHelper.UID);
            int cid = cursor.getInt(index1);
            String name = cursor.getString(index1 + 1);
            String psswd = cursor.getString(index1 + 2);
            stringBuffer.append(cid + " " + name + " " + psswd + "\n");
            data.add(name);
        }

        return data;
    }

    public String getData(String name)
    {
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] columnns = { DatabaseHelper.NAME, DatabaseHelper.PASSWORD };
        String[] selectionArgs = { name };
        Cursor cursor = db.query(DatabaseHelper.TABLE_NAME, columnns, DatabaseHelper.NAME + " =?", selectionArgs, null, null, null);

        StringBuffer stringBuffer = new StringBuffer();
        while (cursor.moveToNext()) {
            int index = cursor.getColumnIndex(DatabaseHelper.PASSWORD);
            String psswd = cursor.getString(index);
            stringBuffer.append(psswd + "\n");
        }

        return stringBuffer.toString();
    }

    public int updateName(String oldName, String newName)
    {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.NAME, newName);
        String[] whereArgs = { oldName };
        int count = db.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper.NAME + "=?", whereArgs);
        return count;
    }

    public int deleteRow(String name)
    {
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] whereArgs = { name };
        int count = db.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper.NAME + "=?", whereArgs);
        return count;
    }

    static class DatabaseHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "mydatabase";
        private static final String TABLE_NAME = "myname";
        private static final int DATABASE_VERSION = 1;
        private static final String UID = "_id";
        private static final String POLICY_NUMBER = "";
        private static final String NAME = "Name";
        private static final String PASSWORD = "Password";
        private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
                + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + POLICY_NUMBER + " VARCHAR(255), "
                + NAME + " VARCHAR(255), "
                + PASSWORD + " VARCHAR(255));";
        private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
        private Context context;

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // NOTE: Make changes to avoid SQL Injection
            try {
                db.execSQL(CREATE_TABLE);
            } catch (SQLException ex) {
                Toast.makeText(context, ex.toString(), Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                db.execSQL(DROP_TABLE);
                onCreate(db);
            } catch (SQLException ex) {
                Toast.makeText(context, ex.toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
