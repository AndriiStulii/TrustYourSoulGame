package com.test.andrewstulii.firstapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.andrewstulii.firstapp.R;

/**
 * Created by andrewstulii.
 */
public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_display_greeting);

        String message = getIntent().getStringExtra(getString(R.string.message_key));
        layout.addView(createGreetingLabel(message));

        if (message.startsWith(getString(R.string.greeting).substring(0,5))) {
            startGame();
        } else if (message.startsWith(getString(R.string.game_over))) {
            toMainMenu();
        }

    }

    private TextView createGreetingLabel(String message) {
        TextView textView = new TextView(this);
        textView.setTextSize(50);
        textView.setTextColor(ContextCompat.getColor(this, R.color.textColor));
        textView.setGravity(Gravity.CENTER_HORIZONTAL);

        textView.setText(message);
        return textView;
    }

    private void startGame(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(DisplayMessageActivity.this, LevelActivity.class);
                startActivity(i);
                finish();
            }
        }, getResources().getInteger(R.integer.standart_delay));
    }

    private void toMainMenu(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(DisplayMessageActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, getResources().getInteger(R.integer.standart_delay));
    }
}
