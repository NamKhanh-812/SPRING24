package com.example.baithem_lab4.model;

public class phim {
    private String tenPhim;
    private int img;

    public phim() {
    }

    public phim(String tenPhim, int img) {
        this.tenPhim = tenPhim;
        this.img = img;
    }

    public String getTenPhim() {
        return tenPhim;
    }

    public void setTenPhim(String tenPhim) {
        this.tenPhim = tenPhim;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
