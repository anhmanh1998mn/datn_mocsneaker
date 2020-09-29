package com.example.manhvan.datn_mocsneaker.util;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

public class AndroidDeviceHelper {
    public static int getWithScreen(Context context){
        WindowManager windowManager= (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display=windowManager.getDefaultDisplay();
        Point size=new Point();
        display.getSize(size);
        return size.x;
    }
    public static int getHeighScreen(Context context){
        WindowManager windowManager= (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display=windowManager.getDefaultDisplay();
        Point size=new Point();
        display.getSize(size);
        return size.y;
    }
}
