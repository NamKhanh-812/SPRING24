package com.example.assignment_gd1.model;

public class nhanvien {
    private String maNv;
    private String hoTen;
    private String tenPhongBan;

    public nhanvien() {
    }

    public nhanvien(String maNv, String hoTen, String tenPhongBan) {
        this.maNv = maNv;
        this.hoTen = hoTen;
        this.tenPhongBan = tenPhongBan;
    }

    public String getMaNv() {
        return maNv;
    }

    public void setMaNv(String maNv) {
        this.maNv = maNv;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getTenPhongBan() {
        return tenPhongBan;
    }

    public void setTenPhongBan(String tenPhongBan) {
        this.tenPhongBan = tenPhongBan;
    }
}
