package com.example.gravbox.app;

import android.graphics.Paint;

/**
 * Created by gmtuk on 24/05/2014.
 */
public interface Shape extends Point{
    Paint getPaint();
}

interface Quadrilateral extends Shape{
    int getHeight();
    int getWidth();
}

interface Circular extends Shape{
    int getRadius();
}