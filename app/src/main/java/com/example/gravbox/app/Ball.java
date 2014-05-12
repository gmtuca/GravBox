package com.example.gravbox.app;

import android.graphics.Paint;
import java.util.Random;

/**
 * Created by gmtuk on 11/05/2014.
 */
public class Ball implements Gravitable, Circular, Runnable {

    private float x, y;
    private int radius;
    private BallSurfaceView ballSurfaceView;
    private Paint paint;
    private float vx, vy;

    private int r, g, b;

    public Ball(float x, float y, int radius, BallSurfaceView ballSurfaceView) {
        this.x = x;
        this.y = y;
        this.vx = 0.0f;
        this.vy = 0.0f;
        this.radius = radius;
        this.ballSurfaceView = ballSurfaceView;

        this.paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        Random rd = new Random();
        this.r =  rd.nextInt();
        this.g =  rd.nextInt();
        this.b =  rd.nextInt();

        this.paint.setARGB(1, r, g, b);

        this.floor = ballSurfaceView.getHeight() - radius;
    }

    private int floor;

    public void startBall(){
        new Thread(this).start();
    }

    private double t = 1;

    public void moveXYby(double dx, double dy){
        x += dx;
        y += dy;
    }

    public float getX() {  return x;  }
    public void setX(float x) {  this.x = x; }

    public float getY() { return y; }
    public void setY(float y) { this.y = y; }

    public int getRadius() { return radius; }

    public double getElasticity() { return 0.8; }

    public float getVelocityX() { return vx;}
    public void setVelocityX(float vx) {  this.vx = vx; }

    public float getVelocityY() { return vy;}
    public void setVelocityY(float vy) {  this.vy = vy; }

    public double getTime() { return t; }
    public void setTime(double t) { this.t = t; }

    public int getFloor(){ return floor; }

    public int getMass(){
        return radius; //this could be improved
    }

    public void run(){
        while (true) {

            Physics.applyGravity(this);

            //TODO y += vy ??????

            System.out.println("vx " + vx);
            x += vx;

            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                System.err.println("Ball has been interrupted");
            }
        }

    }

    public Paint getPaint() {
        return paint;
    }

    @Override
    public String toString() {
        return "Ball - (" + x + ", " + y + "), [" + radius + "], {" + r + ", " + g + ", " + b + "}";
    }
}
