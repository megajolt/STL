package com.example.qhoffman7249.stl;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*public class AI{
    List<Weapon> weapons = new ArrayList<>();
    private int level;
    private int playerHealth;
    private int aiHealth;
    private int playerShield;
    private int aiShield;
    public AI(int startlevel){
        level = startlevel;
        for(int i = 0; i < 10; i++){
            weapons.add(new Weapon(20,20));
        }
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
    public Weapon getWeapon(){
        return weapons.get(0);
    }
}*/
public class AI{
    public class Level{
        Weapon weapon;
        int health;
        int shield;
        public Level(Weapon AIWeapon, int setHealth, int setShield){
            weapon = AIWeapon;
            health = setHealth;
            shield = setShield;
        }
    }
    public class Target{
        int roomNumber;
    }
    public class tuple{
        int number;
        String name;
        public tuple(int roomNumber, String roomName){
            number = roomNumber;
            name = roomName;
        }
    }
    public class targetingAlgorithim{
        //ArrayList<Target> targetArray = new ArrayList<>();
        tuple targetingarray[][] = new tuple[5][5];
        public targetingAlgorithim(int level){
            for(int i = 0; i < 5; i++) {
                switch (i) {
                    case 0:
                        targetingarray[i] = new tuple[]{new tuple(4, "gun room"), new tuple(3, "control room"), new tuple(5, "shield"), new tuple(2, "med room"), new tuple(1, "engine")};
                        break;
                    case 1:
                        targetingarray[i] = new tuple[]{new tuple(3, "control room"), new tuple(4, "gun room"), new tuple(5, "shield"), new tuple(1, "engine"), new tuple(2, "med room")};
                        break;
                    case 2:
                        targetingarray[i] = new tuple[]{new tuple(2, "med room"), new tuple(5, "shield"), new tuple(3, "control room"), new tuple(4, "gun room"), new tuple(1, "engine")};
                        break;
                    case 3:
                        targetingarray[i] = new tuple[]{new tuple(1, "engine"), new tuple(5, "shield"), new tuple(3, "control room"), new tuple(4, "gun room"), new tuple(2, "med room")};
                        break;
                    case 4:
                        targetingarray[i] = new tuple[]{new tuple(1, "engine"), new tuple(2, "med room"), new tuple(5, "shield"), new tuple(3, "control room"), new tuple(4, "gun room")};
                        break;
                }
            }
        }
        public targetingAlgorithim(){

        }
    }

    int level = 1;
    List<Level> levels = new ArrayList<>();
    public AI(){
        targetingAlgorithim algorithim1;
        for(int i = 6; i > 5; i--){
            levels.add(new Level(new Weapon((-i+6)*5,i*4), i*20, i*20));
        }
        algorithim1 = new targetingAlgorithim(level);
    }
    public Weapon getWeapon(){
        return levels.get(level).weapon;
    }
    public Room getTarget(Ship myShip){
        switch(level){
            case 1:
                break;
        }
        return getTarget(myShip);
    }
}
