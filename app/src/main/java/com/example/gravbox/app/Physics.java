package com.example.gravbox.app;

/**
 * Created by gmtuk on 12/05/2014.
 */
public class Physics {
    public static final double GRAVITY = 9.8;
    private static final double GRAVITY_ADJUSTMENT = 0.01;

    public static boolean PAUSED = false;

    public static void applyGravity(Gravitable gravitable){
        if(PAUSED)
            return;

        double t = gravitable.getTime();
        double y = gravitable.getY();
        double vy = gravitable.getVy();
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
                vy = (-vy * e);

            t = 1;
        }

        gravitable.setTime(t);
        gravitable.setY(y);
        gravitable.setVy(vy);
    }

    public static boolean containsPoint(Circular c, double x, double y){
        return distance(x, y, c.getX(), c.getY()) < c.getRadius();
    }

    public static boolean containsPoint(Quadrilateral q, double x, double y){
        return x > q.getX() - q.getWidth()/2 && x < q.getX() + q.getWidth()/2 &&
                y > q.getY() - q.getHeight()/2 && y < q.getY() + q.getHeight()/2;
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

        final double x = ((c1.getX() * c2.getRadius()) + (c2.getX() * c1.getRadius())) / (c1.getRadius() + c2.getRadius());
        final double y = ((c1.getY() * c2.getRadius()) + (c2.getY() * c1.getRadius())) / (c1.getRadius() + c2.getRadius());

        return new Point() {
            @Override
            public double getX() {
                return x;
            }

            @Override
            public double getY() {
                return y;
            }
        };
    }

    public static double distance(double x1, double y1, double x2, double y2){
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    public static double distance(Point p1, Point p2){
        return distance(p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }

}
