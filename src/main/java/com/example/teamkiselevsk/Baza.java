package com.example.teamkiselevsk;

public class Baza {
    int id;
    String login,email,password,tip;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public Baza(int id, String login, String email, String password, String tip) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.password = password;
        this.tip = tip;
    }
}
