package com.example.qhoffman7249.stl;

import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Animation {
    public enum Cell{C,X,W,_}
    private Button [][] board;
    private Button[][] change;
    int times;
    boolean cS =true;
    variables Variables=new variables();
    GameActivity mActivity=new GameActivity();
    public Animation() {

    }
    public void cAnimation() {
        int bitHeight=Variables.bitmapHeight;
        int bitWidth= Variables.bitmapWidth;
        board = new Button[bitHeight][bitWidth];
        change = new Button[bitHeight][bitWidth];
        GridLayout aSpace= Variables.aSpace;
        for (int i = 0; i <bitHeight ; i++) {
            for (int j = 0; j <bitWidth ; j++) {
                board[i][j].setBackgroundResource(R.drawable.crew1);
                aSpace.addView(board[i][j]);
            }
        }
        for (int columns = 0; columns < board.length; columns++) {
            for (int rows = 0; rows < board[columns].length; rows++) {
                if(columns==0&&rows==0){
                    board[columns][rows].setText("X");
                }
                if(columns==board.length-1&&rows==board[columns].length-1){
                    board[columns][rows].setText("C");
                }
                if(board[columns][rows]==null){
                board[columns][rows].setText("_");}
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
                System.out.println(" ");
            }
            times=1;
        }
    }
}
