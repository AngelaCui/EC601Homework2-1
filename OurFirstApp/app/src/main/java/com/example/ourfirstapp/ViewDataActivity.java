package com.example.ourfirstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewDataActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.example.ourfirstapp.MESSAGE";

    private ListView listView;
    ArrayList<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);

        Intent intent = getIntent();
        data = intent.getStringArrayListExtra(EXTRA_MESSAGE);

        listView = (ListView) findViewById(R.id.listView);

        // Toast.makeText(this, data.length, Toast.LENGTH_SHORT).show();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        listView.setAdapter(adapter);
    }


}
