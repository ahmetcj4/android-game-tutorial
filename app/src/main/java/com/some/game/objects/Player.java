package com.some.game.objects;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.some.game.R;

public class Player extends CollideGameObject {
    private boolean boosting;
    private final int GRAVITY = -10;
    private final int MIN_SPEED = 1;
    private final int MAX_SPEED = 50;

    public Player(Context context) {
        super(BitmapFactory.decodeResource(context.getResources(), R.drawable.player));
        boosting = false;
    }

    @Override
    public void setupPlacementAndSpeed() {
        setX(75);
        setY(50);
        setSpeed(1);
    }

    public void setBoosting() {
        boosting = true;
    }

    public void stopBoosting() {
        boosting = false;
    }

    @Override
    public void update(int playerSpeed) {
        int oldSpeed = getSpeed();
        setSpeed(Math.min(Math.max(boosting ? oldSpeed + 2 : oldSpeed - 5, MIN_SPEED),MAX_SPEED));
        setY(Math.min(Math.max(getY() - (getSpeed() + GRAVITY), getMinY()),getMaxY()));
        super.update(playerSpeed);
    }
}
