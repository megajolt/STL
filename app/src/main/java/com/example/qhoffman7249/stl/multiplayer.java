package com.example.qhoffman7249.stl;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ListIterator;

public class multiplayer extends AppCompatActivity {
    public static boolean thehost;
    public static boolean theclient;
    public int people = 1;
    EditText member1;
    EditText member2;
    EditText member3;
    EditText member4;
    private boolean open = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplayer);
        member1  = findViewById(R.id.member1);
        member2 = findViewById(R.id.member2);
        member3 = findViewById(R.id.member3);
        member4 = findViewById(R.id.member4);
        Button addmember = findViewById(R.id.addmember);
        LinearLayout main = findViewById(R.id.main);
        TextView ip = new TextView(this);
        WifiManager wm = (WifiManager) getSystemService(WIFI_SERVICE);
        //String ipaddr = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
        int ipaddr = wm.getConnectionInfo().getIpAddress();
        ip.setText("test");
        main.addView(ip);
        addmember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                people++;
                if(people == 2){
                    member2.setVisibility(View.VISIBLE);
                }
                if(people == 3){
                    member2.setVisibility(View.VISIBLE);
                }
                if(people == 4){
                    member2.setVisibility(View.VISIBLE);
                }
            }
        });
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    DatagramSocket ss = new DatagramSocket(7000);
                    byte[] receive = new byte[65];
                    DatagramPacket rec = null;
                    while(open){
                        rec = new DatagramPacket(receive, receive.length);
                        ss.receive(rec);
                        receive = new byte[65];
                    }
                }catch(Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    boolean checkWifi(){
        ConnectivityManager cm =
                (ConnectivityManager)multiplayer.this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        boolean isWiFi = activeNetwork.getType() == ConnectivityManager.TYPE_WIFI;
        if(isConnected && isWiFi){
            return true;
        }
        else if(isConnected && !isWiFi){
            return false;
        }
        else{
            return false;
        }
    }
}
