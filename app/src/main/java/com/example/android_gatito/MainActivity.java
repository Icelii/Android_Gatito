package com.example.android_gatito;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.os.CountDownTimer;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProgressBar progbar = findViewById(R.id.Loading);

        long msFuture = 5000;
        progbar.setMax((int)msFuture);

        Intent iniciar= new Intent(this, MainActivity2.class);

        new CountDownTimer(msFuture, 1000)
        {
            public void onTick(long msUntilFinished)
            {
                progbar.setProgress((int)msUntilFinished);
            }
            public void onFinish() {
                startActivity(iniciar);
            }
        }.start();
    }
}