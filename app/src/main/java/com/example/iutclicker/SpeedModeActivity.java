package com.example.iutclicker;

import static java.lang.Math.round;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class SpeedModeActivity extends AppCompatActivity {
    private GameEngine game;
    private TextView highScore;
    private TextView score;
    private TextView punchlineView;

    private ImageButton clicker;
    private Button reset;
    private TextView timerView;

    private String[] punchlines;
    private final int nbPunchlines = 10;
    private Thread timer;

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
        this.punchlines = new String[this.nbPunchlines];
        fillLines();
    }

    private void startGame() {
        this.timer = new Thread(() -> {
            for (int i = 5; i>0; i--) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int finalI = i;
                runOnUiThread(() -> timerView.setText(String.valueOf(finalI)));
            }
            runOnUiThread(() -> {
                this.reset.setVisibility(Button.VISIBLE);
                this.clicker.setVisibility(Button.INVISIBLE);
            });
        });
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
}