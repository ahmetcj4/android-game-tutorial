package com.some.game.objects;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.some.game.R;

import java.util.Random;

public class Friend extends CollideGameObject {

    public Friend(Context context) {
        super(BitmapFactory.decodeResource(context.getResources(), R.drawable.friend));
    }

    public void update(int playerSpeed) {
        setX(getX() - playerSpeed - getSpeed());
        if (getX() < getMinX() - getBitmap().getWidth()) {
            setupPlacementAndSpeed();
        }
        super.update(playerSpeed);
    }

    @Override
    public void setupPlacementAndSpeed() {
        Random generator = new Random();
        setSpeed(generator.nextInt(10) + 10);
        setX(getMaxX());
        setY(generator.nextInt(getMaxY()));
    }
}
