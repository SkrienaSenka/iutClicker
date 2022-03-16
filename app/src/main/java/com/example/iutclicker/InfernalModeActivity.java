package com.example.iutclicker;

import static java.lang.Math.round;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
    private static final int NB_PUNCHLINES = 11;
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
        this.punchlines = new String[NB_PUNCHLINES];
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
        this.punchlines[0] = getString(R.string.objois0);
        this.punchlines[1] = getString(R.string.objois1);
        this.punchlines[2] = getString(R.string.objois2);
        this.punchlines[3] = getString(R.string.objois3);
        this.punchlines[4] = getString(R.string.objois4);
        this.punchlines[5] = getString(R.string.objois5);
        this.punchlines[6] = getString(R.string.objois6);
        this.punchlines[7] = getString(R.string.objois7);
        this.punchlines[8] = getString(R.string.objois8);
        this.punchlines[9] = getString(R.string.objois9);
        this.punchlines[10] = getString(R.string.objois10);
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
        return this.punchlines[rand.nextInt(this.punchlines.length)];
    }

    public void shareHighScore(View view) {
        String subject = getString(R.string.subject);

        String content = getString(R.string.content) + " " + this.game.getHighScore() + " " +  getString(R.string.contentInfernal);

        Intent intentEmail = new Intent(Intent.ACTION_SEND);

        intentEmail.putExtra(Intent.EXTRA_SUBJECT, subject);
        intentEmail.putExtra(Intent.EXTRA_TEXT, content);

        intentEmail.setType("text/plain");

        startActivity(Intent.createChooser(intentEmail,getString(R.string.choose)));
    }

    public void exit(View view) {
        this.finish();
    }
}