package com.example.qhoffman7249.stl;

import android.content.Context;
import android.graphics.Point;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Size;
import android.widget.ImageView;
import android.widget.Toast;

public class Crew extends AppCompatImageView{
    //drool
    private String crewName;
    private int[] position;
    public String firstName;
    public Crew(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public Crew(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    public Crew(Context context, int crewNumber) {
        super(context);
        crewName = "Crew " + (crewNumber + 1);
        this.setImageResource(R.drawable.crew1);
        this.setMaxWidth(50);
        this.setMaxHeight(50);
        //this.getLocationOnScreen(position);
        this.setLayoutParams(new DrawerLayout.LayoutParams(30,30));
        //Toast.makeText(context, "size: " + this.getSize(), Toast.LENGTH_SHORT).show();
    }
    int getSize(){
        return this.getSize();

    }
}
