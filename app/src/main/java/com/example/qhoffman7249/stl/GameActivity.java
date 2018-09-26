package com.example.qhoffman7249.stl;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import static java.lang.Thread.sleep;

public class GameActivity extends AppCompatActivity {
    public int damage=5;

    public ProgressBar healthBar;
    public int currentHealth=100;
    public Handler healthHandler= new Handler();

    public ProgressBar shieldBar;
    public Handler shieldHandler= new Handler();
    public Shields shields= new Shields();
    public int currentShield=shields.OnDamage(damage);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        shieldBar=findViewById(R.id.shieldBar);
        shieldBar.setScaleY(2f);
        new Thread(new Runnable() {
            @Override
            public void run() {


                while(currentShield>0){
                    shieldHandler.post(new Runnable() {
                        @Override
                        public void run() {
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
                while(currentHealth>0){
                    healthHandler.post(new Runnable(){
                        @Override
                      public void run(){
                            healthBar.setProgress(currentHealth);
                        }
                    });
                }

            }
        }).start();

    }

}
