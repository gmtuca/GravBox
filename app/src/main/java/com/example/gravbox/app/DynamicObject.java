package com.example.gravbox.app;


/**
 * Created by gmtuk on 23/05/2014.
 */
public abstract class DynamicObject extends GravBoxObject implements Runnable {

    public double vx, vy;
    public Box box;

    public DynamicObject(double x, double y, Box box) {
        super(x,y);

        this.vx = 0;
        this.vy = 0;

        this.box = box;
    }

    public void moveTo(double x, double y){
        this.x = x;
        this.y = y;
    }

    public void moveBy(double dx, double dy){
        moveTo(x + dx, y + dy);
    }

    @Override
    public void setBeingTouched(boolean beingTouched) {
        if(beingTouched)
            reset();

        this.beingTouched = beingTouched;
    }

    public void reset(){
        this.vx = 0;
        this.vy = 0;
    }

    public void startMovement(){
        new Thread(this).start();
    }

    @Override
    public void run(){
        while (true) {
            if(!beingTouched) {
                if(x + vx < box.getLEFT())
                    vx = Math.abs(vx);
                else if(x + vx > box.getRIGHT())
                    vx = -Math.abs(vx);

                if(y + vy < box.getTOP())
                    vy = Math.abs(vy);
                else if(x + vx > box.getBOTTOM())
                    vy = -Math.abs(vx);

                moveBy(vx, vy);
            }

            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                System.err.println(this + " has been interrupted");
            }
        }

    }


    public void setY(double y) {
        this.y = y;
    }

    public double getVx() {
        return vx;
    }

    public void setVx(double vx) {
        this.vx = vx;
    }

    public double getVy() {
        return vy;
    }

    public void setVy(double vy) {
        this.vy = vy;
    }
}
