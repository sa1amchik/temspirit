package com.example.teamkiselevsk;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import javafx.print.PrinterJob;
import javafx.stage.FileChooser;

import static com.example.teamkiselevsk.Konekt.getBazakpk2;

public class raspmatchuser {
        @FXML
        private ResourceBundle resources;

        @FXML
        private URL location;

        @FXML
        private TableColumn<Baza2, String> toornir;

        @FXML
        private TableColumn<Baza2, String> protivnik;

        @FXML
        private TableColumn<Baza2, String> schet;

        @FXML
        private TableColumn<Baza2, Date> data;

        @FXML
        private TableColumn<Baza2, Integer> id;

        @FXML
        private Button Vihod_id;

        @FXML
        private Button exit;


        @FXML
        private TextField filterField;

        @FXML
        private TableView<Baza2> table_users3;

        @FXML
        private TextField txt_mail;

        @FXML
        private TextField txt_login;

        @FXML
        private TextField txt_tip;

        @FXML
        private DatePicker txt_password;


        ObservableList list = FXCollections.observableArrayList();

        @FXML
        private TextField txt_id;


        Connection conn1 = null;
        PreparedStatement pst1 = null;
        ObservableList<Baza2> listBAPA;

        @FXML
        void Exit(ActionEvent event) {
            System.exit(0);
        }

        @FXML
        void Vihod(ActionEvent event) throws IOException {
            FXMLLoader fxmlLoader1 = new FXMLLoader(HelloApplication.class.getResource("mainmenu.fxml"));
            Scene scene = new Scene(fxmlLoader1.load(),771,524);
            Stage stage1 = new Stage();
            stage1.setScene(scene);
            stage1.show();//Открытие нового окна


            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("raspmatchuser.fxml"));
            Stage stage = (Stage) Vihod_id.getScene().getWindow();
            stage.close();//Закрытие предыдущего окна
        }

    @FXML
    void UpdateTable() {
        id.setCellValueFactory(new PropertyValueFactory<Baza2, Integer>("id"));
        toornir.setCellValueFactory(new PropertyValueFactory<Baza2, String>("toornir"));
        protivnik.setCellValueFactory(new PropertyValueFactory<Baza2, String>("protivnik"));
        data.setCellValueFactory(new PropertyValueFactory<Baza2, Date>("data"));
        schet.setCellValueFactory(new PropertyValueFactory<Baza2, String>("schet"));

        listBAPA = getBazakpk2();
        table_users3.setItems(listBAPA);

    }

        @FXML
        void getSelected(MouseEvent event) {
            int index = table_users3.getSelectionModel().getSelectedIndex();
            if (index <= -1){
                return;
            }
            txt_id.setText(id.getCellData(index).toString());
            txt_login.setText(toornir.getCellData(index).toString());
            txt_mail.setText(protivnik.getCellData(index).toString());
            txt_password.setValue(LocalDate.parse(data.getCellData(index).toString()));
            txt_tip.setText(schet.getCellData(index).toString());
        }

        @FXML
        void search_user(ActionEvent event) {
            id.setCellValueFactory(new PropertyValueFactory<Baza2,Integer>("id"));
            toornir.setCellValueFactory(new PropertyValueFactory<Baza2,String>("toornir"));
            protivnik.setCellValueFactory(new PropertyValueFactory<Baza2,String>("protivnik"));
            data.setCellValueFactory(new PropertyValueFactory<Baza2,Date>("data"));
            schet.setCellValueFactory(new PropertyValueFactory<Baza2,String>("schet"));

            listBAPA = getBazakpk2();
            table_users3.setItems(listBAPA);

            FilteredList<Baza2> filteredData = new FilteredList<>(listBAPA, Baza -> true);
            filterField.textProperty().addListener((observable,oldValue,newValue) ->{
                filteredData.setPredicate(person -> {
                    if (newValue == null || newValue.isEmpty()){
                        return true;
                    }

                    String lowerCaseFilter = newValue.toLowerCase();

                    if(String.valueOf(person.getId()).contains(lowerCaseFilter)){
                        return true;
                    }

                    if (person.getToornir().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }

                    else if (person.getProtivnik().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }

                    else if (person.getData().toString().contains(lowerCaseFilter)) {
                        return true;
                    }else if (person.getSchet().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    else  return false;

                });
            });
            SortedList<Baza2> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(table_users3.comparatorProperty());
            table_users3.setItems(sortedData);
        }

        @FXML
        void initialize() {
UpdateTable();

        }

    }


