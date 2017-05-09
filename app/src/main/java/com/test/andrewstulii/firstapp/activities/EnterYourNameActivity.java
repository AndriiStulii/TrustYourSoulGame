package com.test.andrewstulii.firstapp.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.test.andrewstulii.firstapp.R;

/**
 * Created by andrewstulii.
 */
public class EnterYourNameActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String userName = editText.getText().toString();

        SharedPreferences userNamePref = this.getSharedPreferences(
                getString(R.string.user_name_key), Context.MODE_PRIVATE);
        userNamePref.edit().putString(getString(R.string.user_name_key), userName).apply();

        intent.putExtra(getString(R.string.message_key), getString(R.string.greeting, userName));
        startActivity(intent);
        finish();
    }

}
