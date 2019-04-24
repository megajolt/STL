package com.example.qhoffman7249.stl;

import android.animation.ObjectAnimator;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
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
    Ship myShip;
    List<Crew> crewlist = new ArrayList<Crew>();
    private int mInterval = 5000; // 5 seconds by default, can be changed later
    private Handler mHandler;
    private Handler mHandler2;
    int playerCoolDown;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myShip = new Ship(6);
        setContentView(R.layout.activity_game);
        /*for(int i = 0; i < 5; i++) {
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
            LinearLayout linearLayout = findViewById(R.id.ourSide);
            linearLayout.addView(crewlist.get(i));
        }*/
        Button enemyShowButton = findViewById(R.id.enemyShowButton);
        enemyShowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout enemy = findViewById(R.id.enemy);
                enemy.setVisibility(View.VISIBLE);
                LinearLayout ourSide = findViewById(R.id.ourSide);
                ourSide.setVisibility(View.GONE);
            }
        });
        variableSet();
        mHandler = new Handler();
        mHandler2 = new Handler();
        startRepeatingTask();
        Intent m = new Intent(GameActivity.this, gameMusic.class);
        startService(m);
        clickerSet();
        startAnimation(2,7);
        //startAnimation();
    }
    boolean coolDown;
    int ecoolDown;
    @Override
    public void onDestroy() {
        super.onDestroy();
        stopRepeatingTask();
    }
    Runnable mStatusChecker = new Runnable() {
        @Override
        public void run() {
            try {
                if(enemyhealth > 0 && ecoolDown == 0) {
                    Weapon weapon = sysAI.getWeapon();
                    ecoolDown = weapon.coolDown;
                    checkdamage(weapon.damage);
                    //Room target = sysAI.getTarget(myShip);
                    //Toast.makeText(GameActivity.this, "room: " + target.getName(), Toast.LENGTH_SHORT).show();
                    //System.out.println(target.getName());
                    //myShip.setRoomHealth(target.getIndex(), target.getHealth() - weapon.damage);
                    //checkdamage(weapon.damage);
                }
                ecoolDown--;
            } finally {
                mHandler.postDelayed(mStatusChecker, 100);
            }
        }
    };
    //comment to add
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
                    Toast.makeText(GameActivity.this, "elapsed", Toast.LENGTH_LONG).show();
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
    public void clickerSet() {
        //damage buttons
        bastardSword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!coolDown){
                damage=weapons.peaShooter;
                    System.out.println("damage="+damage);
                enemycheckdamage();
                coolDown=true;
                coolDownTime=1000;
                coolDown();
            }
            }
        });
        halberd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!coolDown){
                    damage=weapons.maul;
                    System.out.println("damage="+damage);
                    enemycheckdamage();
                    coolDown=true;
                    coolDownTime=2500;
                    coolDown();
                }

            }
        });
        maul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!coolDown){
                    damage=weapons.halberd;
                    System.out.println("damage="+damage);
                    enemycheckdamage();
                    coolDown=true;
                    coolDownTime=5000;
                    coolDown();
                }

            }
        });
    }
    int activerooms = 5;
    int oxygenLevel = 100;
    public void checkdamage(int enemydamage){
        Room target = sysAI.getTarget(myShip);
        if(target.getHealth() < 1){
            activerooms--;
        }
        if(activerooms < 1 && target.getHealth() < 1){
            Toast.makeText(this, "your dead", Toast.LENGTH_SHORT).show();
        }
        //Toast.makeText(GameActivity.this, "room: " + target.getName(), Toast.LENGTH_SHORT).show();
        System.out.println(target.getName());
        myShip.setRoomHealth(target.getIndex(), target.getHealth() - enemydamage);
        int health = myShip.getHealth();
        int currentShield = myShip.getShield();
        //first check damage relative to enemy action
        //test = test - 10;
        if(enemydamage>currentShield){
            enemydamage = enemydamage - currentShield;
            currentShield = 0;
            health = health - enemydamage;
            System.out.println("shield="+currentShield);
            System.out.println("health="+health);
        }
        else if(currentShield>=enemydamage){
            currentShield = currentShield - enemydamage;
            System.out.println("shield="+currentShield);
            System.out.println("health="+health);
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
        if(currentShield < 1){
            //oxygenLevel -= 20;
            //oxygenCheck(oxygenLevel);
        }
        if(health<100){
            damaged=true;
        }
        damage = 0;
        myShip.setHealth(health);
        myShip.setShield(currentShield);
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
    //int oxygenLevel = 100;

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
        if(oxygenLevel < 1){
            Intent i = new Intent(GameActivity.this, StlMenu.class);
            Toast.makeText(this, "you suffocated", Toast.LENGTH_SHORT).show();
            startActivity(i);
        }
    }
    public void coolDown(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                //coolDown=false;
            }
        }, coolDownTime);
    }
}
