package com.example.thi2;

import java.io.Serializable;

public class person implements Serializable {
    private String ma, hoTen,tuoi;

    public person() {
    }

    public person(String ma, String hoTen, String tuoi) {
        this.ma = ma;
        this.hoTen = hoTen;
        this.tuoi = tuoi;
    }

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
}
