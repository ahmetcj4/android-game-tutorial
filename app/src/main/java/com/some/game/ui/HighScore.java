package com.some.game.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.some.game.R;
import com.some.game.managers.SharedPreferencesManager;

import java.text.MessageFormat;

public class HighScore extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        TextView textView = findViewById(R.id.textView);
        TextView textView2 = findViewById(R.id.textView2);
        TextView textView3 = findViewById(R.id.textView3);
        TextView textView4 = findViewById(R.id.textView4);

        int[] highScores = SharedPreferencesManager.getHighScores();
        textView.setText(MessageFormat.format("1.{0}", highScores[0]));
        textView2.setText(MessageFormat.format("2.{0}", highScores[1]));
        textView3.setText(MessageFormat.format("3.{0}", highScores[2]));
        textView4.setText(MessageFormat.format("4.{0}", highScores[3]));


    }
}
