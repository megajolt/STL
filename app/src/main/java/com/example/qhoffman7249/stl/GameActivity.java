package com.example.qhoffman7249.stl;

import android.animation.ObjectAnimator;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class GameActivity extends AppCompatActivity {
    public int damage=0;


    public ProgressBar shieldBar;
    public Handler shieldHandler= new Handler();
    public int hdamage = 0;
    public Shields shields= new Shields();
    public int currentShield=100;
    public ProgressBar healthBar;
    public int health= 100;
    public int y;
    public boolean isclickedcrew;
    public int xfin = 0;
    public int xpos = 0;
    public int x;
    final TextView shieldHealth=findViewById(R.id.shieldHealth);
    final TextView mainHealth=findViewById(R.id.mainHealth);
    //random comment
    public Gun weapons=new Gun();
    public List<String> characternames;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        View myview = findViewById(R.id.view);
        myview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                x = (int)event.getX();
                y = (int)event.getY();
                if(isclickedcrew) {
                    Toast.makeText(GameActivity.this, "cordinates: x: " + x + " y:" + y, Toast.LENGTH_SHORT).show();
                    isclickedcrew = false;
                }
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_MOVE:
                    case MotionEvent.ACTION_UP:
                }
                return false;
            }

        });
        Button bastardSword=findViewById(R.id.bastardSword);
        Button pruningShears=findViewById(R.id.pruningShears);
        Button maul=findViewById(R.id.maul);
        Button halberd=findViewById(R.id.halberd);
        Button glaive=findViewById(R.id.glaive);
        halberd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                damage=weapons.halberd;

            }
        });
        pruningShears.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                damage=weapons.pruningShears;

            }
        });
        maul.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                damage=weapons.maul;

            }
        });
        glaive.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                damage=weapons.glaive;

            }
        });
        bastardSword.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                damage=10;
                checkdamage();
            }
        });
        //Status bar code
        healthBar= findViewById(R.id.healthBar);
        healthBar.setScaleY(2f);
        healthBar.setProgress(100);
        shieldBar=findViewById(R.id.shieldBar);
        shieldBar.setScaleY(2f);
        shieldBar.setProgress(100);
        Button animate = findViewById(R.id.anim);
        animate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btn = findViewById(R.id.anim);
                xfin = xpos + 100;
                ObjectAnimator animation = ObjectAnimator.ofFloat(btn, "translationX", xpos, xfin);
                xpos = xfin;
                animation.setDuration(2000);
                animation.start();
                isclickedcrew = true;
            }
        });
    }
    public void checkdamage(){
        if(damage>currentShield){
            health = health - damage;
        }
        else if(currentShield>=damage){
            currentShield = currentShield - damage;
        }
        damage = 0;
        healthBar.setProgress(health);
        shieldBar.setProgress(currentShield);
    }
}
