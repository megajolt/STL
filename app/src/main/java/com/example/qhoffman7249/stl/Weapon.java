package com.example.qhoffman7249.stl;

public class Weapon {
    int damage = 2;
    int coolDown = 1;
    public Weapon(int setDamage, int setCoolDown){
        coolDown = setCoolDown;
        damage = setDamage;
    }
    public Weapon(){}
    public void setDamage(int setDamage){
        damage = setDamage;
    }
    public void setCoolDown(int setCoolDown){
        coolDown = setCoolDown;
    }
    public int getCoolDown(){
        return coolDown;
    }
    public int getDamage(){
        return damage;
    }
}
