package com.example.teamkiselevsk;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javax.swing.*;
import java.sql.Connection;
import javafx.stage.Stage;
import java.sql.ResultSet;

public class HelloController {

        @FXML
        private ResourceBundle resources;

        @FXML
        private URL location;

        @FXML
        private TextField login;

        @FXML
        private PasswordField password;

        @FXML
        private Button reg;

        @FXML
        private Button vxod;

    PreparedStatement pst = null;
    Connection conn = null;
    ResultSet rs = null;

        @FXML
        void initialize() {
            assert login != null : "fx:id=\"login\" was not injected: check your FXML file 'hello-view.fxml'.";
            assert password != null : "fx:id=\"password\" was not injected: check your FXML file 'hello-view.fxml'.";
            assert reg != null : "fx:id=\"reg\" was not injected: check your FXML file 'hello-view.fxml'.";
            assert vxod != null : "fx:id=\"vxod\" was not injected: check your FXML file 'hello-view.fxml'.";

        }

    public void Vhod(javafx.event.ActionEvent actionEvent) throws IOException {
        String login1 = login.getText().toString();
        String password1 = password.getText().toString();
        conn = Konekt.ConnectDb();

        if (login1.isEmpty() || password1.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Не все поля заполнены!");
            return;
        }
        String sql1 = "Select * from users where tip = ? and login = ? and password = ?";
        String sql2 = "Select * from users where tip = ? and login = ? and password = ?";
        String d1 = "Пользователь";
        String d2 = "Администратор";
        try {
            pst = conn.prepareStatement(sql1);
            pst.setString(1, d2);
            pst.setString(2, login1);
            pst.setString(3, password1);

            rs = pst.executeQuery();

            if (rs.next()) {

                FXMLLoader fxmlLoader1 = new FXMLLoader(HelloApplication.class.getResource("adminmenu.fxml"));
                Scene scene = new Scene(fxmlLoader1.load(), 600, 400);
                Stage stage1 = new Stage();
                stage1.setScene(scene);
                stage1.show();//Открытие нового окна


                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
                Stage stage = (Stage) vxod.getScene().getWindow();
                stage.close();//Закрытие предыдущего окна
            } else

                try {
                    pst = conn.prepareStatement(sql2);
                    pst.setString(1, d1);
                    pst.setString(2, login1);
                    pst.setString(3, password1);

                    rs = pst.executeQuery();

                    if (rs.next()) {


                        FXMLLoader fxmlLoader2 = new FXMLLoader(HelloApplication.class.getResource("mainmenu.fxml"));
                        Scene scene1 = new Scene(fxmlLoader2.load(), 771, 524);
                        Stage stage2 = new Stage();
                        stage2.setScene(scene1);
                        stage2.show();//Открытие нового окна


                        FXMLLoader fxmlLoader3 = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
                        Stage stage3 = (Stage) vxod.getScene().getWindow();
                        stage3.close();//Закрытие предыдущего окна
                    } else JOptionPane.showMessageDialog(null, "Данные введены неверно!");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
        } catch (SQLException e) {
            throw new RuntimeException(e);


        }
    }

    public void perexodreg(ActionEvent actionEvent) throws IOException {

        FXMLLoader fxmlLoader3 = new FXMLLoader(HelloApplication.class.getResource("registration.fxml"));
        Scene scene1 = new Scene(fxmlLoader3.load(), 600, 420);
        Stage stage2 = new Stage();
        stage2.setScene(scene1);
        stage2.show();//Открытие нового окна


        FXMLLoader fxmlLoader4 = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Stage stage3 = (Stage) vxod.getScene().getWindow();
        stage3.close();//Закрытие предыдущего окна

    }
}
