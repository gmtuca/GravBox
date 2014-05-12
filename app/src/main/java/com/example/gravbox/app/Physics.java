package com.example.gravbox.app;

/**
 * Created by gmtuk on 12/05/2014.
 */
public class Physics {
    public static final double GRAVITY = 9.8;
    private static final double GRAVITY_ADJUSTMENT = 0.05;

    public static void applyGravity(Gravitable gravitable){
        double t = gravitable.getTime();
        float y = gravitable.getY();
        double v = gravitable.getVelocity();
        double r = gravitable.getResistance();
        int f = gravitable.getFloor();
        double e = gravitable.getElasticity();

        t += 0.01;

        // y = y + vt + 1/2 acc * t^2
        y += GRAVITY_ADJUSTMENT * (v * t + 0.5 * GRAVITY * t * t);

        // v = v0 + acc * t
        v += (GRAVITY * t) * r;


        if(y >= f){
            y = f;

            if(v > 0)
                v = -v;

            t = 1;
            r /= e;
        }


        gravitable.setTime(t);
        gravitable.setY(y);
        gravitable.setVelocity(v);
        gravitable.setResistance(r);
    }

}
