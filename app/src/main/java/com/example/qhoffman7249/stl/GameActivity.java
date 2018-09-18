package com.example.qhoffman7249.stl;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

public class GameActivity extends AppCompatActivity {
    public ProgressBar healthBar;
    public int currentHealth=100;
    public Handler healthHandler= new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        healthBar= findViewById(R.id.healthBar);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(currentHealth>0){
                    currentHealth=currentHealth-1;
                    android.os.SystemClock.sleep(50);
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
