package com.example.iutclicker;

import static java.lang.Math.round;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ClassicModeActivity extends AppCompatActivity {
    private GameEngine game;
    private TextView highScore;
    private TextView score;
    private TextView level;
    private TextView cost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classic_mode);

        this.game = new GameEngine(this, "Classic");

        this.highScore=findViewById(R.id.classicHighScoreView);
        this.score=findViewById(R.id.classicScoreView);
        this.level=findViewById(R.id.levelView);
        this.cost=findViewById(R.id.costView);

        this.highScore.setText(String.valueOf(this.game.getHighScore()));
        this.score.setText(String.valueOf(this.game.getScore()));
        this.level.setText(String.valueOf(this.game.getLevel()));
        this.cost.setText(String.valueOf(round(this.game.getLevel()*0.8*this.game.getLevel())));
    }

    // TODO Set onClick listener for the buttons
    public void blandineClicked(View view) {
        if (this.game.increaseScore((int) round(this.game.getLevel()*0.2*this.game.getLevel()) + 1)) {
            this.highScore.setText(String.valueOf(this.game.getHighScore()));
        }
        this.score.setText(String.valueOf(this.game.getScore()));
    }

    public void mercierClicked(View view) {
        if (this.game.getScore()>=round((this.game.getLevel()*0.8*this.game.getLevel()))) {
            this.game.decreaseScore((int) round(this.game.getLevel()*0.8*this.game.getLevel()));
            this.game.increaseLevel();
            this.level.setText(String.valueOf(this.game.getLevel()));
            this.cost.setText(String.valueOf(round(this.game.getLevel()*0.8*this.game.getLevel())));
            this.score.setText(String.valueOf(this.game.getScore()));
        }
    }

    public void exit(View view) {
        this.finish();
    }
}