package com.example.gravbox.app;

import android.graphics.Canvas;

/**
 * Created by gmtuk on 24/05/2014.
 */
public class StaticRectangle extends GravBoxObject implements Quadrilateral{

    int height, width;

    public StaticRectangle(double x, double y, int height, int width) {
        super(x, y);
        this.height = height;
        this.width = width;
    }

    @Override
    boolean containsPoint(double x, double y) {
        return Physics.containsPoint(this, x, y);
    }

    @Override
    public void draw(Canvas c){
        Drawer.draw(this, c);
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }
}
