package com.some.game.managers;

import android.content.Context;
import android.media.MediaPlayer;

import com.some.game.R;

/**
 * GameMusicManager
 * Created on 3/6/2018
 *
 * @author ahmet
 */

public class GameMusicManager {
    private static GameMusicManager instance;
    private MediaPlayer gameOnSound;
    private MediaPlayer killedEnemySound;
    private MediaPlayer gameOverSound;

    public static void init(Context context) {
        instance = new GameMusicManager(context);
    }

    private GameMusicManager(Context context) {
        gameOnSound = MediaPlayer.create(context, R.raw.gameon);
        killedEnemySound = MediaPlayer.create(context, R.raw.killedenemy);
        gameOverSound = MediaPlayer.create(context, R.raw.gameover);
    }

    public static void enemyKilled() {
        instance.killedEnemySound.start();
    }
    public static void gameOn() {
        instance.gameOverSound.stop();
        instance.gameOnSound.start();
    }
    public static void gameOver() {
        instance.gameOnSound.stop();
        instance.gameOverSound.start();
    }

    public static void stopMusic() {
        instance.gameOnSound.stop();
        instance.killedEnemySound.stop();
        instance.gameOnSound.stop();
    }

    public static void releaseResources() {
        instance.gameOnSound.release();
        instance.killedEnemySound.release();
        instance.gameOnSound.release();
    }
}
