package com.example.gravbox.app;

/**
 * Created by gmtuk on 23/05/2014.
 */
public final class Box {
    private final int TOP, BOTTOM, LEFT, RIGHT;

    public Box(int UP, int DOWN, int LEFT, int RIGHT) {
        this.TOP = UP;
        this.BOTTOM = DOWN;
        this.LEFT = LEFT;
        this.RIGHT = RIGHT;
    }

    public int getTOP() {
        return TOP;
    }
    public int getBOTTOM() {
        return BOTTOM;
    }
    public int getLEFT() {
        return LEFT;
    }
    public int getRIGHT() {
        return RIGHT;
    }

    @Override
    public String toString() {
        return "Box{" +
                "TOP=" + TOP +
                ", BOTTOM=" + BOTTOM +
                ", LEFT=" + LEFT +
                ", RIGHT=" + RIGHT +
                '}';
    }
}
