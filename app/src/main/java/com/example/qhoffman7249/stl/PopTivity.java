package com.example.qhoffman7249.stl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
public class PopTivity extends variables {
    public ScrollView current;
    public Button rename;
    public EditText renameField;
    public TextView nameDisplay;
    Crew v=new Crew();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_tivity);
        rename=findViewById(R.id.renameButton);
        renameField=findViewById(R.id.crewNameField);
        nameDisplay=findViewById(R.id.nameDisplay);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        double width = dm.widthPixels;
        double height = dm.heightPixels;
        width = width * 1.2;
        nameDisplay.setText(v.firstName);
        renameField.setText(v.firstName);
        Toast.makeText(PopTivity.this, ""+v.firstName, Toast.LENGTH_LONG).show();
        rename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameSet();
            }
        });
    }
    public void nameSet(){
        v.firstName=renameField.getText().toString();
        nameDisplay.setText(v.firstName);
    }
}