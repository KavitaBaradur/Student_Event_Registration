package com.team2_wpi.android.student_event_registration.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by kavitabaradur on 4/3/17.
 */

public class DBOpenHelper extends SQLiteOpenHelper {

    public DBOpenHelper(Context context, String name,int version) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
