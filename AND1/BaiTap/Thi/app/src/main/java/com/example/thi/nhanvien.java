package com.example.thi;

import java.io.Serializable;

public class nhanvien implements Serializable {
    private String ma;
    private String hoTen;
    private String tuoi;

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getTuoi() {
        return tuoi;
    }

    public void setTuoi(String tuoi) {
        this.tuoi = tuoi;
    }

    public nhanvien(String ma, String hoTen, String tuoi) {
        this.ma = ma;
        this.hoTen = hoTen;
        this.tuoi = tuoi;
    }

    public nhanvien() {
    }
}
