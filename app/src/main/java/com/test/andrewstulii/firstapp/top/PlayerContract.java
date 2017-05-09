package com.test.andrewstulii.firstapp.top;

import android.provider.BaseColumns;

/**
 * Created by andrewstulii.
 */
public final class PlayerContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public PlayerContract() {}

    /* Inner class that defines the table contents */
    public static abstract class PlayerEntry implements BaseColumns {
        public static final String TABLE_NAME = "tops";
        public static final String COLUMN_NAME_USER = "user";
        public static final String COLUMN_NAME_RESULT = "result";


        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + PlayerEntry.TABLE_NAME + " (" +
                        PlayerEntry._ID + " INTEGER PRIMARY KEY," +
                        PlayerEntry.COLUMN_NAME_USER + " TEXT," +
                        PlayerEntry.COLUMN_NAME_RESULT + " INT" + " )";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + PlayerEntry.TABLE_NAME;
    }
}
