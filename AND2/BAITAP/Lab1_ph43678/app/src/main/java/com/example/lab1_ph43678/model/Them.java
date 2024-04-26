package com.example.lab1_ph43678.model;

public class Them {
    private String maSV, hoTen, sdt, queQuan;
    private int id;

    public Them() {
    }

    public Them(String maSV, String hoTen, String sdt, String queQuan, int id) {
        this.maSV = maSV;
        this.hoTen = hoTen;
        this.sdt = sdt;
        this.queQuan = queQuan;
        this.id = id;
    }

    public Them(String maSV, String hoTen, String sdt, String queQuan) {
        this.maSV = maSV;
        this.hoTen = hoTen;
        this.sdt = sdt;
        this.queQuan = queQuan;
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getQueQuan() {
        return queQuan;
    }

    public void setQueQuan(String queQuan) {
        this.queQuan = queQuan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
