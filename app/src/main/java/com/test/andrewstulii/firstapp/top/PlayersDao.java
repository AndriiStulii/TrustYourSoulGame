package com.test.andrewstulii.firstapp.top;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by andrewstulii.
 */
public class PlayersDao extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "TrustYourSoul.db";
    private static final int DATABASE_VERSION = 1;

    PlayersDao(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(PlayerContract.PlayerEntry.SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(PlayerContract.PlayerEntry.SQL_DELETE_ENTRIES);
        onCreate(db);
    }

}
