package com.example.pdd.DBHelp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelperDrivers  extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "PddDbDrivers";
    public static final String KEY_ID = "_id";
    public static final String TABLE_DRIVERS = "drivers";
    public static final String KEY_IDDRIVER = "idDriver";
    public static final String KEY_LICENSENUM = "licenseNum";
    public static final String KEY_SCANDATE = "lastScanDate";
    public static final String KEY_TITLE = "title";
    public static final String KEY_DESCR = "descr";
    public static final String KEY_CREATEDATE = "createDate";

    public DBHelperDrivers(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_DRIVERS + "(" +
                KEY_ID + " integer primary key," +
                KEY_IDDRIVER + " text," +
                KEY_LICENSENUM + " text," +
                KEY_SCANDATE + " text," +
                KEY_TITLE + " text," +
                KEY_DESCR + " text," +
                KEY_CREATEDATE + " text" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_DRIVERS);
        onCreate(db);

    }
}