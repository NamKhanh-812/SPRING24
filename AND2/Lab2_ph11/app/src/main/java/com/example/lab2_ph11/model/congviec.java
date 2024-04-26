package com.example.lab2_ph11.model;

import java.util.HashMap;

public class congviec {
    private String id;
    private String tieude,noidung,ngay,loai;
    private int trangthai;
    //hàm này có
    //nhiệm vụ xử lý dữ liệu thao tác trên Firestore Database
    public HashMap<String,Object> convertHaspmap(){
        HashMap<String,Object> work=new HashMap<>();
        work.put("id",id);
        work.put("tieude",tieude);
        work.put("noidung",noidung);
        work.put("ngay",ngay);
        work.put("loai",loai);
        work.put("trangthai",trangthai);
        return work;
    }

    public congviec() {
    }

    public congviec(String id, String tieude, String noidung, String ngay, String loai, int trangthai) {
        this.id = id;
        this.tieude = tieude;
        this.noidung = noidung;
        this.ngay = ngay;
        this.loai = loai;
        this.trangthai = trangthai;
    }

    public congviec(String tieude, String noidung, String ngay, String loai) {
        this.tieude = tieude;
        this.noidung = noidung;
        this.ngay = ngay;
        this.loai = loai;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTieude() {
        return tieude;
    }

    public void setTieude(String tieude) {
        this.tieude = tieude;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }
}
