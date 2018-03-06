package com.some.game.ui.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceView;

import com.some.game.managers.GameMusicManager;
import com.some.game.managers.SharedPreferencesManager;
import com.some.game.objects.Boom;
import com.some.game.objects.CollideGameObject;
import com.some.game.objects.Enemy;
import com.some.game.objects.Friend;
import com.some.game.objects.Player;
import com.some.game.objects.Star;
import com.some.game.ui.MainActivity;

import java.util.ArrayList;

public class GameView extends SurfaceView implements Runnable {

    private volatile boolean playing;

    int score = 0;
    int countMisses = 0;
    private boolean isGameOver = false;
    private Thread gameThread = null;


    private Player player;
    private Enemy enemy;
    private Friend friend;
    private ArrayList<Star> stars = new ArrayList<>();
    private Boom boom;

    private Paint paint;

    public GameView(Context context) {
        super(context);
        paint = new Paint();

        player = new Player(context);
        int starNums = 100;
        for (int i = 0; i < starNums; i++) {
            Star s = new Star();
            stars.add(s);
        }

        enemy = new Enemy(context);
        boom = new Boom(context);
        friend = new Friend(context);
        GameMusicManager.gameOn();

    }


    @Override
    public void run() {
        while (playing) {
            update();
            draw();
            control();
        }
    }


    private void update() {
        score++;
        boom.clearCollide();
        player.update(0);

        for (Star s : stars) {
            s.update(player.getSpeed());
        }

        enemy.update(player.getSpeed());

        //if collision occurs with player
        if (!enemy.isCollided()) {
            if (player.collides(enemy)) {
                boom.setCollideAt(enemy.getX(), enemy.getY());
                enemy.collided();
                GameMusicManager.enemyKilled();
            } else if (!player.canCollide(enemy) && !enemy.isMissed()) { //miss
                enemy.missed();
                if (++countMisses == 3) {
                    endGame();
                }

            }
        }

        friend.update(player.getSpeed());
        if (player.collides(friend)) {
            boom.setCollideAt(friend.getX(), friend.getY());
            friend.collided();
            endGame();
        }
    }

    private void endGame() {
        playing = false;
        isGameOver = true;
        GameMusicManager.gameOver();
        SharedPreferencesManager.saveScore(score);
    }

    private void draw() {
        if (getHolder().getSurface().isValid()) {
            Canvas canvas = getHolder().lockCanvas();
            canvas.drawColor(Color.BLACK);


            paint.setColor(Color.WHITE);
            paint.setTextSize(30);
            canvas.drawText("Score:" + score + ", Miss: " + countMisses, 100, 50, paint);

            for (Star s : stars) {
                paint.setStrokeWidth(s.getStarWidth());
                canvas.drawPoint(s.getX(), s.getY(), paint);
            }

            drawCollideGameObject(canvas, paint, player);
            drawCollideGameObject(canvas, paint, !enemy.isCollided() ? enemy : boom);
            drawCollideGameObject(canvas, paint, !friend.isCollided() ? friend : boom);

            if (isGameOver) {
                paint.setTextSize(150);
                paint.setTextAlign(Paint.Align.CENTER);

                int yPos = (int) ((canvas.getHeight() / 2) - ((paint.descent() + paint.ascent()) / 2));
                canvas.drawText("Game Over", canvas.getWidth() / 2, yPos, paint);
            }

            getHolder().unlockCanvasAndPost(canvas);
        }
    }

    private void drawCollideGameObject(Canvas canvas, Paint paint, CollideGameObject go) {
        canvas.drawBitmap(go.getBitmap(), go.getX(), go.getY(), paint);
    }

    private void control() {
        try {
            Thread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void pause() {
        playing = false;
        try {
            gameThread.join();
        } catch (InterruptedException ignored) {
        }
    }

    public void resume() {
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_UP:
                player.stopBoosting();
                break;
            case MotionEvent.ACTION_DOWN:
                player.setBoosting();
                break;

        }
        if (isGameOver) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                GameMusicManager.stopMusic();
                getContext().startActivity(new Intent(getContext(), MainActivity.class));
            }

        }
        return true;
    }
}

