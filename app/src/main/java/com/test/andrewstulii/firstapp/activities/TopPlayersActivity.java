package com.test.andrewstulii.firstapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.test.andrewstulii.firstapp.R;
import com.test.andrewstulii.firstapp.top.PlayersService;
import com.test.andrewstulii.firstapp.top.Player;

/**
 * Created by andrewstulii.
 */
public class TopPlayersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));
        layout.setGravity(Gravity.CENTER);
        layout.addView(addTable());
        layout.addView(new TextView(this));
        layout.setBackgroundDrawable(getResources().getDrawable(R.drawable.background));
        layout.addView(addButton());

        setContentView(layout);
    }

    private TableLayout addTable() {
        TableLayout tableLayout = new TableLayout(this);

        PlayersService playersService = new PlayersService(this);

        for (Player player : playersService.getTop10()) {
            TableRow tableRow = new TableRow(this);

            TextView nameView = new TextView(this);
            nameView.setText(player.getUserName());
            nameView.setTextSize(25);
            nameView.setTextColor(ContextCompat.getColor(this, R.color.textColor));
            nameView.setPadding(20, 20, 20, 20);
            tableRow.addView(nameView);

            TextView resultView = new TextView(this);
            resultView.setText(player.getUserResult().toString());
            resultView.setTextSize(25);
            resultView.setTextColor(ContextCompat.getColor(this, R.color.textColor));
            resultView.setPadding(400, 20, 20, 20);
            tableRow.addView(resultView);

            tableLayout.addView(tableRow);
        }

        return tableLayout;
    }

    private Button addButton() {
        Button button = new Button(this);
        button.setText(getResources().getString(R.string.to_main_menu));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        Intent i = new Intent(TopPlayersActivity.this, MainActivity.class);
                        startActivity(i);
                        finish();
                    }
                });
            }
        });

        return button;
    }
}
