package com.some.game.objects;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.some.game.R;

public class Boom extends CollideGameObject {

    public Boom(Context context) {
        super(BitmapFactory.decodeResource
                (context.getResources(), R.drawable.boom));
    }

    @Override
    public void setupPlacementAndSpeed() {
        setX(-500);
        setY(-500);
    }

    public void clearCollide() {
        setupPlacementAndSpeed();
    }

    public void setCollideAt(int x, int y) {
        setX(x);
        setY(y);
    }
}
