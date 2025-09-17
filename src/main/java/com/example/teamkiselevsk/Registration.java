package com.example.teamkiselevsk;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.sql.PreparedStatement;
import java.sql.Connection;

import javax.swing.*;

public class Registration {

    ObservableList list = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button nazad;

    @FXML
    private TextField email;

    @FXML
    private TextField login;

    @FXML
    private PasswordField password;

    @FXML
    private Button registration;

    Connection conn = null;
    PreparedStatement pst = null;

    @FXML
    void initialize() {
    }

    public void back(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader2 = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader2.load(), 600, 400);
        Stage stage1 = new Stage();
        stage1.setScene(scene);
        stage1.show();


        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("registration.fxml"));
        Stage stage = (Stage) nazad.getScene().getWindow();
        stage.close();

    }

    public void Register(javafx.event.ActionEvent actionEvent) throws  IOException {
        String login1 = login.getText().toString();
        String email1 = email.getText().toString();
        String password1 = password.getText().toString();
        conn = Konekt.ConnectDb();

        if (login1.isEmpty() || email1.isEmpty() || password1.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Все поля должны быть заполнены!");
            return;
        }

        String tip = "Пользователь";

        String sql = "insert into users (login, email, password, tip)values(?,?,?,?)";
        try {
            assert conn != null;
            pst = conn.prepareStatement(sql);
            pst.setString(1, login1);
            pst.setString(2, email1);
            pst.setString(3, password1);
            pst.setString(4, tip);
            pst.execute();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        FXMLLoader fxmlLoader1 = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader1.load(), 600, 400);
        Stage stage1 = new Stage();
        stage1.setScene(scene);
        stage1.show();//Открытие нового окна


        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("registration.fxml"));
        Stage stage = (Stage) registration.getScene().getWindow();
        stage.close();
        JOptionPane.showMessageDialog(null, "Регистрация прошла успешно!");
    }
    }


