package com.example.pdd.DBHelp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelperUnpaidFines extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "PddDbUnpaidFines";
    public static final String KEY_ID = "_id";
    public static final String TABLE_UNPAID_FINES = "fines";
    public static final String KEY_IDFINE = "idFine";
    public static final String KEY_POSTNUM = "postNum";
    public static final String KEY_POSTDATE = "postDate";
    public static final String KEY_SUMA = "suma";
    public static final String KEY_TOTALSUMA = "totalSuma";
    public static final String KEY_DISCOUNTDATE = "discountDate";
    public static final String KEY_KOAPCODE= "koapCode";
    public static final String KEY_KOAPTEXT = "koapText";
    public static final String KEY_ADDRESS = "Address";
    public static final String KEY_PAID= "paid";
    public static final String KEY_CAR = "car";
    public static final String KEY_DRIVER= "driver";
    public static final String KEY_WIREPAYMENTPURPOSE = "wirepaymentpurpose";
    public static final String KEY_WIREKBK = "wirekbk";
    public static final String KEY_WIREUSERINN= "wireuserinn";
    public static final String KEY_WIREKPP= "wirekpp";
    public static final String KEY_WIREBANKNAME= "wirebankname";
    public static final String KEY_WIREBANKACCOUNT= "wirebankaccount";
    public static final String KEY_WIREBANKBIK= "wirebankbik";
    public static final String KEY_WIREOKTMO= "wireoktmo";
    public static final String KEY_CREATEDATE= "createDate";

    public DBHelperUnpaidFines(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_UNPAID_FINES + "(" +
                KEY_ID + " integer primary key," +
                KEY_IDFINE + " text unique," +
                KEY_POSTNUM + " text," +
                KEY_POSTDATE + " text," +
                KEY_SUMA + " text," +
                KEY_TOTALSUMA + " text," +
                KEY_DISCOUNTDATE + " text," +
                KEY_KOAPCODE + " text," +
                KEY_KOAPTEXT + " text," +
                KEY_ADDRESS + " text," +
                KEY_PAID + " text," +
                KEY_CAR + " text," +
                KEY_DRIVER + " text," +
                KEY_WIREPAYMENTPURPOSE + " text," +
                KEY_WIREKBK + " text," +
                KEY_WIREUSERINN + " text," +
                KEY_WIREKPP + " text," +
                KEY_WIREBANKNAME + " text," +
                KEY_WIREBANKACCOUNT + " text," +
                KEY_WIREBANKBIK + " text," +
                KEY_WIREOKTMO + " text," +
                KEY_CREATEDATE + " text" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_UNPAID_FINES);
        onCreate(db);

    }
}