package com.example.gravbox.app;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Random;

/**
 * Created by gmtuk on 11/05/2014.
 */
public class Ball extends SurfaceView implements SurfaceHolder.Callback, Gravitable, Circular {

    private float x, y;
    private int radius;
    private Paint paint;

    private MainThread thread;
    private int r, g, b;

    public Ball(Context context, float x, float y, int radius) {
        super(context);
        this.x = x;
        this.y = y;
        this.radius = radius;

        getHolder().addCallback(this);
        this.thread = new MainThread(getHolder(), this);

        this.paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        Random rd = new Random();
        this.r =  rd.nextInt();
        this.g =  rd.nextInt();
        this.b =  rd.nextInt();

        this.paint.setARGB(1, r, g, b);
    }

    private double t = 0;
    private double v = 0;
    private double elasticity = 0.8;
    private double resistance = 1;

    public float getX() {  return x;  }
    public void setX(float x) {  this.x = x; }

    public float getY() { return y; }
    public void setY(float y) { this.y = y; }

    public int getRadius() { return radius;  }

    public double getResistance() { return resistance; }
    public void setResistance(double resistance) { this.resistance = resistance; }

    public double getElasticity() { return elasticity; }

    public double getVelocity() { return v;}
    public void setVelocity(double v) {  this.v = v; }

    public double getTime() { return t; }
    public void setTime(double t) { this.t = t; }

    public int getFloor(){ return getHeight() - radius; }

    public void move(){
        Physics.applyGravity(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(x, y, radius, paint);
    }

    public void clearBall(Canvas canvas){
        canvas.drawColor(Color.WHITE);
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {  }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {  }





}
