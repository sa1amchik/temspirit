package com.example.teamkiselevsk;
import java.util.Date;
public class Baza2 {
    int id;
    String toornir, protivnik, schet;
    Date data;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToornir() {
        return toornir;
    }

    public void setToornir(String toornir) {
        this.toornir = toornir;
    }

    public String getProtivnik() {
        return protivnik;
    }

    public void setProtivnik(String protivnik) {
        this.protivnik = protivnik;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getSchet() {
        return schet;
    }

    public void setSchet(String schet) {
        this.schet = schet;
    }

    public Baza2(int id, String toornir, String protivnik, Date data, String schet) {
        this.id = id;
        this.toornir = toornir;
        this.protivnik = protivnik;
        this.data = data;
        this.schet = schet;
    }
}
