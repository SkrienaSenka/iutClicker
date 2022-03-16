package com.example.iutclicker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void switchMode(View view) {
        Intent activity;
        switch (view.getId()) {
            case R.id.swapInfernalMode:
                activity = new Intent(this, InfernalModeActivity.class);
                break;
            case R.id.swapSpeedMode:
                activity = new Intent(this, SpeedModeActivity.class);
                break;
            default:
                activity = new Intent(this, ClassicModeActivity.class);
                break;
        }
        startActivity(activity);
    }
}