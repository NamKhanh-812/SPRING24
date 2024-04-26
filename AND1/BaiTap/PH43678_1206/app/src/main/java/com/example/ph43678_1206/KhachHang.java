package com.example.ph43678_1206;

public class KhachHang {
    private String maKH;
    private String hoten;
    private String tuoi;
    private String sdt;

    public KhachHang(String maKH, String hoten, String tuoi, String sdt) {
        this.maKH = maKH;
        this.hoten = hoten;
        this.tuoi = tuoi;
        this.sdt = sdt;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getTuoi() {
        return tuoi;
    }

    public void setTuoi(String tuoi) {
        this.tuoi = tuoi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
}
