package com.some.game.objects;

import android.graphics.Bitmap;
import android.graphics.Rect;

import com.some.game.managers.ScreenManager;

/**
 * Created on 3/6/2018
 *
 * @author ahmet
 */

public abstract class CollideGameObject extends GameObject {
    private Rect mesh;
    private Bitmap bitmap;

    private boolean collided = false;
    private boolean missed = false;

    CollideGameObject(Bitmap bitmap) {
        super();
        this.bitmap = bitmap;
        setMaxY(ScreenManager.y() - this.bitmap.getHeight());
        mesh = new Rect(getX(), getY(), this.bitmap.getWidth(), this.bitmap.getHeight());
    }

    public Rect getMesh() {
        return mesh;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    @Override
    public void update(int playerSpeed) {
        mesh.left = getX();
        mesh.top = getY();
        mesh.right = getX() + bitmap.getWidth();
        mesh.bottom = getY() + bitmap.getHeight();

    }

    public boolean collides(CollideGameObject collideGameObject) {
        return Rect.intersects(mesh,collideGameObject.getMesh());
    }

    public boolean canCollide(CollideGameObject collideGameObject) {
     /*   Rect rect = collideGameObject.getMesh();
        return mesh.intersects(rect.left,0,rect.right,ScreenManager.y());*/
        return mesh.left < collideGameObject.mesh.right;
    }
    public void collided() {
        collided = true;
    }

    public boolean isCollided() {
        return collided;
    }

    public void missed() {
        missed = true;
    }

    public boolean isMissed() {
        return missed;
    }

    public void clearCollided() {
        this.collided = false;
    }

    public void clearMissed() {
        this.missed = false;
    }
}
