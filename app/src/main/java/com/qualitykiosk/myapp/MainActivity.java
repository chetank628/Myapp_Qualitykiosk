package com.qualitykiosk.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbarTop = (Toolbar) findViewById(R.id.toolbar_top);
        TextView mTitle = (TextView) toolbarTop.findViewById(R.id.toolbar_title);
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        String[] items2 = new String[]{"","About us"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(
                this,
                R.layout.support_simple_spinner_dropdown_item,
                items2
        );
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner2.setAdapter(adapter2);


    }


}
