package com.some.game.managers;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created on 3/6/2018
 *
 * @author ahmet
 */

public class SharedPreferencesManager {
    private static final String SHAR_PREF_NAME = "SPM";
    private static SharedPreferences sharedPreferences;

    public static void init(Context context) {
        sharedPreferences = context.getSharedPreferences(SHAR_PREF_NAME, Context.MODE_PRIVATE);
    }

    /**
     * @return high 4 score
     */
    public static int[] getHighScores() {
        int highScore[] = new int[4];
        highScore[0] = sharedPreferences.getInt("score1", 0);
        highScore[1] = sharedPreferences.getInt("score2", 0);
        highScore[2] = sharedPreferences.getInt("score3", 0);
        highScore[3] = sharedPreferences.getInt("score4", 0);
        return highScore;
    }

    /**
     * saves score to high score list if it is high score
     * @param score last score
     */
    public static void saveScore(int score) {
        int highScore[] = getHighScores();
        for (int i = 0; i < 4; i++) {
            if (highScore[i] < score) {
                highScore[i] = score;
                break;
            }
        }
        SharedPreferences.Editor e = sharedPreferences.edit();
        for (int i = 0; i < 4; i++) {
            int j = i + 1;
            e.putInt("score" + j, highScore[i]);
        }
        e.apply();
    }
}
