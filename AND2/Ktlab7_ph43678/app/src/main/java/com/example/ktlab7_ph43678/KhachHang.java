package com.example.ktlab7_ph43678;

public class KhachHang {
    private String tenKH,quequan,gioitinh,ngaysinh;
    private int makh;

    public KhachHang() {
    }

    public KhachHang(String tenKH, String quequan, String gioitinh, String ngaysinh, int makh) {
        this.tenKH = tenKH;
        this.quequan = quequan;
        this.gioitinh = gioitinh;
        this.ngaysinh = ngaysinh;
        this.makh = makh;
    }

    public KhachHang(String tenKH, String quequan, String gioitinh, String ngaysinh) {
        this.tenKH = tenKH;
        this.quequan = quequan;
        this.gioitinh = gioitinh;
        this.ngaysinh = ngaysinh;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getQuequan() {
        return quequan;
    }

    public void setQuequan(String quequan) {
        this.quequan = quequan;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public int getMakh() {
        return makh;
    }

    public void setMakh(int makh) {
        this.makh = makh;
    }
}
