package com.example.qhoffman7249.stl;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;

public class settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        double width = dm.widthPixels;
        double height = dm.heightPixels;

        height = height * .8;
        width = width * .6;

        int intwidth = (int)width;
        int intheight = (int)height;
        getWindow().setLayout(intwidth,intheight);
    }
    public void writeFile(String fileContents){
        writeFile("Audio=On\n");
        String filename = "settings.txt";
        FileOutputStream outputStream;
        File file = new File(settings.this.getFilesDir(), filename);

        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(fileContents.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void appendFile(String fileContents){
        //appendFile("Audio=On\n");
        String filename = "settings.txt";
        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(fileContents.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void readFile(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("file.txt"));
            try {
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();

                while (line != null) {
                    sb.append(line);
                    sb.append(System.lineSeparator());
                    line = br.readLine();
                }
                String everything = sb.toString();
            } finally {
                br.close();
            }
        }catch(Exception e){

        }
    }
}
