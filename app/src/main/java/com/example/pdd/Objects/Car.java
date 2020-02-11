package com.example.pdd.Objects;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.pdd.DBHelp.DBHelperCars;

import org.json.JSONException;
import org.json.JSONObject;

public class Car {
    public Car(String id, String regnum, String stsnum, String lastScanDate, String title, String descr, String createDate) {
        this.id = id;
        this.regnum = regnum;
        this.stsnum = stsnum;
        this.lastScanDate = lastScanDate;
        this.title = title;
        this.descr = descr;
        this.createDate = createDate;
    }
    public Car(JSONObject json1){

        try {
            id = json1.getString("id");
            regnum = json1.getString("regnum");
            stsnum = json1.getString("stsnum");
            lastScanDate = json1.getString("lastScanDate");
            title = json1.getString("title");
            descr = json1.getString("descr");
            createDate = json1.getString("createDate");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String id;
    private String regnum;
    private String stsnum;
    private String lastScanDate;
    private String title;
    private String descr;
    private String createDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRegnum() {
        return regnum;
    }

    public void setRegnum(String regnum) {
        this.regnum = regnum;
    }

    public String getStsnum() {
        return stsnum;
    }

    public void setStsnum(String stsnum) {
        this.stsnum = stsnum;
    }

    public String getLastScanDate() {
        return lastScanDate;
    }

    public void setLastScanDate(String lastScanDate) {
        this.lastScanDate = lastScanDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public void insertDbCar(Context context){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelperCars.KEY_IDCAR,id);
        contentValues.put(DBHelperCars.KEY_REGNUM,regnum);
        contentValues.put(DBHelperCars.KEY_STSNUM,stsnum);
        contentValues.put(DBHelperCars.KEY_LAST,lastScanDate);
        contentValues.put(DBHelperCars.KEY_TITLE,title);
        contentValues.put(DBHelperCars.KEY_DESCR,descr);
        contentValues.put(DBHelperCars.KEY_DATE,createDate);
            DBHelperCars dbHelper;
            dbHelper = new DBHelperCars(context);
            SQLiteDatabase database = dbHelper.getWritableDatabase();
            database.insert(DBHelperCars.TABLE_CARS, null, contentValues);
       // database.delete(DBHelperCars.TABLE_CARS, null, null);
    }


}
