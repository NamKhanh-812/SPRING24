package com.example.thithu_ph43678;

public class DongVat {
    private int id,canNang;
    private String tenDongVat, mauSac;

    public DongVat() {
    }

    public DongVat(int id, String tenDongVat, String mauSac, int canNang) {
        this.id = id;
        this.tenDongVat = tenDongVat;
        this.mauSac = mauSac;
        this.canNang = canNang;
    }

    public DongVat(String tenDongVat, String mauSac, int canNang) {
        this.tenDongVat = tenDongVat;
        this.mauSac = mauSac;
        this.canNang = canNang;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenDongVat() {
        return tenDongVat;
    }

    public void setTenDongVat(String tenDongVat) {
        this.tenDongVat = tenDongVat;
    }

    public String getMauSac() {
        return mauSac;
    }

    public void setMauSac(String mauSac) {
        this.mauSac = mauSac;
    }

    public int getCanNang() {
        return canNang;
    }

    public void setCanNang(int canNang) {
        this.canNang = canNang;
    }
}
