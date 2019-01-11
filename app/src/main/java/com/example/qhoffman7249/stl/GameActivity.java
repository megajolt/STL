package com.example.qhoffman7249.stl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Path;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.List;

public class GameActivity extends AppCompatActivity {
    public int damage=0;

    public ProgressBar shieldBar;
    public int hdamage = 0;
    public int currentShield=100;
    public ProgressBar healthBar;
    //private Runnable myhandler;
    public int health= 100;
    public int enemycurrentShield = 100;
    public int enemyhealth = 100;
    public int y=0;
    public double ypos=0;
    public double yfin=0;
    public boolean isclickedcrew;
    public double xfin = 0;
    public double xpos = 0;
    public int x=0;
    private boolean[] roomEnabled = new boolean[6];
    private int[] roomHealth = new int[6];
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
    public  LinearLayout weaponButtons;
    public LinearLayout roomButtons;
    public ImageView enemy;
    public Button crew1;
    public boolean damaged=false;
    public int enemydamage = 0;
    public int coords=0;
    public boolean crewVisibility=false;
    boolean gClicked=false;
    boolean iClicked=false;
    boolean sClicked=false;
    boolean cClicked=false;
    boolean eClicked=false;
    private AI sysAI;
    private int target = 0;
    //random comment
    public Gun weapons=new Gun();
    public Coordinates room=new Coordinates();
    public List<String> characternames;
    private boolean menubool = false;

    @Override
    protected void onPause() {
        super.onPause();
        if(!menubool) {
            /*Intent p = new Intent(GameActivity.this, GameActivity.class);
            p.putExtra("todo", "back");
            startActivity(p);*/
            Intent r = new Intent(GameActivity.this, pause.class);
            startActivity(r);
        }
    }
    private int mInterval = 5000; // 5 seconds by default, can be changed later
    private Handler mHandler;
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
                checkdamage();
                checkTarget();
                //Toast.makeText(GameActivity.this, "testing func ran", Toast.LENGTH_SHORT).show();
                 //this function can change value of mInterval.
            } finally {
                // 100% guarantee that this always happens, even if
                // your update method throws an exception
                mHandler.postDelayed(mStatusChecker, mInterval);
            }
        }
    };
    public void checkTarget(){
        roomHealth[target] = enemydamage;
        if(roomHealth[target] < 1){
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
    }

//random comment to put to server
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        sysAI = new AI(1);
        healthBar = findViewById(R.id.healthBar);
        healthBar.setScaleY(2f);
        healthBar.setProgress(100);
        //healthBar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.healthbar), android.graphics.PorterDuff.Mode.SRC_IN);
        //shieldBar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.shieldbar), android.graphics.PorterDuff.Mode.SRC_IN);
        shieldBar = findViewById(R.id.shieldBar);
        shieldBar.setScaleY(2f);
        shieldBar.setProgress(100);
        mHandler = new Handler();
        startRepeatingTask();
        menubool = false;
        Intent intent = getIntent();
        String todo = intent.getExtras().getString("todo");
        if(todo == "back"){
            Toast.makeText(this, "to == back", Toast.LENGTH_SHORT).show();
            Intent s = new Intent(GameActivity.this, pause.class);
            startActivity(s);
        }
        Toast.makeText(this, "todo: " + todo, Toast.LENGTH_SHORT).show();
        Intent m = new Intent(GameActivity.this, music.class);
        //startService(m);
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
        Button bastardSword = findViewById(R.id.bastardSword);
        Button pruningShears = findViewById(R.id.pruningShears);
        Button maul = findViewById(R.id.maul);
        Button halberd = findViewById(R.id.halberd);
        Button glaive = findViewById(R.id.glaive);
        Button medRoomButton = findViewById(R.id.medBay);
        Button engineRoomButton = findViewById(R.id.engine);
        Button shieldRoomButton = findViewById(R.id.shield);
        final Button gunRoomButton = findViewById(R.id.gun);
        Button controlRoomButton = findViewById(R.id.control);
        final Button crewMan= findViewById(R.id.crewDude);

        //damage buttons
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
                damage = weapons.peaShooter;
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

        //animation buttons

        medRoomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                coords = room.medCoords;
                iClicked=true;
                coordMaker(coords);

            }
        });
        engineRoomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                coords = room.engineCoords;
                eClicked=true;
                coordMaker(coords);

            }
        });
        shieldRoomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                coords = room.shieldCoords;
                sClicked=true;
                coordMaker(coords);

            }
        });
        gunRoomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                coords = room.gunCoords;
                gClicked=true;
                coordMaker(coords);

            }
        });
        controlRoomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                coords = room.controlCoords;
                cClicked=true;
                coordMaker(coords);

            }
        });
        //Infirmary: X:694 y: 560
        // Shield: X: 408 y:655
        //Gun: X: 675 y:722
        //Control: X868 y: 638
        //Engine: X:415 y: 731
        crewMan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xpos=crewMan.getX();
                ypos=crewMan.getY();
                //if the clicked crew member is in the control room
                if(xpos==872.40234375&&ypos==624.654052734375){
                    if(iClicked==true){
                        //runs animations from control room to infirmary
                        iClicked=false;
                    }
                    else if(gClicked==true){
                        //control room to gun room
                        gClicked=false;
                    }
                   else if(sClicked==true){
                        //control room to shield room
                        sClicked=false;
                    }
                    else if(eClicked==true){
                        //control room engine room
                        eClicked=false;
                    }
                    else{}
                }
                //Infirmary
                if(xpos==534.2578125&&ypos==785.79052734375){
                    if(cClicked==true){
                        //infirmary to control
                        cClicked=false;
                    }
                   else if(gClicked==true){
                        //infirmary to gun room
                        gClicked=false;
                    }
                    else if(sClicked==true){
                        //infirmary to shield room
                        sClicked=false;
                    }
                    else if(eClicked==true){
                        //infirmary to engine room
                        eClicked=false;
                    }
                    else {}
                }
                //shield
                if (xpos==557.2265625&&ypos==544.563720703125){
                    if(gClicked==true){
                        //control room to gun room
                        gClicked=false;
                    }

                    else if(eClicked==true){
                        //control room engine room
                        eClicked=false;
                    }
                    else if(iClicked==true){
                        //runs animations from control room to infirmary
                        iClicked=false;
                    }
                    else if(cClicked==true){
                        //infirmary to control
                        cClicked=false;
                    }
                    else{}
                }
                //gun
                if(xpos==653.320315&&ypos==764.7626953125){
                    if(cClicked==true){
                        //infirmary to control
                        cClicked=false;
                    }
                    else if(eClicked==true){
                        //control room engine room
                        eClicked=false;
                    }
                    else if(iClicked==true){
                        //runs animations from control room to infirmary
                        iClicked=false;
                    }
                    else if(sClicked==true){
                        //control room to shield room
                        sClicked=false;
                    }
                    else{}
                }
                //engine
                if (xpos==617.28515625&&ypos==606.62548828125){
                    if(iClicked==true){
                        //runs animations from control room to infirmary
                        iClicked=false;
                    }
                   else if(gClicked==true){
                        //control room to gun room
                        gClicked=false;
                    }
                    else if(sClicked==true){
                        //control room to shield room
                        sClicked=false;
                    }
                    else if(cClicked==true){
                        //infirmary to control
                        cClicked=false;
                    }
                    else{}
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
            Intent r = new Intent(GameActivity.this, StlMenu.class);
            startActivity(r);
        }
        if(health<100){
            damaged=true;
        }
        damage = 0;
        healthBar.setProgress(health);
        shieldBar.setProgress(currentShield);
        Toast.makeText(this, "checkdamage ran", Toast.LENGTH_SHORT).show();
    }
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
    //OLD ANIMATION CODE
    /*
        float cxp = btn.getX();
        float cyp = btn.getY();
        y=y-204;
        ObjectAnimator animationx = ObjectAnimator.ofFloat(btn, "translationX", cxp, x);
        animationx.setDuration(2000);
        animationx.start();
        ObjectAnimator animatory = ObjectAnimator.ofFloat(btn, "translationY", cyp, y);
        animatory.setDuration(2000);
        animatory.start();
        isclickedcrew = true;*/


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