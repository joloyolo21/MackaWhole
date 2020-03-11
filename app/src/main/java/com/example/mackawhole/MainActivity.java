package com.example.mackawhole;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.TestLooperManager;
import android.service.dreams.DreamService;
import android.view.View;
import androidx.gridlayout.widget.GridLayout;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.Random;


public class MainActivity extends AppCompatActivity {

    private GridLayout grid;
    private Drawable moleImage;
    private Drawable molePrime;
    private ImageView[] imageViews;
    private Random rand;
    private int moleLocation;
    private boolean on;
    private Handler handler;
    private int count;
    private UpdateCount update;
    public TextView countView;
    private TextView points;
    private int p;
    private Button molechange;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        grid = findViewById(R.id.gridLayout);
        moleImage = getDrawable(R.drawable.mole);
        molePrime = getDrawable(R.drawable.moleprime);
        countView = findViewById(R.id.countView);
        molechange = findViewById(R.id.molechange);
        imageViews = new ImageView[16];
        rand = new Random();
        p = 0;
        handler = new Handler();
        update = new UpdateCount();
        points = findViewById(R.id.points);
        moleLocation = rand.nextInt(16);
        for (int i = 0; i < 16; i++) {
            imageViews[i] = (ImageView) getLayoutInflater().inflate(R.layout.mole_view, null);
            imageViews[i].setMinimumHeight(270);
            imageViews[i].setMinimumWidth(270);
            if (i == moleLocation) imageViews[i].setImageDrawable(moleImage);
            grid.addView(imageViews[i]);
        }
    }

    public void imagePress(View v) {
        if(v==imageViews[moleLocation]) {
            p+=10;
            points.setText(p+"");
        }
    }

    public void buttonPressed(View v) {
        if (on) {
            on = false;
            handler.removeCallbacks(update);

        } else {
            on = true;
            handler.postDelayed(update, 1000);
        }
    }


        private class UpdateCount implements Runnable {

            public void run() {
                count++;
                countView.setText(count + "");
                imageViews[moleLocation].setImageDrawable(null);
                moleLocation = rand.nextInt(16);
                imageViews[moleLocation].setImageDrawable(moleImage);
                handler.postDelayed(update, 1000);
            }
        }
    }



