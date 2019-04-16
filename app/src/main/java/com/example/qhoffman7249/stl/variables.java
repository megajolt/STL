package com.example.qhoffman7249.stl;

import android.content.res.Resources;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;


import java.util.List;

public class variables extends AppCompatActivity {
    public static int damage=0;
    public static ProgressBar shieldBar;
    public static int currentShield=100;
    public static ProgressBar healthBar;
    public static int health= 100;
    public static int enemycurrentShield = 100;
    public static int enemyhealth = 100;
    public static boolean[] roomEnabled = new boolean[6];
    public static int[] roomHealth = new int[6];
    public static int[][] Occupied = new int[5][5];
    public static int manHealth=99;
    public static MediaPlayer player;
    public static ProgressBar enemyShieldBar;
    public static ProgressBar enemyHealthBar;
    public static int oxygenLevel=100;
    public static int coolDownTime=0;
    public static ImageView oxygenEmergency;
    public static ImageView largerOxygenEmergency;
    public static LinearLayout weaponButtons;
    public static LinearLayout roomButtons;
    public static ImageView enemy;
    public static boolean damaged=false;
    public static int enemydamage = 0;
    public static int coords=0;
    public static boolean[] Clicked = new boolean[6];
    public static AI sysAI;
    public static int target = 0;
    public static Gun weapons=new Gun();
    public static Coordinates room=new Coordinates();
    public static List<String> characternames;
    public static boolean menubool = false;
    public static boolean coolDown=false;
    public ImageView bastardSword;
    public ImageView maul;
    public ImageView halberd;
    public Button medRoomButton;
    public Button engineRoomButton;
    public Button gunRoomButton;
    public Button controlRoomButton;
    public ImageView mShip;
    public Button shieldRoomButton;
    public Button openMenu;
    public Button openWeaponMenu;
    public Button openCrewMenu;
    public Button openRoomMenu;
    public void variableSet(){
        Occupied[0] = new int[5];
        Occupied[1] = new int[5];
        Occupied[2] = new int[5];
        Occupied[3] = new int[5];
        Occupied[4] = new int[5];
        sysAI = new AI(1);
        healthBar = findViewById(R.id.healthBar);
        healthBar.setScaleY(2f);
        healthBar.setProgress(100);
        shieldBar = findViewById(R.id.shieldBar);
        shieldBar.setScaleY(2f);
        shieldBar.setProgress(100);
        menubool = false;
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
        mShip= findViewById(R.id.ship);
        bastardSword = findViewById(R.id.bastardSword);
        maul = findViewById(R.id.maul);
        halberd = findViewById(R.id.halberd);
        medRoomButton = findViewById(R.id.medBay);
        engineRoomButton = findViewById(R.id.engine);
        shieldRoomButton = findViewById(R.id.shield);
        gunRoomButton = findViewById(R.id.gun);
        controlRoomButton = findViewById(R.id.control);
        //openMenu = findViewById(R.id.menu);
    }

}