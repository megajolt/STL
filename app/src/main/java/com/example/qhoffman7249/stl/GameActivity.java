package com.example.qhoffman7249.stl;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class GameActivity extends AppCompatActivity {
    public int damage=0;

    public ProgressBar shieldBar;
    public Handler shieldHandler= new Handler();
    public int hdamage = 0;
    public Shields shields= new Shields();
    public int currentShield=100;
    public ProgressBar healthBar;
    public int health= 100;
    List<Integer> yList= new List<Integer>() {
        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(Object o) {
            return false;
        }

        @NonNull
        @Override
        public Iterator<Integer> iterator() {
            return null;
        }

        @NonNull
        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @NonNull
        @Override
        public <T> T[] toArray(@NonNull T[] a) {
            return null;
        }

        @Override
        public boolean add(Integer integer) {
            return false;
        }

        @Override
        public boolean remove(Object o) {
            return false;
        }

        @Override
        public boolean containsAll(@NonNull Collection<?> c) {
            return false;
        }

        @Override
        public boolean addAll(@NonNull Collection<? extends Integer> c) {
            return false;
        }

        @Override
        public boolean addAll(int index, @NonNull Collection<? extends Integer> c) {
            return false;
        }

        @Override
        public boolean removeAll(@NonNull Collection<?> c) {
            return false;
        }

        @Override
        public boolean retainAll(@NonNull Collection<?> c) {
            return false;
        }

        @Override
        public void clear() {

        }

        @Override
        public Integer get(int index) {
            return null;
        }

        @Override
        public Integer set(int index, Integer element) {
            return null;
        }

        @Override
        public void add(int index, Integer element) {

        }

        @Override
        public Integer remove(int index) {
            return null;
        }

        @Override
        public int indexOf(Object o) {
            return 0;
        }

        @Override
        public int lastIndexOf(Object o) {
            return 0;
        }

        @NonNull
        @Override
        public ListIterator<Integer> listIterator() {
            return null;
        }

        @NonNull
        @Override
        public ListIterator<Integer> listIterator(int index) {
            return null;
        }

        @NonNull
        @Override
        public List<Integer> subList(int fromIndex, int toIndex) {
            return null;
        }
    };
    public int y;
    public boolean isclickedcrew;
    public int xfin = 0;
    public int xpos = 0;
    List<Integer> xList= new List<Integer>() {
        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(Object o) {
            return false;
        }

        @NonNull
        @Override
        public Iterator<Integer> iterator() {
            return null;
        }

        @NonNull
        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @NonNull
        @Override
        public <T> T[] toArray(@NonNull T[] a) {
            return null;
        }

        @Override
        public boolean add(Integer integer) {
            return false;
        }

        @Override
        public boolean remove(Object o) {
            return false;
        }

        @Override
        public boolean containsAll(@NonNull Collection<?> c) {
            return false;
        }

        @Override
        public boolean addAll(@NonNull Collection<? extends Integer> c) {
            return false;
        }

        @Override
        public boolean addAll(int index, @NonNull Collection<? extends Integer> c) {
            return false;
        }

        @Override
        public boolean removeAll(@NonNull Collection<?> c) {
            return false;
        }

        @Override
        public boolean retainAll(@NonNull Collection<?> c) {
            return false;
        }

        @Override
        public void clear() {

        }

        @Override
        public Integer get(int index) {
            return null;
        }

        @Override
        public Integer set(int index, Integer element) {
            return null;
        }

        @Override
        public void add(int index, Integer element) {

        }

        @Override
        public Integer remove(int index) {
            return null;
        }

        @Override
        public int indexOf(Object o) {
            return 0;
        }

        @Override
        public int lastIndexOf(Object o) {
            return 0;
        }

        @NonNull
        @Override
        public ListIterator<Integer> listIterator() {
            return null;
        }

        @NonNull
        @Override
        public ListIterator<Integer> listIterator(int index) {
            return null;
        }

        @NonNull
        @Override
        public List<Integer> subList(int fromIndex, int toIndex) {
            return null;
        }
    };
    public int x;
    public boolean menvis = false;
    //random comment
    public Gun weapons=new Gun();
    public List<String> characternames;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ProgressBar enemyShieldBar = findViewById(R.id.enemyShieldBar);
        ProgressBar enemyHealthBar = findViewById(R.id.enemyHealthBar);
        enemyHealthBar.setProgress(100);
        enemyShieldBar.setProgress(100);
        enemyHealthBar.setScaleY(2f);
        enemyShieldBar.setScaleY(2f);
        Button openMenu = findViewById(R.id.menu);
        openMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout Menu = findViewById(R.id.Menu);
                if(!menvis) {
                    Menu.setVisibility(View.VISIBLE);
                    menvis = true;
                }
                else if(menvis){
                    Menu.setVisibility(View.GONE);
                    menvis = false;
                }
            }
        });
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
        Button bastardSword=findViewById(R.id.bastardSword);
        Button pruningShears=findViewById(R.id.pruningShears);
        Button maul=findViewById(R.id.maul);
        Button halberd=findViewById(R.id.halberd);
        Button glaive=findViewById(R.id.glaive);
        halberd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                damage=weapons.halberd;

            }
        });
        pruningShears.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                damage=weapons.pruningShears;

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
                damage=2;
                checkdamage();
            }
            //fuck
        });
        bastardSword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                damage=weapons.bastardSword;
                checkdamage();
            }
        });
        maul.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                damage=weapons.maul;
                checkdamage();
            }
        });
        glaive.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                damage=weapons.glaive;
                checkdamage();
            }
        });
        pruningShears.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                damage=weapons.pruningShears;
                checkdamage();
            }
        });
        halberd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                damage=weapons.halberd;
                checkdamage();
            }
        });

        //Status bar code
        healthBar= findViewById(R.id.healthBar);
        healthBar.setScaleY(2f);
        healthBar.setProgress(100);
        shieldBar=findViewById(R.id.shieldBar);
        shieldBar.setScaleY(2f);
        shieldBar.setProgress(100);
        /*Button animate = findViewById(R.id.anim);
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
        });*/
        /*
        Button dudebro = findViewById(R.id.crew1);
        dudebro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isclickedcrew = true;
            }
        });*/
    }
    public void checkdamage(){
        if(damage>currentShield){
            damage = damage - currentShield;
            currentShield = 0;
            health = health - damage;
        }
        else if(currentShield>=damage){
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
        damage = 0;
        healthBar.setProgress(health);
        shieldBar.setProgress(currentShield);
    }
}
