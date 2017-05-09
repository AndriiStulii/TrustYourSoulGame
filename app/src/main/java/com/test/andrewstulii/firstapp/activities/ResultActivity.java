package com.test.andrewstulii.firstapp.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.TextView;

import com.test.andrewstulii.firstapp.R;
import com.test.andrewstulii.firstapp.top.PlayersService;
import com.test.andrewstulii.firstapp.top.Player;

/**
 * Created by andrewstulii.
 */
public class ResultActivity extends AppCompatActivity {

    private SharedPreferences pointsPref;
    private SharedPreferences mistakesPref;
    private int currentUserPoints;
    private int currentUserMistakes;
    private int maxMistakes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        pointsPref = this.getSharedPreferences(
                getString(R.string.current_points_key), Context.MODE_PRIVATE);

        mistakesPref = this.getSharedPreferences(
                getString(R.string.current_mistakes_key), Context.MODE_PRIVATE);

        maxMistakes = getResources().getInteger(R.integer.max_mistakes);

        setResultView();
        setPointsView();
        setMistakesView();

        updatePointsCounter();
        updateMistakesCounter();

        if (currentUserMistakes == maxMistakes) {
            savePlayerResult();
            finishGame();
        } else {
            startNextLevel();
        }

    }

    private void startNextLevel() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(ResultActivity.this, LevelActivity.class);
                startActivity(i);
                finish();
            }
        }, getResources().getInteger(R.integer.standart_delay));
    }

    private void finishGame() {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(ResultActivity.this, DisplayMessageActivity.class);
                i.putExtra(getString(R.string.message_key), getString(R.string.game_over));
                startActivity(i);
                finish();
            }
        });
    }

    private void savePlayerResult() {
        PlayersService playersService = new PlayersService(this);
        SharedPreferences userNamePref = this.getSharedPreferences(
                getString(R.string.user_name_key), Context.MODE_PRIVATE);
        Player player = new Player(userNamePref.getString(getString(R.string.user_name_key), ""), currentUserPoints);
        playersService.writePlayerResults(player);
    }

    private void setResultView() {
        currentUserPoints = pointsPref.getInt(getString(R.string.current_points_key), 0);
        currentUserMistakes = mistakesPref.getInt(getString(R.string.current_mistakes_key), 0);

        Intent intent = getIntent();
        boolean result = intent.getBooleanExtra(getString(R.string.current_result_key), true);
        String text;
        if (result) {
            text = getString(R.string.success_message);
            currentUserPoints++;
        } else {
            text = getString(R.string.fail_message);
            currentUserMistakes++;
        }
        TextView textViewResult = (TextView) findViewById(R.id.textViewResult);
        textViewResult.setTextSize(40);
        textViewResult.setTextColor(ContextCompat.getColor(this, R.color.textColor));
        textViewResult.setText(text);
        textViewResult.setGravity(Gravity.CENTER_HORIZONTAL);
    }

    private void setPointsView() {
        TextView textViewPoints = (TextView) findViewById(R.id.textViewPoints);
        textViewPoints.setTextSize(30);
        textViewPoints.setTextColor(ContextCompat.getColor(this, R.color.textColor));
        textViewPoints.setGravity(Gravity.CENTER_HORIZONTAL);
        textViewPoints.setText(getString(R.string.amount_of_points, currentUserPoints));
    }

    private void setMistakesView() {
        TextView textViewMistakes = (TextView) findViewById(R.id.textViewMistakes);
        textViewMistakes.setTextSize(10);
        textViewMistakes.setTextColor(ContextCompat.getColor(this, R.color.textColor));
        textViewMistakes.setGravity(Gravity.CENTER_HORIZONTAL);
        textViewMistakes.setText(getString(R.string.amount_of_mistakes, maxMistakes - currentUserMistakes));
    }

    private void updatePointsCounter() {
        SharedPreferences.Editor editor = pointsPref.edit();
        editor.putInt(getString(R.string.current_points_key), currentUserPoints);
        editor.apply();
    }

    private void updateMistakesCounter() {
        SharedPreferences.Editor editor = mistakesPref.edit();
        editor.putInt(getString(R.string.current_mistakes_key), currentUserMistakes);
        editor.apply();
    }



}
