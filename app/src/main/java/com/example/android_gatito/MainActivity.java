package com.example.android_gatito;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button B;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        B = (Button) findViewById(R.id.btn1);
        B.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        Intent i= new Intent(this, MainActivity2.class);
        startActivity(i);
    }
}