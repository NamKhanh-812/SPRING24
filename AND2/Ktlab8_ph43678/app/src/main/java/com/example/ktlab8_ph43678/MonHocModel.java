package com.example.ktlab8_ph43678;

public class MonHocModel {
    private String maMonHoc,tenMonHoc;
    private int  soTiet;

    public MonHocModel() {
    }

    public MonHocModel(String maMonHoc, String tenMonHoc, int soTiet) {
        this.maMonHoc = maMonHoc;
        this.tenMonHoc = tenMonHoc;
        this.soTiet = soTiet;
    }

    public String getMaMonHoc() {
        return maMonHoc;
    }

    public void setMaMonHoc(String maMonHoc) {
        this.maMonHoc = maMonHoc;
    }

    public String getTenMonHoc() {
        return tenMonHoc;
    }

    public void setTenMonHoc(String tenMonHoc) {
        this.tenMonHoc = tenMonHoc;
    }

    public int getSoTiet() {
        return soTiet;
    }

    public void setSoTiet(int soTiet) {
        this.soTiet = soTiet;
    }
}
