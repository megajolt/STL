package com.example.qhoffman7249.stl;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import com.example.qhoffman7249.stl.connection;
import java.util.StringTokenizer;

public class gamesearch extends AppCompatActivity {
    public ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamesearch);
        /*boolean test = check();
        readfile("game.txt", "load");
        EditText search = findViewById(R.id.search);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                gamesearch.this.adapter.getFilter().filter(editable);
            }
        });*/
        Intent t = new Intent(gamesearch.this, GameActivity.class);
        startActivity(t);
    }
    public void loadfile(){
        RequestQueue queue = Volley.newRequestQueue(gamesearch.this);
        String url = "http://philosophism.org/php/userdata.php";
        final StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("not allowed")){
                    Toast.makeText(gamesearch.this, "response: " + response, Toast.LENGTH_SHORT).show();
                }
                else{
                    response = response.trim();
                    response = response.substring(0, response.length() - 1);
                   load(response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(gamesearch.this, "error: " + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", "all");
                params.put("action", "puzzles");
                return params;
            }

            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }
        };
        queue.add(request);
    }
    public void load(String result){
        String[] myarray = result.split("-");
        ListView myview = findViewById(R.id.puzzles);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, myarray);
        myview.setAdapter(adapter);
        myview.setTextFilterEnabled(true);
        myview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //String puzzlename = String.valueOf(adapterView.getItemAtPosition(i));
                readprogress(i);
            }
        });

    }
    public boolean check(){
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();
        if(netinfo != null){
            return true;
        }
        else {
            return false;
        }
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
                else if(action.equals("load")){
                    load(sb.toString());
                    Toast.makeText(gamesearch.this, "load ran", Toast.LENGTH_SHORT).show();
                    return "nnull";
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
            Intent l = new Intent(gamesearch.this, StlMenu.class);
            Toast.makeText(gamesearch.this, "Error: you are not connected to the internet and no games are saved", Toast.LENGTH_LONG).show();
            startActivity(l);
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
    public void readprogress(int cgame){
        String p = readfile("progress.txt", "cprogress");
        String[] progress = p.split("-");
        String fprogress = progress[cgame];
        Intent game = new Intent(gamesearch.this, GameActivity.class);
        game.putExtra("progress", fprogress);
        startActivity(game);
    }
    private class customadapter extends BaseAdapter{
        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //convertView = getLayoutInflater().inflate(R.id.adapterlayout);
            return null;
        }
    }
}
