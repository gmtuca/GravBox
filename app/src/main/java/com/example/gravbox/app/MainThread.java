package com.example.gravbox.app;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by gmtuk on 11/05/2014.
 */
public class MainThread extends Thread {

    private final SurfaceHolder surfaceHolder;
    private Ball ball;

    public MainThread(SurfaceHolder surfaceHolder, Ball ball){
        this.surfaceHolder = surfaceHolder;
        this.ball = ball;
    }

    @Override
    public void run(){
        super.run();

        Canvas canvas = null;

        while(true){
            try {
                canvas = surfaceHolder.lockCanvas(null);

                synchronized (surfaceHolder) {
                    ball.clearBall(canvas);
                    ball.move();
                    ball.onDraw(canvas);
                }
            }
            finally {
                if(canvas != null)
                    surfaceHolder.unlockCanvasAndPost(canvas);

                try {
                    Thread.sleep(1);
                }
                catch(InterruptedException e){
                    System.err.println("Ball has been interrupted");
                }
            }
        }
    }
}
