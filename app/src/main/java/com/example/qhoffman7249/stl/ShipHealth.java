package com.example.qhoffman7249.stl;

public class ShipHealth {

    public int HealthCalc (int damage, int currentShield, int currentHealth){
        if(damage>currentShield){
            damage=damage-currentShield;
            currentHealth=currentHealth-damage;
            return currentHealth;
        }
        else{
            return currentHealth;
        }

    }
}
