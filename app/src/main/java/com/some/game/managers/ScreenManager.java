package com.some.game.managers;

import android.content.Context;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;

/**
 *  Screen size manager
 *  Created on 3/6/2018
 *
 * @author ahmet
 */

public class ScreenManager {
    private static Point screen;
    public static void init(Context context) {
        Display display = ((AppCompatActivity) context).getWindowManager().getDefaultDisplay();
        screen = new Point();
        display.getSize(screen);
    }

    public static int x(){
        return screen.x;
    }
    public static int y(){
        return screen.y;
    }


}
