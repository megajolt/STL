package com.example.qhoffman7249.stl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StlMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stl_menu);
        Button newgame = findViewById(R.id.newGame);
        Button loadgame = findViewById(R.id.loadGame);
        Button settings = findViewById(R.id.settings);
        final Button multiplayer = findViewById(R.id.multiplayer);
        Button exit = findViewById(R.id.exit);

        newgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ng = new Intent(StlMenu.this, PopTivity.class);
                startActivity(ng);
            }
        });
        loadgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lg = new Intent(StlMenu.this, gamesearch.class);
                startActivity(lg);
            }
        });
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent s = new Intent(StlMenu.this, settings.class);
                startActivity(s);
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
                System.exit(0);
            }
        });
        multiplayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(StlMenu.this, multiplayer.class);
                startActivity(i);
            }
        });
    }
}
