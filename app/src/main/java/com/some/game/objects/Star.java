package com.some.game.objects;

import java.util.Random;

public class Star extends GameObject {
    @Override
    public void update(int playerSpeed) {
        setX(getX() - playerSpeed - getSpeed());
        if (getX() < 0) {
            setupPlacementAndSpeed();
        }
    }

    @Override
    public void setupPlacementAndSpeed() {
        Random generator = new Random();
        setX(getX() < 0 ? getMaxX() : generator.nextInt(getMaxX()));
        setY(generator.nextInt(getMaxY()));
        setSpeed(generator.nextInt(15));
    }

    public float getStarWidth() {
        float minX = 1.0f;
        float maxX = 4.0f;
        Random rand = new Random();
        return rand.nextFloat() * (maxX - minX) + minX;
    }

}
