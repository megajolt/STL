package com.example.qhoffman7249.stl;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class newgame extends AppCompatActivity {
    //random comment
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newgame);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        double width = dm.widthPixels;
        double height = dm.heightPixels;

        height = height * .8;
        width = width * .6;

        int intwidth = (int)width;
        int intheight = (int)height;
        getWindow().setLayout(intwidth,intheight);
        Button submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText gamename = findViewById(R.id.gameName);
                writefile(gamename.getText().toString(), "game.txt");
                writefile("begining", "progress.txt");
                Intent game = new Intent(newgame.this, GameActivity.class);
                game.putExtra("progress", "begining");
                startActivity(game);
            }
        });
    }
    public String readfile(String filename, String action){
        FileInputStream fis = null;
        try {
            fis = openFileInput(filename);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String content;
            int check = 0;
            while((content = br.readLine()) != null){
                sb.append(content);
                check = check + 1;
            }
            if(check == 0){
                return "null";
            }
            else{
                if(action.equals("save")){
                    return sb.toString();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "random";
    }
    public void writefile(String response, String filename){
        String existing = readfile(filename, "save");
        String mresponse = "null";
        if(existing.equals("random")){
            mresponse = response;
        }
        else{
            mresponse = existing + "-" + response;
        }
        FileOutputStream fos = null;
        try{
            fos = openFileOutput(filename, Context.MODE_PRIVATE);
            fos.write(mresponse.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
