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

    public void goClassic(View view) {
        Intent activity = new Intent(this, ClassicModeActivity.class);
        startActivity(activity);
    }

    public void goInfernal(View view) {
        Intent activity = new Intent(this, InfernalModeActivity.class);
        startActivity(activity);
    }
}