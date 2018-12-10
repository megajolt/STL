package com.example.qhoffman7249.stl;

import android.animation.ObjectAnimator;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.AsyncTask;
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
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class GameActivity extends AppCompatActivity {
    public int damage=0;
    //Infirmary: X:694 y: 560
    // Shield: X: 408 y:655
    //Gun: X: 675 y:722
    //Control: X868 y: 638
    //Engine: X:415 y: 731
    public ProgressBar shieldBar;
    public Handler shieldHandler= new Handler();
    public int hdamage = 0;
    public Shields shields= new Shields();
    public int currentShield=100;
    public ProgressBar healthBar;
    //private Runnable myhandler;
    public int health= 100;
    public int enemycurrentShield = 100;
    public int enemyhealth = 100;
    public int y=0;
    public float ypos=0;
    public double yfin=0;
    public boolean isclickedcrew;
    public double xfin = 0;
    public float xpos = 0;
    public int x=0;
    public List<Integer> iOccupied;
    public List<Integer> sOccupied;
    public List<Integer> gOccupied;
    public List<Integer> cOccupied;
    public List<Integer> eOccupied;
    public int manHealth=99;
    public MediaPlayer player;
    public boolean menvis = false;
    public boolean weaponVisibility=false;
    public boolean roomVisibility=false;
    public ProgressBar enemyShieldBar;
    public ProgressBar enemyHealthBar;
    public int oxygenLevel=100;
    public ImageView oxygenEmergency;
    public ImageView largerOxygenEmergency;
    public ScrollView weaponMenu;
    public  LinearLayout weaponButtons;
    public LinearLayout roomButtons;
    public ImageView enemy;
    public Button crew1;
    public boolean damaged=false;
    public int enemydamage;
    public int coords=0;
    public boolean crewVisibility=false;
    //random comment
    public Gun weapons=new Gun();
    public Coordinates room=new Coordinates();
    public List<String> characternames;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Intent m = new Intent(GameActivity.this, music.class);
        BroadcastReceiver bcr = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                checkdamage();
            }
        };
        //startService(m);
        weaponMenu = findViewById(R.id.weaponMenu);
        oxygenEmergency = findViewById(R.id.oxygenEmergency);
        largerOxygenEmergency = findViewById(R.id.largerOxygenEmergency);
        enemyShieldBar = findViewById(R.id.enemyShieldBar);
        enemyHealthBar = findViewById(R.id.enemyHealthBar);
        enemyHealthBar.setProgress(100);
        enemyShieldBar.setProgress(100);
        enemyShieldBar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.shieldbar), android.graphics.PorterDuff.Mode.SRC_IN);
        enemyHealthBar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.healthbar), android.graphics.PorterDuff.Mode.SRC_IN);
        enemyHealthBar.setScaleY(2f);
        enemyShieldBar.setScaleY(2f);
        enemy = findViewById(R.id.enemyShip);
        Button openMenu = findViewById(R.id.menu);
        Button openWeaponMenu= findViewById(R.id.firstSubM);
        final Button openCrewMenu = findViewById(R.id.secondSubM);
        Button openRoomMenu= findViewById(R.id.thirdSubM);
        final LinearLayout weaponButtons= findViewById(R.id.weaponButtons);
        final LinearLayout roomButtons= findViewById(R.id.roomButtons);
        final LinearLayout crewButtons=findViewById(R.id.crewButtons);
        openMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent i = new Intent(GameActivity.this, PopTivity.class);
               startActivity(i);
            }
        });

       openWeaponMenu.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(!weaponVisibility) {
                   weaponMenu.setVisibility(View.VISIBLE);
                   weaponButtons.setVisibility(LinearLayout.VISIBLE);
                   weaponVisibility = true;
                   roomButtons.setVisibility(LinearLayout.GONE);
                   roomVisibility = false;
                   crewButtons.setVisibility(LinearLayout.GONE);
                   crewVisibility = false;
               }
               else if(weaponVisibility){
                   weaponMenu.setVisibility(View.GONE);
                   weaponButtons.setVisibility(LinearLayout.GONE);
                   weaponVisibility = false;
               }

           }
       });
       openCrewMenu.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(!crewVisibility){
                   weaponMenu.setVisibility(View.VISIBLE);
                   crewButtons.setVisibility(View.VISIBLE);
                   crewVisibility= true;
                   weaponButtons.setVisibility(LinearLayout.GONE);
                   weaponVisibility = false;
                   roomButtons.setVisibility(LinearLayout.GONE);
                   roomVisibility = false;
               }
               else if(crewVisibility){
                   crewButtons.setVisibility(LinearLayout.GONE);
                   weaponMenu.setVisibility(View.GONE);
                   crewVisibility = false;
               }
           }
       });
        openRoomMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!roomVisibility){
                    weaponMenu.setVisibility(View.VISIBLE);
                    roomButtons.setVisibility(LinearLayout.VISIBLE);
                    roomVisibility= true;
                    weaponButtons.setVisibility(LinearLayout.GONE);
                    weaponVisibility = false;
                    crewButtons.setVisibility(LinearLayout.GONE);
                    crewVisibility = false;
                }
                else if(roomVisibility){
                    weaponMenu.setVisibility(View.GONE);
                    roomButtons.setVisibility(LinearLayout.GONE);
                    roomVisibility = false;
                }
            }
        });

        View myview = findViewById(R.id.view);
        /*
        myview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                x = (int) event.getX();
                y = (int) event.getY();
                Toast.makeText(GameActivity.this, "coordinates: x: " + x + " y:" + y, Toast.LENGTH_SHORT).show();
                System.out.println(x + ", " + y);
                if (isclickedcrew) {
                    Toast.makeText(GameActivity.this, "coordinates: x: " + x + " y:" + y, Toast.LENGTH_SHORT).show();
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
        });*/
        Button bastardSword = findViewById(R.id.bastardSword);
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

        //Status bar code
        healthBar = findViewById(R.id.healthBar);
        healthBar.setScaleY(2f);
        healthBar.setProgress(100);
        //healthBar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.healthbar), android.graphics.PorterDuff.Mode.SRC_IN);
        //shieldBar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.shieldbar), android.graphics.PorterDuff.Mode.SRC_IN);
        shieldBar = findViewById(R.id.shieldBar);
        shieldBar.setScaleY(2f);
        shieldBar.setProgress(100);

        /*Button animate = findViewById(R.id.crew1);
        animate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btn = findViewById(R.id.crew1);
                xpos = btn.getX();
                ypos = btn.getY();
                if (xpos == 694 && ypos == 560) {
                    iOccupied.add(R.id.crew1);
                }
                if (xpos == 408 && ypos == 655) {
                    sOccupied.add(R.id.crew1);
                }
                if (xpos == 675 && ypos == 722) {
                    gOccupied.add(R.id.crew1);
                }
                if (xpos == 868 && ypos == 638) {
                    cOccupied.add(R.id.crew1);
                }
                if (xpos == 415 && ypos == 731) {
                    eOccupied.add(R.id.crew1);
                }
                System.out.println("xpos: " + xpos + " ypos: " + ypos);
                isclickedcrew = true;
            }
        });*/
        //Room utility code

        if (iOccupied != null && iOccupied.isEmpty()) {
            if (xpos >= 646 && xpos <= 781 && ypos >= 504 && ypos <= 708) {
                manHealth++;

            }
        }
        if (sOccupied != null && sOccupied.isEmpty()) {

        }
        if (gOccupied != null && gOccupied.isEmpty()) {

        }
        if (cOccupied != null && cOccupied.isEmpty()) {

        }
        if (eOccupied != null && eOccupied.isEmpty()) {

        }
    }

    public void checkdamage(){
        //first check damage relative to enemy action
        if(enemydamage>currentShield){
            enemydamage = enemydamage - currentShield;
            currentShield = 0;
            health = health - enemydamage;
        }
        else if(currentShield>=enemydamage){
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
        if(health<100){
            damaged=true;
        }
        damage = 0;
        healthBar.setProgress(health);
        shieldBar.setProgress(currentShield);
    }
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
    public void coordMaker(int coords){
        x=coords/1000;
        y=coords%1000;
    }
    @Override
    protected void onStop() {
        Intent m = new Intent(GameActivity.this, music.class);
        stopService(m);
        super.onStop();
    }
}