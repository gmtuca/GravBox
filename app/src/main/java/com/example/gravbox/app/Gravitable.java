package com.example.gravbox.app;

/**
 * Created by gmtuk on 12/05/2014.
 */
public interface Gravitable {
    float getVelocityY();
    void setVelocityY(float v);

    double getTime();
    void setTime(double t);

    double getElasticity();

    int getFloor();

    float getY();
    void setY(float y);
}
