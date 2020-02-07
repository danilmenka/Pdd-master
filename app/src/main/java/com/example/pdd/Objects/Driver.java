package com.example.pdd.Objects;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.pdd.DBHelp.DBHelperCars;
import com.example.pdd.DBHelp.DBHelperDrivers;

import org.json.JSONException;
import org.json.JSONObject;

public class Driver {
    public Driver(String id, String liscenseNum, String lastScanDate, String title, String descr, String createDate) {
        this.id = id;
        this.licenseNum = liscenseNum;
        this.lastScanDate = lastScanDate;
        this.title = title;
        this.descr = descr;
        this.createDate = createDate;
    }
    private String id;
    private String licenseNum;
    private String lastScanDate;
    private String title;
    private String descr;
    private String createDate;

    public Driver(JSONObject json1) {
        try {
            id = json1.getString("id");
            licenseNum = json1.getString("licenseNum");
            lastScanDate = json1.getString("lastScanDate");
            title = json1.getString("title");
            descr = json1.getString("descr");
            createDate = json1.getString("createDate");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLiscenseNum() {
        return licenseNum;
    }

    public void setLiscenseNum(String liscenseNum) {
        this.licenseNum = liscenseNum;
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


    public void insertDbDriver(Context context) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelperDrivers.KEY_IDDRIVER,id);
        contentValues.put(DBHelperDrivers.KEY_LICENSENUM,licenseNum);
        contentValues.put(DBHelperDrivers.KEY_SCANDATE,lastScanDate);
        contentValues.put(DBHelperDrivers.KEY_TITLE,title);
        contentValues.put(DBHelperDrivers.KEY_DESCR,descr);
        contentValues.put(DBHelperDrivers.KEY_CREATEDATE,createDate);
        DBHelperDrivers dbHelper;
        dbHelper = new DBHelperDrivers(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        database.insert(DBHelperDrivers.TABLE_DRIVERS, null, contentValues);
    }
}
