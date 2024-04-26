package com.example.ontap2;

import java.io.Serializable;

public class sinhvien implements Serializable {
    private String ma,ten,dchi;

    public sinhvien(String ma, String ten, String dchi) {
        this.ma = ma;
        this.ten = ten;
        this.dchi = dchi;
    }

    public sinhvien() {
    }

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

    public String getDchi() {
        return dchi;
    }

    public void setDchi(String dchi) {
        this.dchi = dchi;
    }
}
