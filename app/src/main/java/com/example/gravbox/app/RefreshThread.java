package com.example.gravbox.app;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by gmtuk on 12/05/2014.
 */
public class RefreshThread extends Thread {

    private final SurfaceHolder surfaceHolder;
    private GravBoxSurfaceView gravBoxSurfaceView;

    public RefreshThread(GravBoxSurfaceView gravBoxSurfaceView){
        this.surfaceHolder = gravBoxSurfaceView.getHolder();
        this.gravBoxSurfaceView = gravBoxSurfaceView;
    }

    @Override
    public void run() {
        super.run();

        Canvas canvas = null;

        while (true) {

//            synchronized (gravBoxSurfaceView.getBalls()) {
//                for (DynamicBall b1 : gravBoxSurfaceView.getBalls())
//                    for (DynamicBall b2 : gravBoxSurfaceView.getBalls())
//                        if (b1 != b2 && Physics.collide(b1, b2)) {
//                            System.out.println("Balls collide!");
//
//                            //double newvx = (b1.getVx() * (m1 – m2) + (2 * m2 * b2.getVx()))
//                            /// (m1 + m2);;
//
//                            int m1 = b1.getMass();
//                            int m2 = b2.getMass();
//
//                            double vx1 = b1.getVx();
//                            double vx2 = b2.getVx();
//
//                            double vy1 = b1.getVy();
//                            double vy2 = b2.getVy();
//
//
//                            double theta = Physics.collisionAngle(b1, b2);
//                            double length_in = b1.getRadius() + b2.getRadius() - Physics.distance(b1, b2);
//
//                            double dy = length_in * Math.sin(theta);
//                            double dx = dy / Math.tan(theta);
//
//                            //THIS ALL SEEMS TO BE WRONG
//                            vx1 = (float) ((vx1 * (m1 - m2) + (2 * m2 * vx2)) / (double) (m1 + m2));
//                            vy1 = (float) ((vy1 * (m1 - m2) + (2 * m2 * vy2)) / (double) (m1 + m2));
//                            vx2 = (float) ((vx2 * (m2 - m1) + (2 * m1 * vx1)) / (double) (m1 + m2));
//                            vy2 = (float) ((vy2 * (m2 - m1) + (2 * m1 * vy1)) / (double) (m1 + m2));
//
//                            b1.setVx((float)-dx); //it's always zero TODO
//                            b2.setVx((float)dx); //same here.....
//
//                            b1.setVy(vy1);
//                            b2.setVy(vy2);
//
//                            b1.moveBy(-dx/1.5 , -dy/1.5);
//                            b2.moveBy(dx/1.5, dy/1.5);
//
//
//                        }
//                }

                try {
                    canvas = surfaceHolder.lockCanvas(null);

                    synchronized (surfaceHolder) {
                        if(canvas != null){
                            Drawer.clearCanvas(canvas);
                            gravBoxSurfaceView.onDraw(canvas);
                        }
                    }
                }
                finally {
                    if (canvas != null)
                        surfaceHolder.unlockCanvasAndPost(canvas);
                try {
                    Thread.sleep(5);
                }
                catch (InterruptedException e) {
                    System.err.println("Clearing has been interrupted");
                }
            }
        }
    }
}
