package com.example.iutclicker;

import static java.lang.Math.round;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.concurrent.locks.ReentrantLock;

public class GameEngine {
    SharedPreferences prefsHighScore;
    SharedPreferences.Editor prefsHighScoreEditor;
    private String gameMode;
    private int highScore;
    private int score;
    private int level;
    private ReentrantLock scoreLocker;


    public GameEngine(Activity theGame, String gameMode) {
        this.prefsHighScore = theGame.getSharedPreferences("highScores", Context.MODE_PRIVATE);
        this.prefsHighScoreEditor = this.prefsHighScore.edit();

        this.gameMode = gameMode;

        this.highScore = this.prefsHighScore.getInt(gameMode,0);
        this.score=0;

        this.scoreLocker = new ReentrantLock();

        if (this.gameMode.equals("Classic")) {
                this.level = this.prefsHighScore.getInt("level", 1);
        }
    }

    public int getHighScore() {
        return this.highScore;
    }

    public int getScore() {
        return this.score;
    }

    public int getLevel() {
        return this.level;
    }

    public boolean increaseScore(int scoreGained) {
        this.scoreLocker.lock();
        this.score += scoreGained;
        this.scoreLocker.unlock();
        if(this.score > this.highScore){
            this.highScore = this.score;
            this.prefsHighScoreEditor.putInt(this.gameMode, this.score);
            this.prefsHighScoreEditor.commit();
            return true;
        }
        return false;
    }

    public void decreaseScore(int scoreLost) {
        if (this.score>0) {
            this.scoreLocker.lock();
            this.score -= scoreLost;
            this.scoreLocker.unlock();
        }
    }

    public void increaseLevel() {
        this.level += 1;
        this.prefsHighScoreEditor.putInt("level", this.level);
        this.prefsHighScoreEditor.commit();
    }
}
