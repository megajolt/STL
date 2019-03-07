package com.example.qhoffman7249.stl;

import android.content.res.Resources;

public class Animation {
    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }
    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }
    public int phoneWidth= getScreenWidth();
    public int phoneHeight= getScreenHeight();}
