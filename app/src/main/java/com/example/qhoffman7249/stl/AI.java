package com.example.qhoffman7249.stl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AI{
    List<Weapons> weapons = new ArrayList<>();
    private int level;
    private int playerHealth;
    private int aiHealth;
    private int playerShield;
    private int aiShield;
    public AI(int startlevel){
        level = startlevel;
        weapons.get(0).coolDown = 0;
        weapons.get(0).damage = 2;
    }
    public void update(int shield, int health, int enemyHealth, int enemyShield){
        playerHealth = enemyHealth;
        aiHealth = health;
        aiShield = shield;
        playerShield = enemyShield;
        if(aiHealth < 1){
            levelUp();
        }
    }
    public void levelUp(){
        level++;
    }
    public int getDamage(){
        switch(level){
            case 1:
                return level1(0);
            case 2:
                return level2(0);
        }
        return 0;
    }
    Room getTarget(Ship myShip){
        return myShip.getRoom(0);
    }
    public int level1(int action){
        if(action == 0){
            return 5;
        }
        else if(action == 1){
            return 0;
        }
        return 0;
    }
    public int level2(int action){
        if(action == 0){
            return 5;
        }
        else if(action == 1 && playerShield > 0){
            return 0;
        }
        else if(action == 1){
            Random random = new Random();
            return random.nextInt() % 5;
        }
        return 0;
    }
    public int getLevel(){
        return level;
    }
    public Weapons getWeapon(){
        return weapons.get(0);
    }
}
