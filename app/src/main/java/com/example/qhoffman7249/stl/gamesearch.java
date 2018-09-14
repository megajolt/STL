package com.example.qhoffman7249.stl;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class gamesearch extends AppCompatActivity {
    public ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamesearch);
        boolean test = check();
        if(test) {
            loadfile();
        }
        else{
            readfile("game.txt", "load");
        }
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
        });
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
                    writefile(response, "game.txt");
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
                String puzzlename = String.valueOf(adapterView.getItemAtPosition(i));
                EditText search = findViewById(R.id.search);
                search.setText(puzzlename);
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
    public void readfile(String filename, String action){
        String op = "hello world";
        if(action.equals("save")){
            writefile(op, "game.txt");
        }
    }
    public void writefile(String mresponse, String filename){
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
