package com.example.baigiaothem.Model;

public class DienVien {
    private String tenDV;
    private int id;

    public DienVien() {
    }

    public DienVien(String tenDV, int id) {
        this.tenDV = tenDV;
        this.id = id;
    }

    public DienVien(String tenDV) {
        this.tenDV = tenDV;
    }

    public String getTenDV() {
        return tenDV;
    }

    public void setTenDV(String tenDV) {
        this.tenDV = tenDV;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
