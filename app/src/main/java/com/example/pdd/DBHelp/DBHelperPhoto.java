package com.example.pdd.DBHelp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelperPhoto extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 4;
    public static final String DATABASE_NAME = "PddDbPhoto";
    public static final String KEY_ID = "_id";
    public static final String TABLE_PHOTO = "tablePhoto";
    public static final String KEY_FROMING = "froming";
    public static final String KEY_IDOBJECT = "idObject";
    public static final String KEY_BASE64 = "base64";


    public DBHelperPhoto(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_PHOTO + "(" +
                KEY_ID + " integer primary key," +
                KEY_BASE64  + " text unique," +
                KEY_IDOBJECT + " text," +
                KEY_FROMING + " text" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_PHOTO);
        onCreate(db);

    }
}
