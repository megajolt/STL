package com.example.qhoffman7249.stl;

import android.graphics.Point;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Animation {
    public enum Cell{C,X,W,_}
    private Cell [][] board;
    private Cell[][] change;
    int times;
    boolean cS =true;
    variables Variables=new variables();

    public Animation() {

    }
    public void cAnimation() {
        ImageView ship=Variables.ship;
        final int actualHeight, actualWidth;
        final int imageViewHeight= ship.getHeight(), imageViewWidth=ship.getWidth();
        final int bitmapHeight=ship.getDrawable().getIntrinsicHeight() , bitmapWidth=ship.getDrawable().getIntrinsicWidth();
        if(imageViewHeight*bitmapWidth<=imageViewWidth*bitmapHeight){
            actualWidth = bitmapWidth * imageViewHeight / bitmapHeight;
            actualHeight = imageViewHeight;
        }else {
            actualHeight = bitmapHeight * imageViewWidth / bitmapWidth;
            actualWidth = imageViewWidth;
        }
        board = new Cell[actualHeight][actualWidth];
        System.out.println(actualHeight);
        System.out.println(actualWidth);
        for (int columns = 0; columns < board.length; columns++) {
            for (int rows = 0; rows < board[columns].length; rows++) {
                if(columns==0&&rows==0){
                    board[columns][rows]= Cell.X;
                }
                if(columns==board.length-1&&rows==board[columns].length-1){
                    board[columns][rows]= Cell.C;
                }
                if(board[columns][rows]==null){
                board[columns][rows] = Cell._;}
            }
        }
        for (int i = 0; i < change.length ; i++) {
            for (int j = 0; j < change[i].length ; j++) {
                change[i][j]=board[i][j];
            }
        }
        while(times==0) {
            System.out.println("Board array:");
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    System.out.print(board[i][j]);
                }
                System.out.println("");
            }
            times=1;
        }
    }
}
