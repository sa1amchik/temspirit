package com.example.teamkiselevsk;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class adminmenu {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button match;

    @FXML
    private Button pol;


    @FXML
    void initialize() {


    }

    public void setPol(javafx.event.ActionEvent actionEvent) throws IOException {

            FXMLLoader fxmlLoader1 = new FXMLLoader(HelloApplication.class.getResource("admintable.fxml"));
            Scene scene = new Scene(fxmlLoader1.load(), 1123, 591);
            Stage stage1 = new Stage();
            stage1.setScene(scene);
            stage1.show();//Открытие нового окна


            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("adminmenu.fxml"));
            Stage stage = (Stage) pol.getScene().getWindow();
            stage.close();//Закрытие предыдущего окна
        }


    public void setMatch(javafx.event.ActionEvent actionEvent) throws  IOException{
            FXMLLoader fxmlLoader2 = new FXMLLoader(HelloApplication.class.getResource("raspmatch.fxml"));
            Scene scene = new Scene(fxmlLoader2.load(), 1152, 591);
            Stage stage1 = new Stage();
            stage1.setScene(scene);
            stage1.show();//Открытие нового окна


            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("adminmenu.fxml"));
            Stage stage = (Stage) match.getScene().getWindow();
            stage.close();//Закрытие предыдущего окна
        }


    public void back(ActionEvent actionEvent) throws IOException {

        FXMLLoader fxmlLoader2 = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader2.load(), 600, 400);
        Stage stage1 = new Stage();
        stage1.setScene(scene);
        stage1.show();//Открытие нового окна


        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("adminmenu.fxml"));
        Stage stage = (Stage) match.getScene().getWindow();
        stage.close();//Закрытие предыдущего окна
    }
}
