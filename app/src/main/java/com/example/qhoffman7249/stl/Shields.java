package com.example.qhoffman7249.stl;

public class Shields {

    int shieldHealth=100;
     //placeholder
    //when damaged decrease shield health, if there is excess damage after shield is destroyed damage ship health
    public int OnDamage(int damage){
        if(shieldHealth>=0) {
            if (damage <= shieldHealth) {
                shieldHealth = shieldHealth - damage;
            }
            else if(damage>shieldHealth){
                damage=damage-shieldHealth;

                // call health subtraction method with damage
            }
        }
       return shieldHealth;
    }

}
