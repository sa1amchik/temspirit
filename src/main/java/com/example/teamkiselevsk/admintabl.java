package com.example.teamkiselevsk;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.print.PrinterJob;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;

import static com.example.teamkiselevsk.Konekt.getBazakpk;

public class admintabl {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Baza, String> login;

    @FXML
    private TableColumn<Baza, String> email;

    @FXML
    private TableColumn<Baza, String> password;

    @FXML
    private TableColumn<Baza, String> tip;

    @FXML
    private TableColumn<Baza, Integer> id;

    @FXML
    private Button Vihod_id;

    @FXML
    private Button exit;

    @FXML
    private TextField filterField;

    @FXML
    private TableView<Baza> table_users1;

    @FXML
    private TextField txt_mail;

    @FXML
    private TextField txt_login;

    @FXML
    private TextField txt_tip;

    @FXML
    private TextField txt_password;


    ObservableList list = FXCollections.observableArrayList();

    @FXML
    private TextField txt_id;


    Connection conn1 = null;
    PreparedStatement pst1 = null;
    ObservableList<Baza> listBAPA;

    @FXML
    private Button export;
    @FXML
    private Button print;
    @FXML
    private Button save;

    @FXML
    void Export(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("SQL Files", "*.sql"));
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            try (FileWriter writer = new FileWriter(file)) {


                conn1 = Konekt.ConnectDb();
                Statement statement = conn1.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM user"); // Измените на ваши данные

                // Записываем заголовки
                writer.append("Id|Логин|Почта|Пароль|Тип\n\n"); // Замените названия столбцов на ваши

                // Записываем данные
                while (resultSet.next()) {
                    writer.append(resultSet.getString("Id"));
                    writer.append("|");
                    writer.append(resultSet.getString("login"));
                    writer.append("|");
                    writer.append(resultSet.getString("email"));
                    writer.append("|");
                    writer.append(resultSet.getString("password"));
                    writer.append("|");
                    writer.append(resultSet.getString("tip"));
                    writer.append("\n");
                }
            } catch (IOException | SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    @FXML
    void Print(ActionEvent event) {
        PrinterJob job = PrinterJob.createPrinterJob();

        if (job != null && job.showPrintDialog(table_users1.getScene().getWindow())) {
            boolean success = job.printPage(table_users1);
            if (success) {
                job.endJob();
            }
        }
    }
    @FXML
    void Save(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Сохранить таблицу");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            try (FileWriter writer = new FileWriter(file)) {
                // Запись заголовков
                for (TableColumn<Baza, ?> column : table_users1.getColumns()) {
                    writer.append(column.getText()).append("|");
                }
                writer.append("\n");
                writer.append("\n");

                ObservableList<Baza> data = table_users1.getItems();
                for (Baza item : data) {

                    // Предполагается, что MyData имеет методы для получения значений полей
                    writer.append(String.valueOf(item.getId())).append("|");
                    writer.append(item.getLogin()).append("|");
                    writer.append(item.getEmail()).append("|");
                    writer.append(item.getPassword()).append("|");
                    writer.append(item.getTip()).append("|");
                    writer.append("\n");

                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void Add_users(ActionEvent event) {
        conn1 = Konekt.ConnectDb();

        String sql = "insert into user (login, email, password, tip)values(?,?,?,?)";
        try {
            assert conn1 != null;
            pst1 = conn1.prepareStatement(sql);
            pst1.setString(1, txt_login.getText());
            pst1.setString(2, txt_mail.getText());
            pst1.setString(3, txt_password.getText());
            pst1.setString(4, txt_tip.getText());
            pst1.execute();

            UpdateTable();

        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @FXML
    void Delete(ActionEvent event) {
        conn1 = Konekt.ConnectDb();

        String sql = "delete from user where id = ?";

        try {
            assert conn1 != null;
            pst1 = conn1.prepareStatement(sql);
            pst1.setString(1, txt_id.getText());
            pst1.execute();


            UpdateTable();

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @FXML
    void Edit(ActionEvent event) {
        try{
            conn1 = Konekt.ConnectDb();
            String value1 = txt_id.getText();
            String value2 = txt_login.getText();
            String value3 = txt_mail.getText();
            String value4 = txt_password.getText();
            String value5 = txt_tip.getText();
            String sql = "update user set id = '"+value1+"', login = '"+value2+"', email = '"+value3+"',  password = '"+value4+"', tip = '"+value5+"', where id = '"+value1+"' ";
            pst1 = conn1.prepareStatement(sql);
            pst1.execute();
            UpdateTable();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }

    @FXML
    void Exit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void UpdateTable() {
        id.setCellValueFactory(new PropertyValueFactory<Baza,Integer>("id"));
        login.setCellValueFactory(new PropertyValueFactory<Baza,String>("login"));
        email.setCellValueFactory(new PropertyValueFactory<Baza,String>("email"));
        password.setCellValueFactory(new PropertyValueFactory<Baza,String>("password"));
        tip.setCellValueFactory(new PropertyValueFactory<Baza,String>("tip"));

        listBAPA = getBazakpk();
        table_users1.setItems(listBAPA);

    }

    @FXML
    void Vihod(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader1 = new FXMLLoader(HelloApplication.class.getResource("adminmenu.fxml"));
        Scene scene = new Scene(fxmlLoader1.load(),600,400);
        Stage stage1 = new Stage();
        stage1.initStyle(StageStyle.UNDECORATED);
        stage1.setScene(scene);
        stage1.show();//Открытие нового окна


        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("admintable.fxml"));
        Stage stage = (Stage) Vihod_id.getScene().getWindow();
        stage.close();//Закрытие предыдущего окна
    }

    @FXML
    void getSelected(MouseEvent event) {
        int index = table_users1.getSelectionModel().getSelectedIndex();
        if (index <= -1){
            return;
        }
        txt_id.setText(id.getCellData(index).toString());
        txt_login.setText(login.getCellData(index).toString());
        txt_mail.setText(email.getCellData(index).toString());
        txt_password.setText(password.getCellData(index).toString());
        txt_tip.setText(tip.getCellData(index).toString());
    }

    @FXML
    void search_user(ActionEvent event) {
        id.setCellValueFactory(new PropertyValueFactory<Baza,Integer>("id"));
        login.setCellValueFactory(new PropertyValueFactory<Baza,String>("login"));
        email.setCellValueFactory(new PropertyValueFactory<Baza,String>("email"));
        password.setCellValueFactory(new PropertyValueFactory<Baza,String>("password"));
        tip.setCellValueFactory(new PropertyValueFactory<Baza,String>("tip"));

        listBAPA = getBazakpk();
        table_users1.setItems(listBAPA);

        FilteredList<Baza> filteredData = new FilteredList<>(listBAPA, Baza -> true);
        filterField.textProperty().addListener((observable,oldValue,newValue) ->{
            filteredData.setPredicate(person -> {
                if (newValue == null || newValue.isEmpty()){
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if(String.valueOf(person.getId()).contains(lowerCaseFilter)){
                    return true;
                }

                if (person.getLogin().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }

                else if (person.getEmail().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }

                else if (person.getPassword().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }else if (person.getTip().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                else  return false;

            });
        });
        SortedList<Baza> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table_users1.comparatorProperty());
        table_users1.setItems(sortedData);
    }

    @FXML
    void initialize() {

        UpdateTable();


    }

}

