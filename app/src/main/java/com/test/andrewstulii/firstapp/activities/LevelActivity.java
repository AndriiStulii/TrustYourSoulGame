package com.test.andrewstulii.firstapp.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.test.andrewstulii.firstapp.R;
import com.test.andrewstulii.firstapp.facts.Fact;
import com.test.andrewstulii.firstapp.facts.FactsService;

/**
 * Created by andrewstulii.
 */
public class LevelActivity extends AppCompatActivity {

    private SharedPreferences sharedPref;
    private int levelCounter;
    private Fact currentFact;
    private FactsService factsService = new FactsService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPref = this.getSharedPreferences(
                getString(R.string.level_counter_key), Context.MODE_PRIVATE);

        levelCounter = sharedPref.getInt(getString(R.string.level_counter_key), 1);

        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));
        ll.setGravity(Gravity.CENTER);
        ll.addView(createLevelTextView());
        ll.addView(new TextView(this));
        ll.addView(createFactTextView());
        ll.addView(new TextView(this));
        ll.addView(new TextView(this));
        ll.addView(createTrueButton());
        ll.addView(createFalseButton());
        ll.setBackgroundDrawable(getResources().getDrawable(R.drawable.background));
        setContentView(ll);

        updateLevelCounter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    private void updateLevelCounter() {
        levelCounter++;
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(getString(R.string.level_counter_key), levelCounter);
        editor.apply();
    }

    private TextView createLevelTextView(){
        TextView textView = new TextView(this);
        textView.setTextSize(30);
        textView.setTextColor(ContextCompat.getColor(this, R.color.textColor));
        textView.setGravity(Gravity.TOP);
        textView.setGravity(Gravity.CENTER_HORIZONTAL);
        textView.setText(getResources().getString(R.string.level, levelCounter));
        return textView;
    }

    private TextView createFactTextView(){
        TextView textView = new TextView(this);
        currentFact = factsService.getRandomFact();
        textView.setTextSize(20);
        textView.setTextColor(ContextCompat.getColor(this, R.color.textColor));
        String text = getResources().getConfiguration().locale.getCountry().equals("RU") ?
                currentFact.getFact_ru() : currentFact.getFact_en();
        textView.setText(text);
        return textView;
    }

    private Button createTrueButton(){
        Button button = new Button(this);
        button.setBackgroundColor(Color.GREEN);
        button.setText(getString(R.string.button_true));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick(currentFact.isFact_truth());
            }
        });
        return button;
    }

    private Button createFalseButton(){
        Button button = new Button(this);
        button.setBackgroundColor(Color.RED);
        button.setText(getString(R.string.button_false));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick(!currentFact.isFact_truth());
            }
        });
        return button;
    }

    private void onButtonClick(boolean result){
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra(getString(R.string.current_result_key), result);
        startActivity(intent);
        finish();
    }
}
