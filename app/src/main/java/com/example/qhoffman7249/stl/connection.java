package com.example.qhoffman7249.stl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
//this is the Dylan
public class connection extends Service {
    public String testlink = "nothing";
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return super.onStartCommand(intent, flags, startId);
    }
    public String readprogramfile(String filename){
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
            return sb.toString();
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
    public void interpret(String command){

    }
    //http://10.162.34.69/
    public void loadfile(){
        RequestQueue queue = Volley.newRequestQueue(connection.this);
        String url = "http://10.162.34.69/artifice/program.artifice";
        final StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("not allowed")){
                    Toast.makeText(connection.this, "response: " + response, Toast.LENGTH_SHORT).show();
                }
                else{
                    response = response.trim();
                    response = response.substring(0, response.length() - 1);
                    interpret(response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(connection.this, "error: " + error, Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(request);
    }
}
