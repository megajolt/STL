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
import android.widget.Toast;
import android.graphics.Path;

public class GameActivity extends variables{
    Path cToEPath= new Path();
    Path cToSPath=new Path();
    Path cToGPath=new Path();
    Path cToIPath=new Path();
    private int mInterval = 5000; // 5 seconds by default, can be changed later
    private Handler mHandler;
    private Handler mHandler2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
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
                    if(time == 0){

                    }
                    if(time == 1){

                    }
                    if(time == 2){

                    }
                    if(time == 3){

                    }
                    if(time == 4){

                    }
                    if(time == 5){

                    }
                    if(time == 6){

                    }
                    if(time == 7){

                    }
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
        openMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menubool = true;
                Intent i = new Intent(GameActivity.this, PopTivity.class);
                startActivity(i);
            }
        });
        View myview = findViewById(R.id.view);
        myview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    xfin=event.getX();
                    yfin=event.getY();
                    Toast.makeText(GameActivity.this, "coordinates: x: " + xfin + " y:" + yfin, Toast.LENGTH_LONG).show();
                }
                return true;
            }
        });

        float cxp = crewMan.getX();
        float cyp = crewMan.getY();
        floatY=floatY-204;
        ObjectAnimator animationx = ObjectAnimator.ofFloat(crewMan, "translationX", cxp, floatX);
        animationx.setDuration(2000);
        animationx.start();
        ObjectAnimator animatory = ObjectAnimator.ofFloat(crewMan, "translationY", cyp, floatY);
        animatory.setDuration(2000);
        animatory.start();
        //damage buttons
        /*halberd.setOnClickListener(new View.OnClickListener() {
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
        });*/
        //Infirmary: X:534.2578125 y: 785.79052734375
        // Shield: X: 557.2265625  y:544.563720703125
        //Gun: X: 653.320315 y: 544.563720703125
        //Control: X: 872.40234375 y: 624.654052734375
        //Engine: X:617.28515625 y: 606.62548828125
        crewMan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xpos=crewMan.getX();
                ypos=crewMan.getY();
                //if the clicked crew member is in the control room
                Clicked[4]=true;
                if(xpos==872.40234375&&ypos==412.654052734375){
                    if(Clicked[3]==true){
                        //runs animations from control room to infirmary
                        cToIPath.moveTo((float)872.40234375,(float)624.654052734375);
                        cToIPath.lineTo((float)657.28125,(float)616.64501953125);
                        cToIPath.lineTo((float)657.28125,(float)702.700927734375);
                        cToIPath.lineTo((float)611.25,(float)702.700927734375);
                        cToIPath.lineTo((float)553.2421875,(float)702.700927734375);
                        cToIPath.lineTo((float)553.2421875,(float)785.79052734375);
                        cToIPath.offset(-10,-10);
                        ObjectAnimator.ofFloat(crewMan,crewMan.X,crewMan.Y,cToIPath).setDuration(4000).start();
                        Clicked[3]=false;
                    }
                    else if(Clicked[2]==true){
                        //control room to gun room
                        cToGPath.moveTo((float)872.40234375,(float)624.654052734375);
                        cToGPath.lineTo((float)657.28125,(float)616.64501953125);
                        cToGPath.lineTo((float)657.28125,(float)712.700927734375);
                        cToGPath.lineTo((float)654.28125,(float)744.7626953125);
                        cToGPath.offset(-10,-10);
                        ObjectAnimator.ofFloat(crewMan,crewMan.X,crewMan.Y,cToGPath).setDuration(4000).start();
                        Clicked[2]=false;
                    }
                    else if(Clicked[4]==true){
                        //control room to shield room
                        cToSPath.moveTo((float)872.40234375,(float)624.654052734375);
                        cToSPath.lineTo((float)657.28125,(float)616.64501953125);
                        cToSPath.lineTo((float)657.28125,(float)562.59228515625);
                        cToSPath.lineTo((float)658.30078125,(float)538.565185546875);
                        cToSPath.lineTo((float)762.363281,(float)538.565185546875);
                        cToSPath.offset(-10,-10);
                        ObjectAnimator.ofFloat(crewMan,crewMan.X,crewMan.Y,cToSPath).setDuration(20000).start();
                        Clicked[4]=false;
                    }
                    else if(Clicked[1]==true){
                        //control room engine room
                        cToEPath.moveTo((float)872.40234375,(float)616.654052734375);
                        cToEPath.lineTo((float)663.28125,(float)616.64501953125);
                        cToEPath.lineTo((float)611.25,(float)616.64501953125);
                        cToEPath.lineTo((float)611.25,(float)584.60888671875);
                        cToEPath.offset(-10,-10);
                        ObjectAnimator.ofFloat(crewMan,crewMan.X,crewMan.Y,cToEPath).setDuration(4000).start();
                        Clicked[1]=false;
                    }
                }
                //Infirmary
                if(xpos==553.2421875&&ypos==785.79052734375){
                    if(Clicked[0]==true){
                        //infirmary to control
                        cToIPath.moveTo((float)553.2421875,(float)785.79052734375);
                        cToIPath.lineTo((float)553.2421875,(float)702.700927734375);
                        cToIPath.lineTo((float)611.25,(float)702.700927734375);
                        cToIPath.lineTo((float)657.28125,(float)702.700927734375);
                        cToIPath.lineTo((float)657.28125,(float)616.64501953125);
                        cToIPath.lineTo((float)872.40234375,(float)624.654052734375);
                        cToIPath.offset(-10,-10);
                        ObjectAnimator.ofFloat(crewMan,crewMan.X,crewMan.Y,cToIPath).setDuration(4000).start();
                        Clicked[0]=false;
                    }
                    else if(Clicked[2]==true){
                        //infirmary to gun room
                        Clicked[2]=false;
                    }
                    else if(Clicked[4]==true){
                        //infirmary to shield room
                        Clicked[4]=false;
                    }
                    else if(Clicked[1]==true){
                        //infirmary to engine room
                        Clicked[1]=false;
                    }
                }
                //shield
                if (xpos==557.2265625&&ypos==544.563720703125){
                    if(Clicked[2]==true){
                        //shield room to gun room
                        Clicked[2]=false;
                    }

                    else if(Clicked[1]==true){
                        //shield room engine room
                        Clicked[1]=false;
                    }
                    else if(Clicked[3]==true){
                        //shield room to infirmary
                        Clicked[3]=false;
                    }
                    else if(Clicked[0]==true){
                        //shield to control
                        Clicked[0]=false;
                    }
                }
                //gun
                if(xpos==653.320315&&ypos==764.7626953125){
                    if(Clicked[0]==true){
                        //gun to control
                        cToGPath.moveTo((float)654.28125,(float)744.7626953125);
                        cToGPath.lineTo((float)657.28125,(float)712.700927734375);
                        cToGPath.lineTo((float)657.28125,(float)616.64501953125);
                        cToGPath.lineTo((float)872.40234375,(float)624.654052734375);
                        cToGPath.offset(-10,-10);
                        ObjectAnimator.ofFloat(crewMan,crewMan.X,crewMan.Y,cToGPath).setDuration(4000).start();
                        Clicked[0]=false;
                    }
                    else if(Clicked[1]==true){
                        //gun room engine room
                        Clicked[1]=false;
                    }
                    else if(Clicked[3]==true){
                        //gun room to infirmary
                        Clicked[3]=false;
                    }
                    else if(Clicked[4]==true){
                        //gun room to shield room
                        Clicked[4]=false;
                    }
                }
                //engine
                if (xpos==617.28515625&&ypos==606.62548828125){
                    if(Clicked[3]==true){
                        //engine room to infirmary
                        Clicked[3]=false;
                    }
                    else if(Clicked[2]==true){
                        //engine room to gun room
                        Clicked[2]=false;
                    }
                    else if(Clicked[4]==true){
                        //engine room to shield room
                        Clicked[4]=false;
                    }
                    else if(Clicked[0]==true){
                        //engine to control
                        cToEPath.moveTo((float)611.25,(float)584.60888671875);
                        cToEPath.lineTo((float)611.25,(float)616.64501953125);
                        cToEPath.lineTo((float)663.28125,(float)616.64501953125);
                        cToEPath.lineTo((float)872.40234375,(float)616.654052734375);
                        cToEPath.offset(-10,-10);
                        ObjectAnimator.ofFloat(crewMan,crewMan.X,crewMan.Y,cToEPath).setDuration(4000).start();
                        Clicked[0]=false;
                    }
                }

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
    public void oxygenCheck(int oxygenLevel){
        if (oxygenLevel<50){
            oxygenEmergency.setVisibility(View.VISIBLE);
        }
        if(oxygenLevel>=50){
            oxygenEmergency.setVisibility(View.GONE);
            largerOxygenEmergency.setVisibility(View.GONE);
        }
        if (oxygenLevel<25){
            oxygenEmergency.setVisibility(View.GONE);
            largerOxygenEmergency.setVisibility(View.VISIBLE);
        }
    }
    /*
    public void coordMaker(int coords){
        x=coords/1000;
        y=coords%1000;
    }*/
    }
