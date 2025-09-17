package com.example.teamkiselevsk;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.sql.*;

public class Konekt{

    public static Connection ConnectDb() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/polzovateli", "root", "");
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    public static ObservableList<Baza> getBazakpk() {
        Connection connBaza = ConnectDb();
        ObservableList<Baza> list = FXCollections.observableArrayList();
        try {
            assert connBaza != null;
            PreparedStatement ps1 = connBaza.prepareStatement("select * from users");
            ResultSet rs1 = ps1.executeQuery();

            while (rs1.next()) {
                list.add(new Baza(Integer.parseInt(rs1.getString("id")), rs1.getString("login"), rs1.getString("email"), rs1.getString("password"), rs1.getString("tip")));
            }
        } catch (Exception ignored) {
        }
        return list;
    }
    public static ObservableList<Baza2> getBazakpk2() {
        Connection connBaza = ConnectDb();
        ObservableList<Baza2> list1 = FXCollections.observableArrayList();
        try {
            assert connBaza != null;
            PreparedStatement ps2 = connBaza.prepareStatement("select * from raspmatch");
            ResultSet rs2 = ps2.executeQuery();

            while (rs2.next()) {
                list1.add(new Baza2(Integer.parseInt(rs2.getString("id")), rs2.getString("toornir"), rs2.getString("protivnik"), Date.valueOf(rs2.getString("data")), rs2.getString("schet")));
            }
        } catch (Exception ignored) {
        }
        return list1;
    }

}
