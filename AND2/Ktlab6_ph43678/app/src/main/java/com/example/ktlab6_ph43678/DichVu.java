package com.example.ktlab6_ph43678;

public class DichVu {
    private int maDV, thanhTien;
    private String ngay, noiDung;

    public DichVu() {
    }

    public DichVu(int maDV, int thanhTien, String ngay, String noiDung) {
        this.maDV = maDV;
        this.thanhTien = thanhTien;
        this.ngay = ngay;
        this.noiDung = noiDung;
    }

    public DichVu( String noiDung, String ngay,int thanhTieng) {
        this.thanhTien = thanhTien;
        this.ngay = ngay;
        this.noiDung = noiDung;
    }

    public int getMaDV() {
        return maDV;
    }

    public void setMaDV(int maDV) {
        this.maDV = maDV;
    }

    public int getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(int thanhTien) {
        this.thanhTien = thanhTien;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }
}
