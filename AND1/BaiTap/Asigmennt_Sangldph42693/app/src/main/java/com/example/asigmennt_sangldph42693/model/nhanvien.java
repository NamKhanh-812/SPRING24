package com.example.asigmennt_sangldph42693.model;

import java.io.Serializable;

public class nhanvien implements Serializable {

    private String maNV;
    private String tenNV;
    private String pbNV;

    public nhanvien() {
    }

    public nhanvien(String maNV, String tenNV, String pbNV) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.pbNV = pbNV;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getPbNV() {
        return pbNV;
    }

    public void setPbNV(String pbNV) {
        this.pbNV = pbNV;
    }
}
