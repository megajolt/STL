package com.example.qhoffman7249.stl;

public class Room {
    private int number;
    private double innerHeight;
    private double innerWidth;
    private int myHealth = 100/5;
    private double xpos;
    private double ypos;
    private String name;
    public Room(int roomNumber, double width, double height){
        number = roomNumber;
    }
    public Room(int roomNumber){
        number = roomNumber;
    }
    public Room(int roomNumber, String roomName){number = roomNumber; name = roomName;}
    void setWidth(double width){
        innerWidth = width;
    }
    void setName(String targetName){
        name = targetName;
    }
    String getName(){
        return name;
    }
    void setHeight(double height){
        innerHeight = height;
    }
    double getWidth(){
        return innerWidth;
    }
    double getHeight(){
        return innerHeight;
    }
    void setHealth(int health){
        myHealth = health;
    }
    int getHealth(){
        return myHealth;
    }
    void setXpos(double Xpos){
        xpos = Xpos;
    }
    void setYpos(double Ypos){
        ypos = Ypos;
    }
    double getY(){
        return ypos;
    }
    double getX(){
        return xpos;
    }
    int getIndex(){
        return number;
    }
}
