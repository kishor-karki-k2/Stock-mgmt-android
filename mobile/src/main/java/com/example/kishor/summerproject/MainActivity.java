package com.example.kishor.summerproject;


import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;



public class MainActivity extends Activity {


    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridView gridView= (GridView)findViewById(R.id.gridview);
        gridView.setAdapter(new ImageAdapter(this));


    }

  }
