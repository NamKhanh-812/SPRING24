package com.example.qlma.model;

import java.util.HashMap;

public class MonAn {
    private String id, maMonAn, tenMonAn, ngayLam;
    private int trangThai;

    public MonAn() {
    }

    public HashMap<String, Object> converHashMap() {
        HashMap<String, Object> work = new HashMap<>();
        work.put("id", id);
        work.put("maMonAn", maMonAn);
        work.put("tenMonAn", tenMonAn);
        work.put("ngayLam", ngayLam);
        return work;
    }

    public MonAn(String id, String maMonAn, String tenMonAn, String ngayLam, int trangThai) {
        this.id = id;
        this.maMonAn = maMonAn;
        this.tenMonAn = tenMonAn;
        this.ngayLam = ngayLam;
        this.trangThai = trangThai;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaMonAn() {
        return maMonAn;
    }

    public void setMaMonAn(String maMonAn) {
        this.maMonAn = maMonAn;
    }

    public String getTenMonAn() {
        return tenMonAn;
    }

    public void setTenMonAn(String tenMonAn) {
        this.tenMonAn = tenMonAn;
    }

    public String getNgayLam() {
        return ngayLam;
    }

    public void setNgayLam(String ngayLam) {
        this.ngayLam = ngayLam;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public MonAn(String maMonAn, String tenMonAn, String ngayLam) {
        this.maMonAn = maMonAn;
        this.tenMonAn = tenMonAn;
        this.ngayLam = ngayLam;
    }
}

