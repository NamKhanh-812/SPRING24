package com.example.ktlab7_ph43678;

public class KhachHang {
    private String makh,tenKH,quequan,gioitinh,ngaysinh;


    public KhachHang() {
    }

    public KhachHang(String makh, String tenKH, String quequan, String gioitinh, String ngaysinh) {
        this.makh = makh;
        this.tenKH = tenKH;
        this.quequan = quequan;
        this.gioitinh = gioitinh;
        this.ngaysinh = ngaysinh;
    }

    public String getMakh() {
        return makh;
    }

    public void setMakh(String makh) {
        this.makh = makh;
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
}
