package com.example.thithu3;

import java.io.Serializable;

public class dv implements Serializable {
    private String ma;
    private String ten;
    private double cannang;
    private int soluong;

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public double getCannang() {
        return cannang;
    }

    public void setCannang(double cannang) {
        this.cannang = cannang;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public dv() {
    }

    public dv(String ma, String ten, double cannang, int soluong) {
        this.ma = ma;
        this.ten = ten;
        this.cannang = cannang;
        this.soluong = soluong;
    }
}
