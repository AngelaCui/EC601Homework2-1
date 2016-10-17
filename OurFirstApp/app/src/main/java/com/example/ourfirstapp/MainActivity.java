package com.example.ourfirstapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /*
        Key to send and receive data across activities
        Good practise to use package name a prefix since different apps may interacts
     */
    public final static String EXTRA_MESSAGE = "com.example.ourfirstapp.MESSAGE";
    DatabaseAdapter databaseAdapter;

    private EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseAdapter = new DatabaseAdapter(this);

        username = (EditText) findViewById(R.id.usernameEditText);
        password = (EditText) findViewById(R.id.passwordEditText);
    }

    /*
        Called when the user click on the Send Button
     */
    public void addMessage(View view)
    {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        //EditText editText = (EditText) findViewById(R.id.edit_message);
        //String message = editText.getText().toString();
        //intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void addUser(View view)
    {
        String user = username.getText().toString();
        String psswd = password.getText().toString();

        long id = databaseAdapter.insertData(user, psswd);
        if (id < 0) Toast.makeText(this, "Error: Could not insert values", Toast.LENGTH_LONG).show();
        else Toast.makeText(this, "Successfully added user", Toast.LENGTH_SHORT).show();

        username.setText("");
        password.setText("");
    }

    public void viewDetails(View view)
    {
        ArrayList<String> data = databaseAdapter.getAllData();
        // Toast.makeText(this, data, Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this, ViewDataActivity.class);
        intent.putExtra(EXTRA_MESSAGE, data);
        startActivity(intent);
    }
}

