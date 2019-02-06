package com.example.qhoffman7249.stl;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ListIterator;

public class multiplayer extends AppCompatActivity {
    public static boolean thehost;
    public static boolean theclient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplayer);
        Button host = findViewById(R.id.host);
        Button client = findViewById(R.id.client);
        host.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout hostClicked = findViewById(R.id.hostClicked);
                LinearLayout clientClicked = findViewById(R.id.clientClicked);
                clientClicked.setVisibility(View.GONE);
                hostClicked.setVisibility(View.VISIBLE);
                if(thehost){
                    hostClicked.setVisibility(View.GONE);
                    thehost = false;
                }
                else{
                    thehost = true;
                }
            }
        });
        client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout hostClicked = findViewById(R.id.hostClicked);
                LinearLayout clientClicked = findViewById(R.id.clientClicked);
                clientClicked.setVisibility(View.VISIBLE);
                hostClicked.setVisibility(View.GONE);
                if(theclient){
                    clientClicked.setVisibility(View.GONE);
                    theclient = false;
                }
                else{
                    theclient = true;
                }
            }
        });
        ConnectivityManager cm =
                (ConnectivityManager)multiplayer.this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        boolean isWiFi = activeNetwork.getType() == ConnectivityManager.TYPE_WIFI;
        if(isConnected && isWiFi){
            Toast.makeText(multiplayer.this, "connected to wifi", Toast.LENGTH_LONG).show();
        }
        else if(isConnected && !isWiFi){
            Toast.makeText(this, "connected to data", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "not connected", Toast.LENGTH_LONG).show();
        }
    }
}
