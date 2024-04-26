package com.example.asigmennt_sangldph42693;

import java.io.Serializable;

public class user implements Serializable {
    private String user,pass;

    public user(String user, String pass) {
        this.user = user;
        this.pass = pass;
    }

    public user() {
    }

    public user(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
