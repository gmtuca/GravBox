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
        float vy = gravitable.getVelocityY();
        int f = gravitable.getFloor();
        double e = gravitable.getElasticity();

        t += 0.01;

        // y = y + vt + 1/2 acc * t^2
        y += GRAVITY_ADJUSTMENT * (vy * t + 0.5 * GRAVITY * t * t);

        // v = v0 + acc * t
        vy += (GRAVITY * t);

        if(y >= f){
            y = f;

            if(vy > 0)
                vy = (float)(-vy * e);

            t = 1;
        }

        gravitable.setTime(t);
        gravitable.setY(y);
        gravitable.setVelocityY(vy);
    }

    public static boolean collide(Circular c1, Circular c2){
        return distance(c1, c2) < c1.getRadius() + c2.getRadius();
    }

    public static double collisionAngle(Circular c1, Circular c2){
        if(!collide(c1, c2))
            return Double.POSITIVE_INFINITY;

        double adjecent = Math.abs(c1.getX() - c2.getX());
        double opposite = Math.abs(c1.getY() - c2.getY());

        return Math.atan(opposite / adjecent);
    }

    public static Point collisionPoint(Circular c1, Circular c2){
        if(!collide(c1,c2))
            return null;

        final float x = ((c1.getX() * c2.getRadius()) + (c2.getX() * c1.getRadius())) / (c1.getRadius() + c2.getRadius());
        final float y = ((c1.getY() * c2.getRadius()) + (c2.getY() * c1.getRadius())) / (c1.getRadius() + c2.getRadius());

        return new Point() {
            @Override
            public float getX() {
                return x;
            }

            @Override
            public float getY() {
                return y;
            }
        };
    }

    public static float distance(Point p1, Point p2){
        return (float)Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2));
    }

}
