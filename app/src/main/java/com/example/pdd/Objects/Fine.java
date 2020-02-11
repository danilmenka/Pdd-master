package com.example.pdd.Objects;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.pdd.DBHelp.DBHelperCars;
import com.example.pdd.DBHelp.DBHelperFines;
import com.example.pdd.Objects.Car;
import com.example.pdd.Objects.Driver;

import org.json.JSONException;
import org.json.JSONObject;

public class Fine {
    public Fine(String id, String postNum, String postDate, String suma, String totalSuma, String discountDate, String koapCode,
                String koapText, String address, String paid, String car, String driver, String wirepaymentpurpose, String wirekbk,
                String wireuserinn, String wirekpp, String wirebankname, String wirebankaccount, String wirebankbik, String wireoktmo,
                String createDate) {
        this.id = id;
        this.postNum = postNum;
        this.postDate = postDate;
        this.suma = suma;
        this.totalSuma = totalSuma;
        this.discountDate = discountDate;
        this.koapCode = koapCode;
        this.koapText = koapText;
        this.address = address;
        this.paid = paid;
        this.car = car;
        this.driver = driver;
        this.wirepaymentpurpose = wirepaymentpurpose;
        this.wirekbk = wirekbk;
        this.wireuserinn = wireuserinn;
        this.wirekpp = wirekpp;
        this.wirebankname = wirebankname;
        this.wirebankaccount = wirebankaccount;
        this.wirebankbik = wirebankbik;
        this.wireoktmo = wireoktmo;
        this.createDate = createDate;
    }
    public Fine(JSONObject json1){
        try {
         id = json1.getString("id");
         postNum = json1.getString("postNum");
         postDate= json1.getString("postDate");
         suma = json1.getString("suma");
         totalSuma = json1.getString("totalSuma");
         discountDate = json1.getString("discountDate");
         koapCode = json1.getString("koapCode");
         koapText = json1.getString("koapText");
         address = json1.getString("address");
         paid = json1.getString("paid");
         car = json1.getString("car");
         driver = json1.getString("driver");
         wirepaymentpurpose = json1.getString("wirepaymentpurpose");
         wirekbk = json1.getString("wirekbk");
         wireuserinn = json1.getString("wireuserinn");
         wirekpp = json1.getString("wirekpp");
         wirebankname = json1.getString("wirebankname");
         wirebankaccount = json1.getString("wirebankaccount");
         wirebankbik = json1.getString("wirebankbik");
         wireoktmo = json1.getString("wireoktmo");
         createDate = json1.getString("createDate");
        } catch (JSONException e) {
            e.printStackTrace();
        }
         }
    private String id;
    private String postNum;
    private String postDate;
    private String suma;
    private String totalSuma;
    private String discountDate;
    private String koapCode;
    private String koapText;
    private String address;
    private String paid;
    private String car;
    private String driver;
    private String wirepaymentpurpose;
    private String wirekbk;
    private String wireuserinn;
    private String wirekpp;
    private String wirebankname;
    private String wirebankaccount;
    private String wirebankbik;
    private String wireoktmo;
    private String createDate;

    public String getId() {
        return id;
    }

    public String getPostNum() {
        return postNum;
    }

    public String getPostDate() {
        return postDate;
    }

    public String getSuma() {
        return suma;
    }

    public String getTotalSuma() {
        return totalSuma;
    }

    public String getDiscountDate() {
        return discountDate;
    }

    public String getKoapCode() {
        return koapCode;
    }

    public String getKoapText() {
        return koapText;
    }

    public String getAddress() {
        return address;
    }

    public String getPaid() {
        return paid;
    }

    public String getCar() {
        return car;
    }

    public String getDriver() {
        return driver;
    }

    public String getWirepaymentpurpose() {
        return wirepaymentpurpose;
    }

    public String getWirekbk() {
        return wirekbk;
    }

    public String getWireuserinn() {
        return wireuserinn;
    }

    public String getWirekpp() {
        return wirekpp;
    }

    public String getWirebankname() {
        return wirebankname;
    }

    public String getWirebankaccount() {
        return wirebankaccount;
    }

    public String getWirebankbik() {
        return wirebankbik;
    }

    public String getWireoktmo() {
        return wireoktmo;
    }

    public String getCreateDate() {
        return createDate;
    }



    public void insertDbFine(Context context){
        ContentValues contentValues = new ContentValues();
                contentValues.put(DBHelperFines.KEY_IDFINE,id);
                contentValues.put(DBHelperFines.KEY_POSTNUM,postNum);
                contentValues.put(DBHelperFines.KEY_POSTDATE,postDate);
                contentValues.put(DBHelperFines.KEY_SUMA,suma);
                contentValues.put(DBHelperFines.KEY_TOTALSUMA,totalSuma);
                contentValues.put(DBHelperFines.KEY_DISCOUNTDATE,discountDate);
                contentValues.put(DBHelperFines.KEY_KOAPCODE,koapCode);
                contentValues.put(DBHelperFines.KEY_KOAPTEXT,koapText);
                contentValues.put(DBHelperFines.KEY_ADDRESS,address);
                contentValues.put(DBHelperFines.KEY_PAID,paid);
                contentValues.put(DBHelperFines.KEY_CAR,car);
                contentValues.put(DBHelperFines.KEY_DRIVER,driver);
                contentValues.put(DBHelperFines.KEY_WIREPAYMENTPURPOSE,wirepaymentpurpose);
                contentValues.put(DBHelperFines.KEY_WIREKBK,wirekbk);
                contentValues.put(DBHelperFines.KEY_WIREUSERINN,wireuserinn);
                contentValues.put(DBHelperFines.KEY_WIREKPP,wirekpp);
                contentValues.put(DBHelperFines.KEY_WIREBANKNAME,wirebankname);
                contentValues.put(DBHelperFines.KEY_WIREBANKACCOUNT,wirebankaccount);
                contentValues.put(DBHelperFines.KEY_WIREBANKBIK,wirebankbik);
                contentValues.put(DBHelperFines.KEY_WIREOKTMO,wireoktmo);
                contentValues.put(DBHelperFines.KEY_CREATEDATE,createDate);

        DBHelperFines dbHelper;
        dbHelper = new DBHelperFines(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        database.insert(DBHelperFines.TABLE_FINES, null, contentValues);

    }

}
