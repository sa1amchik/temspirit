package com.example.teamkiselevsk;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class Sostav {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button nazad;


    @FXML
    void initialize() {

    }

    public void back(javafx.event.ActionEvent actionEvent) throws IOException {

            FXMLLoader fxmlLoader1 = new FXMLLoader(HelloApplication.class.getResource("mainmenu.fxml"));
            Scene scene = new Scene(fxmlLoader1.load(), 771, 524);
            Stage stage1 = new Stage();
            stage1.setScene(scene);
            stage1.show();//Открытие нового окна


            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("sostav.fxml"));
            Stage stage = (Stage) nazad.getScene().getWindow();
            stage.close();//Закрытие предыдущего окна

    }
}
