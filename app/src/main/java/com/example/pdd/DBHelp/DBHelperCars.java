package com.example.pdd.DBHelp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelperCars  extends SQLiteOpenHelper{
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "PddDb";
    public static final String KEY_ID = "_id";
    public static final String TABLE_CARS = "cars";
    public static final String KEY_IDCAR = "idCar";
    public static final String KEY_REGNUM = "regnum";
    public static final String KEY_STSNUM = "stsnum";
    public static final String KEY_LAST = "lastScanDate";
    public static final String KEY_TITLE = "titile";
    public static final String KEY_DESCR = "descr";
    public static final String KEY_DATE = "createDate";

    public DBHelperCars(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_CARS + "(" +
                KEY_ID + " integer primary key," +
                KEY_IDCAR + " text unique," +
                KEY_REGNUM + " text," +
                KEY_STSNUM + " text," +
                KEY_LAST + " text," +
                KEY_TITLE + " text," +
                KEY_DESCR + " text," +
                KEY_DATE + " text" +")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_CARS);
        onCreate(db);

    }
}
