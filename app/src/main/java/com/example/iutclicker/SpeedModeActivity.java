package com.example.iutclicker;

import static java.lang.Math.round;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

public class SpeedModeActivity extends AppCompatActivity {
    private GameEngine game;
    private TextView highScore;
    private TextView score;
    private TextView punchlineView;

    private ImageButton clicker;
    private Button reset;
    private TextView timerView;

    private String[] punchlines;
    private static final int NB_PUNCHLINES = 23;

    private Thread timer;

    private boolean gameStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speed_mode);

        this.game = new GameEngine(this, "Speed");

        this.highScore = findViewById(R.id.speedHighScoreView);
        this.score = findViewById(R.id.speedScoreView);

        this.highScore.setText(String.valueOf(this.game.getHighScore()));
        this.score.setText(String.valueOf(this.game.getScore()));

        this.clicker = findViewById(R.id.jeanButton);
        this.reset = findViewById(R.id.resetSpeedButton);
        this.timerView = findViewById(R.id.timerView);

        this.reset.setVisibility(Button.INVISIBLE);

        this.punchlineView = findViewById(R.id.jeanPunchlineView);
        this.punchlines = new String[NB_PUNCHLINES];
        fillLines();

        this.gameStarted = false;
    }

    private void fillLines() {
        this.punchlines[0] = getString(R.string.jean0);
        this.punchlines[1] = getString(R.string.jean1);
        this.punchlines[2] = getString(R.string.jean2);
        this.punchlines[3] = getString(R.string.jean3);
        this.punchlines[4] = getString(R.string.jean4);
        this.punchlines[5] = getString(R.string.jean5);
        this.punchlines[6] = getString(R.string.jean6);
        this.punchlines[7] = getString(R.string.jean7);
        this.punchlines[8] = getString(R.string.jean8);
        this.punchlines[9] = getString(R.string.jean9);
        this.punchlines[10] = getString(R.string.jean10);
        this.punchlines[11] = getString(R.string.jean11);
        this.punchlines[12] = getString(R.string.jean12);
        this.punchlines[13] = getString(R.string.jean13);
        this.punchlines[14] = getString(R.string.jean14);
        this.punchlines[15] = getString(R.string.jean15);
        this.punchlines[16] = getString(R.string.jean16);
        this.punchlines[17] = getString(R.string.jean17);
        this.punchlines[18] = getString(R.string.jean18);
        this.punchlines[19] = getString(R.string.jean19);
        this.punchlines[20] = getString(R.string.jean20);
        this.punchlines[21] = getString(R.string.jean21);
        this.punchlines[22] = getString(R.string.jean22);
    }

    public void jeanClicked(View view) {
        if (this.gameStarted) {
            if (this.game.increaseScore(1)) {
                this.highScore.setText(String.valueOf(this.game.getHighScore()));
            }
            this.score.setText(String.valueOf(this.game.getScore()));
            this.punchlineView.setText(getRandomLine());
        } else {
            startGame();
            timer.start();
        }
    }

    private void startGame() {
        this.gameStarted = true;
        this.timer = new Thread(() -> {
            for (int i = 0; i<5; i++) {
                int finalI = 5-i;
                runOnUiThread(() -> this.timerView.setText(String.valueOf(finalI)));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            runOnUiThread(() -> {
                this.clicker.setVisibility(Button.INVISIBLE);
                this.reset.setVisibility(Button.VISIBLE);
                this.timerView.setText(String.valueOf(0));
                this.gameStarted = false;
            });
        });
    }

    public void resetGame(View view) {
        game.decreaseScore(game.getScore());
        this.score.setText(String.valueOf(this.game.getScore()));
        this.reset.setVisibility(Button.INVISIBLE);
        this.clicker.setVisibility(Button.VISIBLE);
    }

    private String getRandomLine() {
        Random rand = new Random();
        return this.punchlines[rand.nextInt(this.punchlines.length)];
    }

    public void exit(View view) {
        this.finish();
    }
}