package com.example.gravbox.app;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by gmtuk on 12/05/2014.
 */
public class RefreshThread extends Thread {

    private final SurfaceHolder surfaceHolder;
    private BallSurfaceView ballSurfaceView;

    public RefreshThread(BallSurfaceView ballSurfaceView){
        this.surfaceHolder = ballSurfaceView.getHolder();
        this.ballSurfaceView = ballSurfaceView;
    }

    @Override
    public void run() {
        super.run();

        Canvas canvas = null;

        while (true) {

            synchronized (ballSurfaceView.getBalls()) {
                for (Ball b1 : ballSurfaceView.getBalls())
                    for (Ball b2 : ballSurfaceView.getBalls())
                        if (b1 != b2 && Physics.collide(b1, b2)) {
                            System.out.println("Balls collide!");

                            //double newvx = (b1.getVelocityX() * (m1 – m2) + (2 * m2 * b2.getVelocityX()))
                            /// (m1 + m2);;

                            int m1 = b1.getMass();
                            int m2 = b2.getMass();

                            float vx1 = b1.getVelocityX();
                            float vx2 = b2.getVelocityX();

                            float vy1 = b1.getVelocityY();
                            float vy2 = b2.getVelocityY();

                            vx1 = (float) ((vx1 * (m1 - m2) + (2 * m2 * vx2)) / (double) (m1 + m2));
                            vy1 = (float) ((vy1 * (m1 - m2) + (2 * m2 * vy2)) / (double) (m1 + m2));
                            vx2 = (float) ((vx2 * (m2 - m1) + (2 * m1 * vx1)) / (double) (m1 + m2));
                            vy2 = (float) ((vy2 * (m2 - m1) + (2 * m1 * vy1)) / (double) (m1 + m2));


                            double theta = Physics.collisionAngle(b1, b2);
                            float lenth_in = b1.getRadius() + b2.getRadius() - Physics.distance(b1, b2);

                            double dy = lenth_in * Math.sin(theta);
                            double dx = dy / Math.tan(theta);

                            b1.setVelocityX(vx1); //it's always zero TODO
                            b2.setVelocityX(vx2); //same here.....

                            b1.setVelocityY(vy1);
                            b2.setVelocityY(vy2);

                            b1.moveXYby(-dx/1.5 , -dy/1.5);
                            b2.moveXYby(dx/1.5, dy/1.5);


                        }
                }

                try {
                    canvas = surfaceHolder.lockCanvas(null);

                    synchronized (surfaceHolder) {
                        if(canvas != null){
                            ballSurfaceView.clearCanvas(canvas);
                            ballSurfaceView.onDraw(canvas);
                        }
                    }
                }
                finally {
                    if (canvas != null)
                        surfaceHolder.unlockCanvasAndPost(canvas);

                try {
                    Thread.sleep(25);
                }
                catch (InterruptedException e) {
                    System.err.println("Clearing has been interrupted");
                }
            }
        }
    }
}
