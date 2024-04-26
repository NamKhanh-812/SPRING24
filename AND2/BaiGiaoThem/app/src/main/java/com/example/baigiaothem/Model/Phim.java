package com.example.baigiaothem.Model;

public class Phim {
    private String tenPhim;
    private int id;

    public Phim() {
    }

    public Phim(String tenPhim, int id) {
        this.tenPhim = tenPhim;
        this.id = id;
    }

    public Phim(String tenPhim) {
        this.tenPhim = tenPhim;
    }

    public String getTenPhim() {
        return tenPhim;
    }

    public void setTenPhim(String tenPhim) {
        this.tenPhim = tenPhim;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
