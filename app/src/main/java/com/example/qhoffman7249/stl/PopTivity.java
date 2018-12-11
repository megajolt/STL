package com.example.qhoffman7249.stl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

public class PopTivity extends AppCompatActivity {
    public ScrollView current;
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

        current = findViewById(R.id.weapons);

        int intwidth = (int)width;
        int intheight = (int)height;
        getWindow().setLayout(intwidth,intheight);

        Button showrooms = findViewById(R.id.secondSubM);
        showrooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                current.setVisibility(View.GONE);
                current = findViewById(R.id.rooms);
                current.setVisibility(View.VISIBLE);
            }
        });
        Button showweapons = findViewById(R.id.firstSubM);
        showweapons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                current.setVisibility(View.GONE);
                current = findViewById(R.id.weapons);
                current.setVisibility(View.VISIBLE);
            }
        });
        Button showcrew = findViewById(R.id.thirdSubM);
        showcrew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                current.setVisibility(View.GONE);
                current = findViewById(R.id.crew);
                current.setVisibility(View.VISIBLE);
            }
        });
        Button showpowerreg = findViewById(R.id.fourthSubM);
        showpowerreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int name = v.getId();
                Toast.makeText(PopTivity.this, "name: " + name, Toast.LENGTH_SHORT).show();
                current.setVisibility(View.GONE);
                current = findViewById(R.id.power);
                current.setVisibility(View.VISIBLE);
            }
        });
    }
}
