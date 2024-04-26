package com.example.assignment.model;

public class SanPham {
    private String  tenSanPham;
    private int maSanPham ,giaBan, soLuong;

    public SanPham() {
    }

    public SanPham(String tenSanPham, int maSanPham, int giaBan, int soLuong) {
        this.tenSanPham = tenSanPham;
        this.maSanPham = maSanPham;
        this.giaBan = giaBan;
        this.soLuong = soLuong;
    }

    public SanPham(String tenSanPham, int giaBan, int soLuong) {
        this.tenSanPham = tenSanPham;
        this.giaBan = giaBan;
        this.soLuong = soLuong;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public int getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(int giaBan) {
        this.giaBan = giaBan;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
