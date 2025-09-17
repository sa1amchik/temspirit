package com.example.teamkiselevsk;

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

import java.io.File;
import java.io.FileWriter;
import java.sql.*;
import java.time.LocalDate;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import static com.example.teamkiselevsk.Konekt.getBazakpk2;

public class Raspmatch {
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
    private TableView<Baza2> table_users5;

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
                ResultSet resultSet = statement.executeQuery("SELECT * FROM raspmatch"); // Измените на ваши данные

                // Записываем заголовки
                writer.append("Id|Турнир|Противник|Дата|Счёт\n\n"); // Замените названия столбцов на ваши

                // Записываем данные
                while (resultSet.next()) {
                    writer.append(resultSet.getString("Id"));
                    writer.append("|");
                    writer.append(resultSet.getString("toornir"));
                    writer.append("|");
                    writer.append(resultSet.getString("protivnik"));
                    writer.append("|");
                    writer.append(resultSet.getString("data"));
                    writer.append("|");
                    writer.append(resultSet.getString("schet"));
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

        if (job != null && job.showPrintDialog(table_users5.getScene().getWindow())) {
            boolean success = job.printPage(table_users5);
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
                for (TableColumn<Baza2, ?> column : table_users5.getColumns()) {
                    writer.append(column.getText()).append("|");
                }
                writer.append("\n");
                writer.append("\n");

                ObservableList<Baza2> data = table_users5.getItems();
                for (Baza2 item : data) {

                    // Предполагается, что MyData имеет методы для получения значений полей
                    writer.append(String.valueOf(item.getId())).append("|");
                    writer.append(item.getToornir()).append("|");
                    writer.append(item.getProtivnik()).append("|");
                    writer.append((CharSequence) item.getData()).append("|");
                    writer.append(item.getSchet()).append("|");
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

        String sql = "insert into raspmatch (toornir, protivnik, data, schet)values(?,?,?,?)";
        try {
            assert conn1 != null;
            pst1 = conn1.prepareStatement(sql);
            pst1.setString(1, txt_login.getText());
            pst1.setString(2, txt_mail.getText());
            pst1.setDate(3, java.sql.Date.valueOf(txt_password.getValue()));
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

        String sql = "delete from raspmatch where id = ?";

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
            String value4 = String.valueOf(txt_password.getValue());
            String value5 = txt_tip.getText();
            String sql = "update raspmatch set id = '"+value1+"', toornir = '"+value2+"', raspmatch = '"+value3+"',  data = '"+value4+"', schet = '"+value5+"', where id = '"+value1+"' ";
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
        id.setCellValueFactory(new PropertyValueFactory<Baza2, Integer>("id"));
        toornir.setCellValueFactory(new PropertyValueFactory<Baza2, String>("toornir"));
        protivnik.setCellValueFactory(new PropertyValueFactory<Baza2, String>("protivnik"));
        data.setCellValueFactory(new PropertyValueFactory<Baza2, Date>("data"));
        schet.setCellValueFactory(new PropertyValueFactory<Baza2, String>("schet"));

        listBAPA = getBazakpk2();
        table_users5.setItems(listBAPA);

    }

    @FXML
    void Vihod(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader1 = new FXMLLoader(HelloApplication.class.getResource("adminmenu.fxml"));
        Scene scene = new Scene(fxmlLoader1.load(),600,400);
        Stage stage1 = new Stage();
        stage1.setScene(scene);
        stage1.show();//Открытие нового окна


        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("raspmatch.fxml"));
        Stage stage = (Stage) Vihod_id.getScene().getWindow();
        stage.close();//Закрытие предыдущего окна
    }

    @FXML
    void getSelected(MouseEvent event) {
        int index = table_users5.getSelectionModel().getSelectedIndex();
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
        table_users5.setItems(listBAPA);

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
        sortedData.comparatorProperty().bind(table_users5.comparatorProperty());
        table_users5.setItems(sortedData);
    }

    @FXML
    void initialize() {

        UpdateTable();


    }

}

