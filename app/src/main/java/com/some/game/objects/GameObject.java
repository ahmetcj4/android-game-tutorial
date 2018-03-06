package com.some.game.objects;

import com.some.game.managers.ScreenManager;

public abstract class GameObject {
    private int x;
    private int y;
    private int speed;

    private int maxX;
    private int maxY;
    private int minX;
    private int minY;

    GameObject() {
        maxX = ScreenManager.x();
        maxY = ScreenManager.y();
        minX = 0;
        minY = 0;
        setupPlacementAndSpeed();
    }

    public abstract void update(int playerSpeed);
    public abstract void setupPlacementAndSpeed();

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSpeed() {
        return speed;
    }

    int getMaxX() {
        return maxX;
    }

    int getMaxY() {
        return maxY;
    }

    public void setMaxY(int maxY) {
        this.maxY = maxY;
    }

    public int getMinX() {
        return minX;
    }

    public int getMinY() {
        return minY;
    }
}
