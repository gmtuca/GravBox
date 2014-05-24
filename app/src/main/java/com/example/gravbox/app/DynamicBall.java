package com.example.gravbox.app;

import android.graphics.Canvas;


/**
 * Created by gmtuk on 11/05/2014.
 */
public class DynamicBall extends Gravitable implements Circular {

    private int radius;

    public DynamicBall(double x, double y, int radius, Box box){
        super(x, y, 0.8, box);

        this.radius = radius;
    }

    @Override
    public void moveTo(double x, double y){
        if(x < box.getLEFT() + radius) {
            this.x = box.getLEFT() + radius;
            this.vx = Math.abs(vx);
        }
        else if(x > box.getRIGHT() - radius) {
            this.x = box.getRIGHT() - radius;
            this.vx = -Math.abs(vx);
        }
        else
            this.x = x;

        if(y < box.getTOP() + radius)
            this.y = box.getTOP() + radius;
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
    public int getRadius() { return radius; }

    @Override
    public int getFloor() {
        return box.getBOTTOM() - radius;
    }

    @Override
    public String toString() {
        return "DynamicBall - (" + x + ", " + y + "), [" + radius + "]";
    }
}
