package com.example.qhoffman7249.stl;

import android.animation.ObjectAnimator;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    public Shields shields= new Shields();
    public int currentShield=100;
    public static float xpos = 0;
    public float xfin = 0;
    public boolean isclickedcrew = false;
    public ProgressBar healthBar;
    public Handler healthHandler= new Handler();
    public ShipHealth health= new ShipHealth();
    public int currentHealth=100;
    public int y;
    public int x;

    public Gun weapons=new Gun();
    public List<String> characternames;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        /*final TextView shieldHealth=findViewById(R.id.shieldHealth);
        final TextView mainHealth=findViewById(R.id.mainHealth);*/

        Button halberd=findViewById(R.id.halberd);
        Button glaive=findViewById(R.id.glaive);
        Button bastardSword=findViewById(R.id.bastardSword);
        Button pruningShears=findViewById(R.id.pruningShears);
        Button maul=findViewById(R.id.maul);

        halberd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                damage=weapons.halberd;

            }
        });
        bastardSword.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                damage=weapons.bastardSword;

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
        pruningShears.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                damage=weapons.pruningShears;

            }
        });

        //animation code
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

        //Status bar code
        shieldBar=findViewById(R.id.shieldBar);
        shieldBar.setScaleY(2f);
        shieldBar.setProgress(100);
        new Thread(new Runnable() {
            @Override
            public void run() {

                while(currentShield!=0){
                    shieldHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            if(damage>currentShield){
                                currentShield=0;
                            }
                            else {
                                currentShield = shields.OnDamage(damage, currentShield);
                                damage=0;
                            }
                            shieldBar.setProgress(currentShield);
                            //shieldHealth.setText(currentShield);
                        }
                    });
                }
            }
        }).start();
        healthBar= findViewById(R.id.healthBar);
        healthBar.setScaleY(2f);
        new Thread(new Runnable() {
            @Override
            public void run() {

                while(currentHealth!=0){
                    healthHandler.post(new Runnable(){
                        @Override
                      public void run(){
                            if(damage>currentHealth){
                                currentHealth=0;
                            }
                            else{
                                currentHealth=health.HealthCalc(damage,currentShield,currentHealth);
                            }
                            healthBar.setProgress(currentHealth);
                            //mainHealth.setText(currentHealth);
                        }
                    });
                }

            }
        }).start();

    }

}
