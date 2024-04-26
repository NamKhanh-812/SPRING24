package com.example.lab5_ph43678.model;

public class monAn {
    private String tenMonAn;
    private String giaMonAn;
    private String diaChiMonAn;

    public monAn(String tenMonAn, String giaMonAn, String diaChiMonAn) {
        this.tenMonAn = tenMonAn;
        this.giaMonAn = giaMonAn;
        this.diaChiMonAn = diaChiMonAn;
    }

    public monAn() {
    }

    public String getTenMonAn() {
        return tenMonAn;
    }

    public void setTenMonAn(String tenMonAn) {
        this.tenMonAn = tenMonAn;
    }

    public String getGiaMonAn() {
        return giaMonAn;
    }

    public void setGiaMonAn(String giaMonAn) {
        this.giaMonAn = giaMonAn;
    }

    public String getDiaChiMonAn() {
        return diaChiMonAn;
    }

    public void setDiaChiMonAn(String diaChiMonAn) {
        this.diaChiMonAn = diaChiMonAn;
    }
}
