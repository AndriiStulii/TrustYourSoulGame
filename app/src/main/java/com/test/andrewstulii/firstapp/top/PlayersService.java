package com.test.andrewstulii.firstapp.top;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrewstulii.
 */
public class PlayersService {

    private Context context;

    public PlayersService(Context context) {
        this.context = context;
    }

    public List<Player> getTop10() {
        List<Player> top10 = new ArrayList<>();
        SQLiteDatabase database = new PlayersDao(context).getReadableDatabase();

        String[] projection = {
                PlayerContract.PlayerEntry.COLUMN_NAME_USER,
                PlayerContract.PlayerEntry.COLUMN_NAME_RESULT
        };

        String orderBy = PlayerContract.PlayerEntry.COLUMN_NAME_RESULT + " DESC";

        Cursor cursor = database.query(
                PlayerContract.PlayerEntry.TABLE_NAME,          // The table to query
                projection,                               // The columns to return
                null,                                     // The columns for the WHERE clause
                null,                                     // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                orderBy,                                  // The sort order
                "10"                                      // limit
        );

        try {
            while (cursor.moveToNext()) {
                top10.add(new Player(
                        cursor.getString(cursor.getColumnIndexOrThrow(PlayerContract.PlayerEntry.COLUMN_NAME_USER)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(PlayerContract.PlayerEntry.COLUMN_NAME_RESULT))));
            }
        } finally {
            cursor.close();
            database.close();
        }

        return top10;
    }

    public void writePlayerResults(Player user){
        SQLiteDatabase database = new PlayersDao(context).getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PlayerContract.PlayerEntry.COLUMN_NAME_USER, user.getUserName());
        values.put(PlayerContract.PlayerEntry.COLUMN_NAME_RESULT, user.getUserResult());

        database.insert(PlayerContract.PlayerEntry.TABLE_NAME, null, values);

        database.close();
    }

}
