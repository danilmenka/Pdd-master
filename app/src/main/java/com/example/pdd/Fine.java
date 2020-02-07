package com.example.pdd;

import com.example.pdd.Objects.Car;
import com.example.pdd.Objects.Driver;

public class Fine {
    public Fine(String id, String postNum, String postDate, String suma, String totalSuma, String discountDate, String koapCode,
                String koapText, String address, Boolean paid, Car car, Driver driver, String wirepaymentpurpose, String wirekbk,
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

    private String id;
    private String postNum;
    private String postDate;
    private String suma;
    private String totalSuma;
    private String discountDate;
    private String koapCode;
    private String koapText;
    private String address;
    private Boolean paid;
    private Car car;
    private Driver driver;
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

    public Boolean getPaid() {
        return paid;
    }

    public Car getCar() {
        return car;
    }

    public Driver getDriver() {
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

    public void setId(String id) {
        this.id = id;
    }

    public void setPostNum(String postNum) {
        this.postNum = postNum;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public void setSuma(String suma) {
        this.suma = suma;
    }

    public void setTotalSuma(String totalSuma) {
        this.totalSuma = totalSuma;
    }

    public void setDiscountDate(String discountDate) {
        this.discountDate = discountDate;
    }

    public void setKoapCode(String koapCode) {
        this.koapCode = koapCode;
    }

    public void setKoapText(String koapText) {
        this.koapText = koapText;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public void setWirepaymentpurpose(String wirepaymentpurpose) {
        this.wirepaymentpurpose = wirepaymentpurpose;
    }

    public void setWirekbk(String wirekbk) {
        this.wirekbk = wirekbk;
    }

    public void setWireuserinn(String wireuserinn) {
        this.wireuserinn = wireuserinn;
    }

    public void setWirekpp(String wirekpp) {
        this.wirekpp = wirekpp;
    }

    public void setWirebankname(String wirebankname) {
        this.wirebankname = wirebankname;
    }

    public void setWirebankaccount(String wirebankaccount) {
        this.wirebankaccount = wirebankaccount;
    }

    public void setWirebankbik(String wirebankbik) {
        this.wirebankbik = wirebankbik;
    }

    public void setWireoktmo(String wireoktmo) {
        this.wireoktmo = wireoktmo;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }


}
