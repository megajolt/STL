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
        Button exit = findViewById(R.id.exit);

        newgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ms = new Intent(StlMenu.this, mainservice.class);
                startService(ms);
            }
        });
    }
}
