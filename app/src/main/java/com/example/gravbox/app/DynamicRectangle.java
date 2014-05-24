package com.example.gravbox.app;

import android.graphics.Canvas;

/**
 * Created by gmtuk on 23/05/2014.
 */
public class DynamicRectangle extends Gravitable implements Quadrilateral {

    private int width, height;

    public DynamicRectangle(double x, double y, int width, int height, Box box) {
        super(x, y, 0.05, box);

        this.width = width;
        this.height = height;
    }

    @Override
    public void moveTo(double x, double y){
        if(x < box.getLEFT() + width) {
            this.x = box.getLEFT() + width;
            this.vx = Math.abs(vx);
        }
        else if(x > box.getRIGHT() - width) {
            this.x = box.getRIGHT() - width;
            this.vx = -Math.abs(vx);
        }
        else
            this.x = x;

        if(y < box.getTOP() + height)
            this.y = box.getTOP() + height;
        else if(y > getFloor())
            this.y = getFloor();
        else
            this.y = y;
    }

    @Override
    public void draw(Canvas c){
       Drawer.draw(this, c);
    }

    @Override
    public boolean containsPoint(double x, double y) {
        return Physics.containsPoint(this, x, y);
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getFloor() {
        return box.getBOTTOM() - height;
    }

    @Override
    public String toString() {
        return "DynamicRectangle - (" + x + ", " + y + "), [" + width + ", " + height + "]";
    }
}
