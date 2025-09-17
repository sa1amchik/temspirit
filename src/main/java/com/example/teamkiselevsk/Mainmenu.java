package com.example.teamkiselevsk;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Mainmenu {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button info;

    @FXML
    private Button raspmath;

    @FXML
    private Button sostav;



    @FXML
    void initialize() {
        assert info != null : "fx:id=\"info\" was not injected: check your FXML file 'mainmenu.fxml'.";
        assert raspmath != null : "fx:id=\"raspmath\" was not injected: check your FXML file 'mainmenu.fxml'.";
        assert sostav != null : "fx:id=\"sostav\" was not injected: check your FXML file 'mainmenu.fxml'.";

    }

    public void setRaspmath(javafx.event.ActionEvent actionEvent) throws IOException {

            FXMLLoader fxmlLoader1 = new FXMLLoader(HelloApplication.class.getResource("raspmatchuser.fxml"));
            Scene scene = new Scene(fxmlLoader1.load(), 1084, 591);
            Stage stage1 = new Stage();
            stage1.setScene(scene);
            stage1.show();//Открытие нового окна


            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("mainmenu.fxml"));
            Stage stage = (Stage) raspmath.getScene().getWindow();
            stage.close();//Закрытие предыдущего окна
        }

    public void setInfo(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader3 = new FXMLLoader(HelloApplication.class.getResource("info.fxml"));
        Scene scene = new Scene(fxmlLoader3.load(), 787, 595);
        Stage stage1 = new Stage();
        stage1.setScene(scene);
        stage1.show();//Открытие нового окна


        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("mainmenu.fxml"));
        Stage stage = (Stage) info.getScene().getWindow();
        stage.close();//Закрытие предыдущего окна
    }

    public void setSostav(javafx.event.ActionEvent actionEvent) throws IOException {
            FXMLLoader fxmlLoader2 = new FXMLLoader(HelloApplication.class.getResource("sostav.fxml"));
            Scene scene = new Scene(fxmlLoader2.load(), 1018, 533);
            Stage stage1 = new Stage();
            stage1.setScene(scene);
            stage1.show();//Открытие нового окна


            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("mainmenu.fxml"));
            Stage stage = (Stage) sostav.getScene().getWindow();
            stage.close();//Закрытие предыдущего окна
    }
}

