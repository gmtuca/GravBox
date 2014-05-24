package com.example.gravbox.app;

/**
 * Created by gmtuk on 12/05/2014.
 */
public abstract class Gravitable extends DynamicObject {

    private double t;
    private double e;

    public Gravitable(double x, double y, double e, Box box) {
        super(x, y, box);
        this.e = e;
        this.t = 1;
    }

    @Override
    public void run(){
        while (true) {

            if(!beingTouched) {
                Physics.applyGravity(this);

                //TODO y += vy ??????

                //collision with walls
                moveBy(vx, 0);

                vx *= 0.999;

            }

            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                System.err.println(this + " has been interrupted");
            }
        }

    }

    public void reset(){
        super.reset();
        this.t = 1;
    }

    public double getTime(){
        return t;
    }

    public void setTime(double t){
        this.t = t;
    }

    public double getElasticity(){
        return e;
    }

    public int getFloor(){
        return box.getBOTTOM();
    }

}
