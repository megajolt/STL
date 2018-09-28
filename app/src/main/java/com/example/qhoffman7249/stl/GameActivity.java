package com.example.qhoffman7249.stl;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class GameActivity extends AppCompatActivity {
    public int damage=0;


    public ProgressBar shieldBar;
    public Handler shieldHandler= new Handler();
    public Shields shields= new Shields();
    public int currentShield=100;
//commit comment
    public ProgressBar healthBar;
    public Handler healthHandler= new Handler();
    public ShipHealth health= new ShipHealth();
    public int currentHealth=100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Button damageTest = findViewById(R.id.button4);

        damageTest.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                damage++;
            }
        });

        shieldBar=findViewById(R.id.shieldBar);
        shieldBar.setScaleY(2f);
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
                            currentHealth= health.HealthCalc(damage,currentShield,currentHealth);
                            healthBar.setProgress(currentHealth);
                        }
                    });
                }

            }
        }).start();

    }

}
