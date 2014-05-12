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
public class BallSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private final HashSet<Ball> balls;

    public BallSurfaceView(Context context) {
        super(context);

        balls = new HashSet<Ball>();

        //balls.add(new Ball(450, 450, 50));
        //balls.add(new Ball(300, 350, 50));
        //balls.add(new Ball(100, 100, 75));

        SurfaceHolder holder = getHolder();

        if(holder != null)
            holder.addCallback(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        synchronized (balls) {
            for (Ball b : balls)
                canvas.drawCircle(b.getX(), b.getY(), b.getRadius(), b.getPaint());
        }
    }

    public void clearCanvas(Canvas canvas){
        canvas.drawColor(Color.WHITE); //clear
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {  }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Ball newBall = new Ball(motionEvent.getX(), motionEvent.getY(), 50, BallSurfaceView.this);
                synchronized (balls) {
                    balls.add(newBall);
                }
                newBall.startBall();

                System.out.println("New " + newBall);
                return false;
            }
        });


        //for any balls that are initiated on the start
        synchronized (balls) {
            for (Ball b : balls)
                b.startBall();
        }

        new RefreshThread(this).start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {  }

    public HashSet<Ball> getBalls() {
        return balls;
    }
}
