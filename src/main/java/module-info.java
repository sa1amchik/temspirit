module com.example.teamkiselevsk {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;


    opens com.example.teamkiselevsk to javafx.fxml;
    exports com.example.teamkiselevsk;
}