package com.example.swapper;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import service.PostgresConnection;

import java.sql.*;

public class HelloController {
    @FXML
    private Label welcomeText;
    private Connection connection = PostgresConnection.initializePostgresqlDatabase();

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText(getNameFromDB("Tomi"));

    }

    private String getNameFromDB(String name){
        try {
            System.out.println("start");
            PreparedStatement selectStatement = connection.prepareStatement(
                    "SELECT  * FROM friends WHERE name LIKE (?)");
            selectStatement.setString(1, name);


            ResultSet sqlReturnValues = selectStatement.executeQuery();

            sqlReturnValues.next();


            return sqlReturnValues.getString(2);

        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return "Not found";
        }
    }
}