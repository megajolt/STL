package com.example.qhoffman7249.stl;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class GameActivity extends AppCompatActivity {
    public int damage=0;

    public ProgressBar shieldBar;
    public Handler shieldHandler= new Handler();
    public int hdamage = 0;
    public Shields shields= new Shields();
    public int currentShield=100;
    public ProgressBar healthBar;
    public int health= 100;
    public int enemycurrentShield = 100;
    public int enemyhealth = 100;
    public int y=0;
    public float ypos=0;
    public boolean isclickedcrew;
    public float xfin = 0;
    public float xpos = 0;
    public int x=0;
    public int manHealth=99;
    public MediaPlayer player;
    public boolean menvis = false;
    public ProgressBar enemyShieldBar;
    public ProgressBar enemyHealthBar;
    //random comment
    public Gun weapons=new Gun();
    public List<String> characternames;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Intent m = new Intent(GameActivity.this, music.class);
        //startService(m);
        enemyShieldBar = findViewById(R.id.enemyShieldBar);
        enemyHealthBar = findViewById(R.id.enemyHealthBar);
        enemyHealthBar.setProgress(100);
        enemyShieldBar.setProgress(100);
        enemyShieldBar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.shieldbar),android.graphics.PorterDuff.Mode.SRC_IN);
        enemyHealthBar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.healthbar), android.graphics.PorterDuff.Mode.SRC_IN);
        enemyHealthBar.setScaleY(2f);
        enemyShieldBar.setScaleY(2f);
        Button openMenu = findViewById(R.id.menu);
        openMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout Menu = findViewById(R.id.Menu);
                if(!menvis) {
                    Menu.setVisibility(View.VISIBLE);
                    menvis = true;
                }
                else if(menvis){
                    Menu.setVisibility(View.GONE);
                    menvis = false;
                }
            }
        });
        View myview = findViewById(R.id.view);
        myview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                x = (int)event.getX();
                y = (int)event.getY();
                Toast.makeText(GameActivity.this, "cordinates: x: " + x + " y:" + y, Toast.LENGTH_SHORT).show();
                System.out.println(x+", " + y);
                if(isclickedcrew) {
                    Toast.makeText(GameActivity.this, "cordinates: x: " + x + " y:" + y, Toast.LENGTH_SHORT).show();
                    animate();
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
                enemycheckdamage();
            }
        });
        pruningShears.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                damage=weapons.pruningShears;
                enemycheckdamage();
            }
        });
        maul.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                damage=weapons.maul;
                enemycheckdamage();
            }
        });
        glaive.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                damage=weapons.glaive;
                enemycheckdamage();
            }
            //fuck
        });
        bastardSword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                damage=weapons.bastardSword;
                enemycheckdamage();
            }
        });

        //Status bar code
        healthBar= findViewById(R.id.healthBar);
        healthBar.setScaleY(2f);
        healthBar.setProgress(100);
        //healthBar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.healthbar), android.graphics.PorterDuff.Mode.SRC_IN);
        //shieldBar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.shieldbar), android.graphics.PorterDuff.Mode.SRC_IN);
        shieldBar=findViewById(R.id.shieldBar);
        shieldBar.setScaleY(2f);
        shieldBar.setProgress(100);

       Button animate = findViewById(R.id.crew1);
        animate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Button btn = findViewById(R.id.crew1);
                xpos = btn.getX();
                ypos = btn.getY();
                System.out.println("xpos: "+xpos+ " ypos: "+ypos);
                isclickedcrew = true;
            }
        });
    }
   /* public void checkdamage(){
        //first check damage relative to enemy action
        if(damage>currentShield){
            damage = damage - currentShield;
            currentShield = 0;
            health = health - damage;
        }
        else if(currentShield>=damage){
            currentShield = currentShield - damage;
        }
        if(health < 50 && health > 25){
           healthBar.getProgressDrawable().setColorFilter(Color.YELLOW, android.graphics.PorterDuff.Mode.SRC_IN);
        }
        if(health < 25){
            healthBar.getProgressDrawable().setColorFilter(Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);
        }
        if(health <= 0){
            Toast.makeText(GameActivity.this, "You Dead", Toast.LENGTH_SHORT).show();
            Intent r = new Intent(GameActivity.this, StlMenu.class);
            startActivity(r);
        }
        damage = 0;
        healthBar.setProgress(health);
        shieldBar.setProgress(currentShield);
    }*/
    public void enemycheckdamage(){
        Toast.makeText(GameActivity.this, "enemycheckdamage ran", Toast.LENGTH_SHORT).show();
        if(damage>enemycurrentShield){
            damage = damage - enemycurrentShield;
            enemycurrentShield = 0;
            enemyhealth = enemyhealth - damage;
        }
        else if(enemycurrentShield>=damage){
            enemycurrentShield = enemycurrentShield - damage;
        }
        if(enemyhealth < 50 && enemyhealth > 25){
            enemyHealthBar.getProgressDrawable().setColorFilter(Color.YELLOW, android.graphics.PorterDuff.Mode.SRC_IN);
        }
        if(enemyhealth < 25){
            enemyHealthBar.getProgressDrawable().setColorFilter(Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);
        }
        if(enemyhealth <= 0){
            Toast.makeText(GameActivity.this, "You Win!!", Toast.LENGTH_SHORT).show();
        }
        damage = 0;
        enemyHealthBar.setProgress(enemyhealth);
        enemyShieldBar.setProgress(enemycurrentShield);
    }
    public void animate(){
        Button btn = findViewById(R.id.crew1);
        float cxp = btn.getX();
        float cyp = btn.getY();
        y=y-204;
        ObjectAnimator animationx = ObjectAnimator.ofFloat(btn, "translationX", cxp, x);
        Toast.makeText(GameActivity.this, "x: " + btn.getX() + "y: " + btn.getY(), Toast.LENGTH_SHORT).show();
        animationx.setDuration(2000);
        animationx.start();
        ObjectAnimator animatory = ObjectAnimator.ofFloat(btn, "translationY", cyp, y);
        animatory.setDuration(2000);
        animatory.start();
        isclickedcrew = true;

    }

    @Override
    protected void onStop() {
        Intent m = new Intent(GameActivity.this, music.class);
        stopService(m);
        super.onStop();
    }
}
