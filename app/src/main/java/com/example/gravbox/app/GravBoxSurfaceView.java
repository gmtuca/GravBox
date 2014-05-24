package com.example.gravbox.app;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.util.HashSet;

/**
 * Created by gmtuk on 12/05/2014.
 */
public class GravBoxSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private final HashSet<DynamicObject> movingObjects;

    public GravBoxSurfaceView(Context context) {
        super(context);

        movingObjects = new HashSet<DynamicObject>();

        SurfaceHolder holder = getHolder();

        if(holder != null)
            holder.addCallback(this);
    }

    public DynamicObject getObjectAt(double x, double y){

        for(DynamicObject m : movingObjects)
            if(m.containsPoint(x,y)) {
                System.out.println("You are touching me");
                return m;
            }

        return null;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        synchronized (movingObjects) {
            for (DynamicObject m : movingObjects)
                m.draw(canvas);
        }
    }


    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {  }


    private DynamicObject objectBeingTouched;

    private double timePressed = -1;
    private double timeReleased = -1;

    private double prevX = -1;
    private double prevY = -1;

    private double dragSpeedX = 0;
    private double dragSpaceX = 0;

    private double vx, vy;

    private Box box;

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

        box = new Box(0, getHeight(), 0, getWidth());
        System.out.println(box);


        //DynamicRectangle r = new DynamicRectangle(500,500,150,100, box);
        //movingObjects.add(r);

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                double x = motionEvent.getX();
                double y = motionEvent.getY();

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        System.out.println("Down");
                        objectBeingTouched = getObjectAt(x, y);

                        timePressed = System.currentTimeMillis();
                        prevX = x;
                        prevY = y;

                        //only one object at a time for now
                        if (objectBeingTouched == null && movingObjects.size() == 0) {
                            //add a new ball
                            DynamicBall newBall = new DynamicBall(motionEvent.getX(), motionEvent.getY(), 130, box);
                            synchronized (movingObjects) {
                                movingObjects.add(newBall);
                            }
                            newBall.setBeingTouched(true);
                            newBall.startMovement();

                            System.out.println("New " + newBall);
                        }

                        break;
                    case MotionEvent.ACTION_MOVE:
                        System.out.println("Move");
                        if (objectBeingTouched != null) {
                            objectBeingTouched.setBeingTouched(true);
                            objectBeingTouched.moveTo(x, y);

                            dragSpaceX += x - prevX;

                            if (x > prevX)
                                vx = 1;
                            else if (x < prevX)
                                vx = -1;
                            else
                                vx = 0;

                            prevX = x;
                            prevY = y;
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        System.out.println("Released");
                        timeReleased = System.currentTimeMillis();

                        vx *= 5 * dragSpaceX / (timeReleased - timePressed);
                        if (objectBeingTouched != null)
                            objectBeingTouched.setVx(vx);

                        DynamicObject objectBeingReleased = getObjectAt(x, y);

                        if (objectBeingReleased != null)
                            objectBeingReleased.setBeingTouched(false);


                        break;
                    default:
                        System.out.println("Something else");
                        break;
                }

                return true;
            }
        });

        //for any objects that are initiated on the start
        synchronized (movingObjects) {
            for (DynamicObject m : movingObjects)
                m.startMovement();
        }

        new RefreshThread(this).start();

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {}
}
