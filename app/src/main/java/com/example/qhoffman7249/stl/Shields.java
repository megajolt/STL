package com.example.qhoffman7249.stl;

public class Shields {


    public int OnDamage(int damage, int currentShield){
        if(currentShield>=0) {
            if (damage <= currentShield) {
                currentShield = currentShield - damage;
                return currentShield;
            }
            else if(damage>currentShield){
                damage=damage-currentShield;

            }
        }
        return damage;
    }

}
