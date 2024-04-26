package com.example.lab8_ph43678;

import java.io.Serializable;

public class nhanvien implements Serializable {
    private String manv;
    private String hoten;
    private String diachi;

    public nhanvien(String manv, String hoten, String diachi) {
        this.manv = manv;
        this.hoten = hoten;
        this.diachi = diachi;
    }

    public nhanvien() {
    }

    public String getManv() {
        return manv;
    }

    public void setManv(String manv) {
        this.manv = manv;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }
}
