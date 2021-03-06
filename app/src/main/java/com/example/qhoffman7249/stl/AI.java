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
        tuple targetingarray[][] = new tuple[6][6];
        public targetingAlgorithim(int level){
                switch (level) {
                    case 0:
                        targetingarray[level] = new tuple[]{new tuple(1, "engine"), new tuple(2, "med room"), new tuple(3, "control room"), new tuple(4, ""), new tuple(5, ""), new tuple(6, "shield")};
                        break;
                    case 1:
                        targetingarray[level] = new tuple[]{new tuple(1, "engine"), new tuple(2, "med room"), new tuple(3, "control room"), new tuple(4, ""), new tuple(5, ""), new tuple(6, "shield")};
                        break;
                    case 2:
                        targetingarray[level] = new tuple[]{new tuple(1, "engine"), new tuple(2, "med room"), new tuple(3, "control room"), new tuple(4, ""), new tuple(5, ""), new tuple(6, "shield")};
                        break;
                    case 3:
                        targetingarray[level] = new tuple[]{new tuple(1, "engine"), new tuple(2, "med room"), new tuple(3, "control room"), new tuple(4, ""), new tuple(5, ""), new tuple(6, "shield")};
                        break;
                    case 4:
                        targetingarray[level] = new tuple[]{new tuple(1, "engine"), new tuple(2, "med room"), new tuple(3, "control room"), new tuple(4, ""), new tuple(5, ""), new tuple(6, "shield")};
                        break;
                }
        }
        public targetingAlgorithim(){

        }
        public int getTarget(Ship theShip){
            for(int i = 0; i < 5; i++){
                if(theShip.getRoom(i).getHealth() > 0){
                    return i;
                }
            }
            return 7;
        }
    }

    int level = 1;
    List<Level> levels = new ArrayList<>();
    targetingAlgorithim algorithim1;
    List<targetingAlgorithim> algors = new ArrayList<>();
    public AI(){
        targetingAlgorithim algorithim1 = new targetingAlgorithim(0);
        for(int i = 6; i > 0; i--){
            levels.add(new Level(new Weapon((-i+6)*5,i*1), i*20, i*20));
        }
        for(int a = 0; a < 6; a++){
            algors.add(new targetingAlgorithim(a));
        }
    }
    public Weapon getWeapon(){
        return levels.get(level).weapon;
    }
    public Room getTarget(Ship myShip){
        return myShip.getRoom(algors.get(level).getTarget(myShip));
    }
}
