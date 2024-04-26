package com.example.lab5_ph43678.model;

public class school {
    private int hinh;
    private String ten;

    public school() {
    }

    public school(int hinh, String ten) {
        this.hinh = hinh;
        this.ten = ten;
    }

    public int getHinh() {
        return hinh;
    }

    public void setHinh(int hinh) {
        this.hinh = hinh;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }
}
