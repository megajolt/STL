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
        width = width * 1.2;

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
        });//some random comment
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
        /*
        * Button bastardSword = findViewById(R.id.bastardSword);
        Button pruningShears = findViewById(R.id.pruningShears);
        Button maul = findViewById(R.id.maul);
        Button halberd = findViewById(R.id.halberd);
        Button glaive = findViewById(R.id.glaive);
        Button medRoomButton = findViewById(R.id.medBay);
        Button engineRoomButton = findViewById(R.id.engine);
        Button shieldRoomButton = findViewById(R.id.shield);
        Button gunRoomButton = findViewById(R.id.gun);
        Button controlRoomButton = findViewById(R.id.control);

        halberd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                damage = weapons.halberd;
                enemycheckdamage();
            }
        });
        pruningShears.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                damage = weapons.pruningShears;
                enemycheckdamage();
            }
        });
        maul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                damage = weapons.maul;
                enemycheckdamage();
            }
        });
        glaive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                damage = weapons.glaive;
                enemycheckdamage();
            }
        });
        bastardSword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                damage = weapons.bastardSword;
                enemycheckdamage();
            }
        });
        medRoomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                coords = room.medCoords;
                coordMaker(coords);
                animate();
            }
        });
        engineRoomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                coords = room.engineCoords;
                coordMaker(coords);
                animate();
            }
        });
        shieldRoomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                coords = room.shieldCoords;
                coordMaker(coords);
                animate();
            }
        });
        gunRoomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                coords = room.gunCoords;
                coordMaker(coords);
                animate();
            }
        });
        controlRoomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                coords = room.controlCoords;
                coordMaker(coords);
                animate();
            }
        });
        * */
    }
}
