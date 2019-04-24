package com.example.qhoffman7249.stl;

public class Animation {
    public enum Cell{C,X,W,_}
    private Cell [][] board;
    private Cell[][] change;
    int times;
    boolean cS =true;

    public Animation() {

    }
    public void cAnimation() {
        board = new Cell[10][10];
        change= new Cell[10][10];
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
        for (int t = 0; t < board.length ; t++) {
            for (int u = 0; u < board[t].length ; u++) {
                    //all surrounding squares
                    if(t!=0&&t!=board[0].length-1&&u!=0&&u!=board.length-1&&board[t][u]==Cell.C){
                        switch(board[t-1][u]){
                            case _:
                                System.out.println("move O.K");
                                break;
                            case X:
                                System.out.println("GOAL!!");
                                break;
                            case W:
                                System.out.println("Wall");
                                break;
                        }
                        switch (board[t-1][u-1]){
                            case _:
                                System.out.println("move O.K");
                                break;
                            case X:
                                System.out.println("GOAL!!");
                                break;
                            case W:
                                System.out.println("Wall");
                                break;
                        }
                        switch (board[t][u-1]){
                            case _:
                                System.out.println("move O.K");
                                break;
                            case X:
                                System.out.println("GOAL!!");
                                break;
                            case W:
                                System.out.println("Wall");
                                break;
                        }
                        switch (board[t][u+1]){
                            case _:
                                System.out.println("move O.K");
                                break;
                            case X:
                                System.out.println("GOAL!!");
                                break;
                            case W:
                                System.out.println("Wall");
                                break;
                        }
                        switch (board[t+1][u]){
                            case _:
                                System.out.println("move O.K");
                                break;
                            case X:
                                System.out.println("GOAL!!");
                                break;
                            case W:
                                System.out.println("Wall");
                                break;
                        }
                        switch (board[t+1][u+1]) {
                            case _:
                                System.out.println("move O.K");
                                break;
                            case X:
                                System.out.println("GOAL!!");
                                break;
                            case W:
                                System.out.println("Wall");
                                break;
                        }
                        switch (board[t-1][u+1]) {
                            case _:
                                System.out.println("move O.K");
                                break;
                            case X:
                                System.out.println("GOAL!!");
                                break;
                            case W:
                                System.out.println("Wall");
                                break;
                        }
                        switch (board[t+1][u-1]){
                            case _:
                                System.out.println("move O.K");
                                break;
                            case X:
                                System.out.println("GOAL!!");
                                break;
                            case W:
                                System.out.println("Wall");
                                break;
                        }
                    }
                    //top left corner
                    else if(t==0&&u==0&&board[t][u]==Cell.C){
                        switch (board[t][u+1]){
                            case _:
                                System.out.println("move O.K");
                                break;
                            case X:
                                System.out.println("GOAL!!");
                                break;
                            case W:
                                System.out.println("Wall");
                                break;
                        }
                        switch (board[t+1][u+1]){
                            case _:
                                System.out.println("move O.K");
                                break;
                            case X:
                                System.out.println("GOAL!!");
                                break;
                            case W:
                                System.out.println("Wall");
                                break;
                        }
                        switch (board[t+1][u]){
                            case _:
                                System.out.println("move O.K");
                                break;
                            case X:
                                System.out.println("GOAL!!");
                                break;
                            case W:
                                System.out.println("Wall");
                                break;
                        }

                    }
                    //top right corner
                    else if(t==0&&u==board.length-1&&board[t][u]==Cell.C){
                        switch (board[t][u-1]){
                            case _:
                                System.out.println("move O.K");
                                break;
                            case X:
                                System.out.println("GOAL!!");
                                break;
                            case W:

                                break;
                        }
                        switch (board[t+1][u-1]){
                            case _:
                                System.out.println("move O.K");
                                break;
                            case X:
                                System.out.println("GOAL!!");
                                break;
                            case W:
                                System.out.println("Wall");
                                break;
                        }
                        switch (board[t+1][u]){
                            case _:
                                System.out.println("move O.K");
                                break;
                            case X:
                                System.out.println("GOAL!!");
                                break;
                            case W:
                                System.out.println("Wall");
                                break;
                        }

                    }
                    //bottom left corner
                    else if(t==board[0].length-1&&u==0&&board[t][u]==Cell.C){
                        switch (board[t][u+1]){
                            case _:
                                System.out.println("move O.K");
                                break;
                            case X:
                                System.out.println("GOAL!!");
                                break;
                            case W:
                                System.out.println("Wall");
                                break;
                        }
                        switch(board[t-1][u]){
                            case _:
                                System.out.println("move O.K");
                                break;
                            case X:
                                System.out.println("GOAL!!");
                                break;
                            case W:
                                System.out.println("Wall");
                                break;
                        }
                        switch (board[t-1][u+1]){
                            case _:
                                System.out.println("move O.K");
                                break;
                            case X:

                                break;
                            case W:
                                System.out.println("Wall");
                                break;
                        }
                    }
                    //bottom right corner surrounding squares
                    else if(t==board[0].length-1&&u==board.length-1&&board[t][u]==Cell.C){
                        switch(board[t-1][u]){
                            case _:
                                System.out.println("move O.K");
                                break;
                            case X:
                                System.out.println("GOAL!!");
                                break;
                            case W:
                                System.out.println("Wall");
                                break;
                        }
                        switch (board[t-1][u-1]){
                            case _:
                                System.out.println("move O.K");
                                break;
                            case X:
                                System.out.println("GOAL!!");
                                break;
                            case W:
                                System.out.println("Wall");
                                break;
                        }
                        switch (board[t][u-1]){
                            case _:
                                System.out.println("move O.K");
                                break;
                            case X:
                                System.out.println("GOAL!!");
                                break;
                            case W:
                                System.out.println("Wall");
                                break;
                        }
                }
                //right, no corners
                else if(t==board[0].length-1&&u<board.length-1&& u>0&&board[t][u]==Cell.C){
                        switch(board[t-1][u]) {
                            case _:
                                System.out.println("move O.K");
                                break;
                            case X:
                                System.out.println("GOAL!!");
                                break;
                            case W:
                                System.out.println("Wall");
                                break;
                        }
                        switch(board[t][u-1]) {
                            case _:
                                System.out.println("move O.K");
                                break;
                            case X:
                                System.out.println("GOAL!!");
                                break;
                            case W:
                                System.out.println("Wall");
                                break;
                        }
                        switch(board[t-1][u-1]) {
                            case _:
                                System.out.println("move O.K");
                                break;
                            case X:
                                System.out.println("GOAL!!");
                                break;
                            case W:
                                System.out.println("Wall");
                                break;
                        }
                        switch(board[t][u+1]) {
                            case _:
                                System.out.println("move O.K");
                                break;
                            case X:
                                System.out.println("GOAL!!");
                                break;
                            case W:
                                System.out.println("Wall");
                                break;
                        }
                        switch(board[t-1][u+1]) {
                            case _:
                                System.out.println("move O.K");
                                break;
                            case X:
                                System.out.println("GOAL!!");
                                break;
                            case W:
                                System.out.println("Wall");
                                break;
                        }
                    }
                    //bottom, no corners
                else if(t < board[0].length-1 && u == board.length-1 && t > 0&&board[t][u]==Cell.C) {
                        switch(board[t][u-1]) {
                            case _:
                                System.out.println("move O.K");
                                break;
                            case X:
                                System.out.println("GOAL!!");
                                break;
                            case W:
                                System.out.println("Wall");
                                break;
                        }
                        switch(board[t-1][u]) {
                            case _:
                                System.out.println("move O.K");
                                break;
                            case X:
                                System.out.println("GOAL!!");
                                break;
                            case W:
                                System.out.println("Wall");
                                break;
                        }
                        switch(board[t-1][u-1]) {
                            case _:
                                System.out.println("move O.K");
                                break;
                            case X:
                                System.out.println("GOAL!!");
                                break;
                            case W:
                                System.out.println("Wall");
                                break;
                        }
                        switch(board[t+1][u-1]) {
                            case _:
                                System.out.println("move O.K");
                                break;
                            case X:
                                System.out.println("GOAL!!");
                                break;
                            case W:
                                System.out.println("Wall");
                                break;
                        }

                        switch(board[t+1][u]) {
                            case _:
                                System.out.println("move O.K");
                                break;
                            case X:
                                System.out.println("GOAL!!");
                                break;
                            case W:
                                System.out.println("Wall");
                                break;
                        }
                    }
                    //left no corners
                else if(t == 0 && u < board.length-1 && u>0&&board[t][u]==Cell.C){
                        switch(board[t][u-1]) {
                            case _:
                                System.out.println("move O.K");
                                break;
                            case X:
                                System.out.println("GOAL!!");
                                break;
                            case W:
                                System.out.println("Wall");
                                break;
                        }
                        switch(board[t][u+1]) {
                            case _:
                                System.out.println("move O.K");
                                break;
                            case X:
                                System.out.println("GOAL!!");
                                break;
                            case W:
                                System.out.println("Wall");
                                break;
                        }
                        switch(board[t+1][u+1]) {
                            case _:
                                System.out.println("move O.K");
                                break;
                            case X:
                                System.out.println("GOAL!!");
                                break;
                            case W:
                                System.out.println("Wall");
                                break;
                        }
                        switch(board[t+1][u]) {
                            case _:
                                System.out.println("move O.K");
                                break;
                            case X:
                                System.out.println("GOAL!!");
                                break;
                            case W:
                                System.out.println("Wall");
                                break;
                        }
                        switch(board[t+1][u-1]) {
                            case _:
                                System.out.println("move O.K");
                                break;
                            case X:
                                System.out.println("GOAL!!");
                                break;
                            case W:
                                System.out.println("Wall");
                                break;
                        }
                    }
                    //top, no corners
                else if(t < board[0].length-1 && t > 0 && u == 0&&board[t][u]==Cell.C){
                        switch(board[t-1][u]) {
                            case _:
                                System.out.println("move O.K");
                                break;
                            case X:
                                System.out.println("GOAL!!");
                                break;
                            case W:
                                System.out.println("Wall");
                                break;
                        }
                        switch(board[t+1][u]) {
                            case _:
                                System.out.println("move O.K");
                                break;
                            case X:
                                System.out.println("GOAL!!");
                                break;
                            case W:
                                System.out.println("Wall");
                                break;
                        }
                        switch(board[t+1][u+1]) {
                            case _:
                                System.out.println("move O.K");
                                break;
                            case X:
                                System.out.println("GOAL!!");
                                break;
                            case W:
                                System.out.println("Wall");
                                break;
                        }
                        switch(board[t][u+1]) {
                            case _:
                                System.out.println("move O.K");
                                break;
                            case X:
                                System.out.println("GOAL!!");
                                break;
                            case W:
                                System.out.println("Wall");
                                break;
                        }
                        switch(board[t-1][u+1]) {
                            case _:
                                System.out.println("move O.K");
                                break;
                            case X:
                                System.out.println("GOAL!!");
                                break;
                            case W:
                                System.out.println("Wall");
                                break;
                        }
                    }
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
