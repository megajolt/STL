package com.example.qhoffman7249.stl;

import android.content.Context;
import android.graphics.Point;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.widget.ImageView;

public class Crew extends AppCompatImageView{
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
        //this.getLocationOnScreen(position);
        this.setLayoutParams(new DrawerLayout.LayoutParams(200,200));
    }
}
