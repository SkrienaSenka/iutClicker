package com.example.iutclicker;

import static java.lang.Math.round;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class InfernalModeActivity extends AppCompatActivity {
    private GameEngine game;
    private TextView highScore;
    private TextView score;
    private TextView punchlineView;
    private String[] punchlines;
    private final int nbPunchlines = 10;
    private Thread decrease;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infernal_mode);

        this.game = new GameEngine(this, "Infernal");

        this.highScore = findViewById(R.id.infernalHighScoreView);
        this.score = findViewById(R.id.infernalScoreView);

        this.highScore.setText(String.valueOf(this.game.getHighScore()));
        this.score.setText(String.valueOf(this.game.getScore()));

        this.punchlineView = findViewById(R.id.objoisPunchlineView);

        this.punchlines = new String[this.nbPunchlines];
        fillLines();

        this.decrease = new Thread(() -> {
            while(true) {
                try {
                    Thread.sleep(round(1000 / (0.08 * game.getScore() + 1)));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                game.decreaseScore(1);
                runOnUiThread(() -> score.setText(String.valueOf(game.getScore())));
            }
        });
        this.decrease.start();
    }

    private void fillLines() {
        this.punchlines[0] = this.getString(R.string.later);
        this.punchlines[1] = this.getString(R.string.doesPing);
        this.punchlines[2] = this.getString(R.string.frames);
        this.punchlines[3] = this.getString(R.string.youTalkGood);
        this.punchlines[4] = this.getString(R.string.easyForYou);
        this.punchlines[5] = this.getString(R.string.dontLeave);
        this.punchlines[6] = this.getString(R.string.noProblem);
        this.punchlines[7] = this.getString(R.string.monstrous);
        this.punchlines[8] = this.getString(R.string.yourTooBad);
        this.punchlines[9] = this.getString(R.string.simple);
    }

    public void objoisClicked(View view) {
        if (this.game.increaseScore(1)) {
            this.highScore.setText(String.valueOf(this.game.getHighScore()));
        }
        this.score.setText(String.valueOf(this.game.getScore()));
        this.punchlineView.setText(getRandomLine());
    }

    private String getRandomLine() {
        Random rand = new Random();
        return this.punchlines[rand.nextInt(this.nbPunchlines)];
    }

    public void exit(View view) {
        this.finish();
    }
}