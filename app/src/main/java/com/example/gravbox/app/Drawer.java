package com.example.gravbox.app;

import android.graphics.Canvas;
import android.graphics.Color;

/**
 * Created by gmtuk on 24/05/2014.
 */
public abstract class Drawer {

    public static void clearCanvas(Canvas canvas){
        canvas.drawColor(Color.WHITE);
    }

    public static void draw(Quadrilateral q, Canvas ca){
        ca.drawRect((float)q.getX() - q.getWidth()/2, (float)q.getY() - q.getHeight()/2,
                (float)q.getX() + q.getWidth()/2, (float) q.getY() + q.getHeight()/2, q.getPaint());
    }

    public static void draw(Circular c, Canvas ca) {
        ca.drawCircle((float)c.getX(), (float)c.getY(), c.getRadius(), c.getPaint());
    }

}
