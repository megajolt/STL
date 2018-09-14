package com.example.qhoffman7249.stl;

public class Shields {
    int shieldHealth=100;
    int damage=5;
    //
    public void OnDamage(/*damage*/){
        if(shieldHealth>0) {
            if (damage <= shieldHealth) {
                shieldHealth = shieldHealth - damage;
            }
            else if(damage>shieldHealth){
                shieldHealth=0;
                damage=damage-shieldHealth;
                // call health subtraction method with damage
            }
        }
        else{
            // call health subtraction method with damage
        }
    }
}
