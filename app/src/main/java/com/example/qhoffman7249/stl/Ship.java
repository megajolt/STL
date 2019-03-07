package com.example.qhoffman7249.stl;

import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class Ship{
    int shield = 100;
    int health = 100;
    double xpos;
    double ypos;
    ImageView view;
    boolean hasView = false;
    int level = 1;
    List<Room> rooms = new ArrayList<>();
    public Ship(int numberOfRooms) {
        for(int i = 0; i < numberOfRooms; i++){
            rooms.add(new Room(i));
        }
    }
    public Ship(int numberOfRooms, ImageView val) {
        for(int i = 0; i < numberOfRooms; i++){
            rooms.add(new Room(i));
        }
        view = val;
        hasView = true;
        ypos = view.getY();
        xpos = view.getX();
    }
    void setRoomWidth(int number, double width){
        rooms.get(number).setWidth(width);
    }
    void setRoomHeight(int number, double height){
        rooms.get(number).setHeight(height);
    }
    double getWidth(int number){
        return rooms.get(number).getWidth();
    }
    double getHeight(int number){
        return rooms.get(number).getHeight();
    }
    double getXpos(int number){
        return rooms.get(number).getX();
    }
    double getYpos(int number){
        return rooms.get(number).getY();
    }
    void setHealth(int healthhealth){
        health = healthhealth;
    }
    void setShield(int shieldhealth){
        shield = shieldhealth;
    }
    int getHealth(){
        return health;
    }
    int getShield(){
        return shield;
    }
    void incLevel(){
        level++;
    }
    int getLevel(){
        return level;
    }
    Room getRoom(int number){
        return rooms.get(number);
    }
    void setName(int number, String targetName){
        rooms.get(number).setName(targetName);
    }
    String getName(int number){
        return rooms.get(number).getName();
    }
    Room get_room_by_name(String name){
        for(int i = 0; i < rooms.size(); i++){
            if(rooms.get(i).getName() == name){
                return rooms.get(i);
            }
        }
        return null;
    }
    void setYPosition(float val){
        if(hasView){
            view.setY(val);
        }else {
            ypos = val;
        }
    }
    void setXPosition(float val){
        if(hasView){
            view.setX(val);
        }
        else {
            xpos = val;
        }
    }
    double getYPosition(){
        return ypos;
    }
    double getXPosition(){
        return xpos;
    }
}
