package com.example.gravbox.app;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.Random;

/**
 * Created by gmtuk on 24/05/2014.
 */
public abstract class GravBoxObject implements Shape{

    double x, y;
    Paint paint;

    protected GravBoxObject(double x, double y) {
        this.x = x;
        this.y = y;

        this.paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        setRandomColor();
    }

    public void setRandomColor(){
        Random rd = new Random();
        this.paint.setARGB(255, rd.nextInt(255), rd.nextInt(255), rd.nextInt(255));
    }

    public boolean beingTouched = false;

    public void setBeingTouched(boolean beingTouched) {
        this.beingTouched = beingTouched;
    }

    abstract void draw(Canvas c);
    abstract boolean containsPoint(double x, double y);

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public Paint getPaint(){
        return paint;
    }

}
