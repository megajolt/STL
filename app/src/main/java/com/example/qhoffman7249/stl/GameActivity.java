package com.example.qhoffman7249.stl;

import android.animation.ObjectAnimator;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.graphics.Path;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends variables{
    Path cToEPath= new Path();
    Path cToSPath=new Path();
    Path cToGPath=new Path();
    Path cToIPath=new Path();
    List<Crew> crewlist = new ArrayList<Crew>();
    private int mInterval = 5000; // 5 seconds by default, can be changed later
    private Handler mHandler;
    private Handler mHandler2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        for(int i = 0; i < 5; i++) {
            crewlist.add(new Crew(this, i));
            final int b = i;
            crewlist.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent r = new Intent(GameActivity.this, PopTivity.class);
                    r.putExtra("crew-number", b);
                    startActivity(r);
                }
            });
            LinearLayout linearLayout = findViewById(R.id.linearLayout);
            linearLayout.addView(crewlist.get(i));
        }
        variableSet();
        mHandler = new Handler();
        mHandler2 = new Handler();
        startRepeatingTask();
        Intent m = new Intent(GameActivity.this, music.class);
        //startService(m);
        clickerSet();
        startAnimation(2,7);
        //startAnimation();
        //
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        stopRepeatingTask();
    }
    Runnable mStatusChecker = new Runnable() {
        @Override
        public void run() {
            try {
                enemydamage = sysAI.getDamage();
                target = sysAI.getTarget();
                if(enemyhealth > 0) {
                    checkdamage();
                    checkTarget();
                }
            } finally {
                mHandler.postDelayed(mStatusChecker, mInterval);
            }
        }
    };
    int randomint = 0;
    int animtimes = 200;
    int rotation = 0;
    int time = 0;
    Runnable animation = new Runnable() {
        @Override
        public void run() {
            //put frame change code here
            try{
                if(randomint >= animtimes){
                    //rotate and set frame
                    Toast.makeText(GameActivity.this, "elapsed", Toast.LENGTH_SHORT).show();
                    time = 0;
                }
                else{
                    time++;
                }
                randomint++;
            }finally {
                mHandler2.postDelayed(animation, 12);
            }
        }
    };
    public void checkTarget(){
        roomHealth[target] = enemydamage;
        if(roomHealth[
                target] < 1){
            roomEnabled[target] = false;
        }
        else{
            roomEnabled[target] = true;
        }
    }
    public void startRepeatingTask() {
        mStatusChecker.run();
    }
    public void stopRepeatingTask() {
        mHandler.removeCallbacks(mStatusChecker);
        mHandler = null;
    }
    public void stopAnimation(){
        mHandler2.removeCallbacks(animation);
    }
    public void startAnimation(int times, int angle){
        animtimes = times;
        rotation = angle;
        stopAnimation();
        animation.run();
    }
    /*xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx - set onclick listeners - xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx*/
    public void clickerSet(){
        //damage buttons
        halberd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                damage = weapons.halberd;
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
        bastardSword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                damage = weapons.bastardSword;
                enemycheckdamage();
            }
        });

    }
    int test = 100;
    public void checkdamage(){
        //first check damage relative to enemy action
        //test = test - 10;
        if(enemydamage>currentShield){
            enemydamage = enemydamage - currentShield;
            currentShield = 0;
            health = health - enemydamage;
        }
        else if(currentShield>=enemydamage){
            currentShield = currentShield - enemydamage;
        }
        if(health < 50 && health > 25){
           healthBar.getProgressDrawable().setColorFilter(Color.YELLOW, android.graphics.PorterDuff.Mode.SRC_IN);
        }
        if(health < 25){
            healthBar.getProgressDrawable().setColorFilter(Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);
        }
        if(health <= 0){
            Toast.makeText(GameActivity.this, "You Dead", Toast.LENGTH_SHORT).show();
            stopRepeatingTask();
            Intent r = new Intent(GameActivity.this, StlMenu.class);
            startActivity(r);
        }
        if(health<100){
            damaged=true;
        }
        damage = 0;
        healthBar.setProgress(health);
        shieldBar.setProgress(currentShield);
    }
    //code to change enemy health bar
    public void enemycheckdamage(){
        Toast.makeText(GameActivity.this, "enemycheckdamage ran", Toast.LENGTH_SHORT).show();
        if(damage>enemycurrentShield){
            damage -= enemycurrentShield;
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
    //call this with whatever changes oxygen
    public void oxygenCheck(int oxygenLevel) {
        if (oxygenLevel < 50) {
            oxygenEmergency.setVisibility(View.VISIBLE);
        }
        if (oxygenLevel >= 50) {
            oxygenEmergency.setVisibility(View.GONE);
            largerOxygenEmergency.setVisibility(View.GONE);
        }
        if (oxygenLevel < 25) {
            oxygenEmergency.setVisibility(View.GONE);
            largerOxygenEmergency.setVisibility(View.VISIBLE);
        }
    }
}
