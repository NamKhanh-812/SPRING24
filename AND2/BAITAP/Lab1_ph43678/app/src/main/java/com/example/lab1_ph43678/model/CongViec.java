package com.example.lab1_ph43678.model;

public class CongViec {
    private int id;
    private  String title, content, date, type;
    private int trangthai;

    public CongViec(int id, String title, String content, String date, String type, int trangthai) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
        this.type = type;
        this.trangthai = trangthai;
    }

    public CongViec(String title, String content, String date, String type) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.type = type;
    }

    public CongViec() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }
}
