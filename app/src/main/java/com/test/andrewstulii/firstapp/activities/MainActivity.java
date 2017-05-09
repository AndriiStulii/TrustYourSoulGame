package com.test.andrewstulii.firstapp.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.test.andrewstulii.firstapp.R;

/**
 * Created by andrewstulii.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        Animation animOne = AnimationUtils.loadAnimation(this, R.anim.logo_anim);
        imageView.startAnimation(animOne);

        clearPreferences();
    }

    public void startNewGame(View view) {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(MainActivity.this, EnterYourNameActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    public void showTopUsers(View view) {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(MainActivity.this, TopPlayersActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    public void endGame(View view) {
        finish();
    }

    private void clearPreferences(){
        SharedPreferences levelCounter = this.getSharedPreferences(
                getString(R.string.level_counter_key), Context.MODE_PRIVATE);
        levelCounter.edit().clear().apply();

        SharedPreferences pointsCounter = this.getSharedPreferences(
                getString(R.string.current_points_key), Context.MODE_PRIVATE);
        pointsCounter.edit().clear().apply();

        SharedPreferences mistakesCounter = this.getSharedPreferences(
                getString(R.string.current_mistakes_key), Context.MODE_PRIVATE);
        mistakesCounter.edit().clear().apply();

        SharedPreferences userName = this.getSharedPreferences(
                getString(R.string.user_name_key), Context.MODE_PRIVATE);
        userName.edit().clear().apply();
    }
}
