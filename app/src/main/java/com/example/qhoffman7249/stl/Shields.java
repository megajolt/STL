package com.example.qhoffman7249.stl;

public class Shields {
    int counter=0;
    int shieldHealth=100;
     //placeholder
    //when damaged decrease shield health, if there is excess damage after shield is destroyed damage ship health
    public int OnDamage(int damage){
        if(shieldHealth>=0) {
            if (damage <= shieldHealth) {
                while(counter!=damage) {
                    shieldHealth = shieldHealth - 1;
                }
            }
            else if(damage>shieldHealth){
                damage=damage-shieldHealth;

                // call health subtraction method with damage
            }
        }
       return shieldHealth;
    }

}
