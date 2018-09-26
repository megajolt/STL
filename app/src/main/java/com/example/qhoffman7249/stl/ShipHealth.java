package com.example.qhoffman7249.stl;

public class ShipHealth {
    public int shipHealth=100;
    public int HealthCalc (int damage, int currentShield){
        if(damage>currentShield){
            damage=damage-currentShield;
            shipHealth=shipHealth-damage;
            return shipHealth;
        }
        else{
            return shipHealth;
        }

    }
}
