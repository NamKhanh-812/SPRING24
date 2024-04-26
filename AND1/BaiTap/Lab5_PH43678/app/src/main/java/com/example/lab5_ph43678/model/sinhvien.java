package com.example.lab5_ph43678.model;

public class sinhvien {
    private String tenCoSo;
    private String hoTen;
    private String diaChi;

    public sinhvien() {
    }

    public sinhvien(String tenCoSo, String hoTen, String diaChi) {
        this.tenCoSo = tenCoSo;
        this.hoTen = hoTen;
        this.diaChi = diaChi;
    }

    public String getTenCoSo() {
        return tenCoSo;
    }

    public void setTenCoSo(String tenCoSo) {
        this.tenCoSo = tenCoSo;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
}
