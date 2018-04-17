package com.example.ayodejifabusuyi.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void playClick(View view) {
        Intent intent = new Intent(MainActivity.this, GameActivity.class);
        startActivity(intent);
    }

    public void exitClick(View view) {
        System.exit(0);
    }


}

