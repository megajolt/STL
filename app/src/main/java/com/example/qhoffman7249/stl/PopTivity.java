package com.example.qhoffman7249.stl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

public class PopTivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_tivity);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        double width = dm.widthPixels;
        double height = dm.heightPixels;

        height = height * .8;
        width = width * .6;

        int intwidth = (int)width;
        int intheight = (int)height;
        getWindow().setLayout(intwidth,intheight);

        Button showrooms = findViewById(R.id.secondSubM);
        showrooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScrollView weapons = findViewById(R.id.weapons);
                weapons.setVisibility(View.GONE);
                ScrollView rooms = findViewById(R.id.rooms);
                rooms.setVisibility(View.VISIBLE);
            }
        });
        Button showweapons = findViewById(R.id.firstSubM);
        showweapons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScrollView rooms = findViewById(R.id.rooms);
                rooms.setVisibility(View.GONE);
                ScrollView weapons = findViewById(R.id.weapons);
                weapons.setVisibility(View.VISIBLE);
            }
        });
    }
}
