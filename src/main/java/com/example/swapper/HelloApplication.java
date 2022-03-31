package com.example.swapper;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import service.PostgresConnection;

import java.io.IOException;
import java.sql.SQLException;

public class HelloApplication extends Application {
    public static Stage stage;

    @Override
    public void start(Stage primaryStage) throws IOException, SQLException {
        PostgresConnection.initializePostgresqlDatabase();
        stage = primaryStage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("views/filter.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1024, 576);
        primaryStage.setTitle("Swapper!");
        primaryStage.setScene(scene);
        scene.getRoot().setStyle("-fx-font-family: 'serif'");
        primaryStage.show();
    }

    public void changeScene(String fxml) throws IOException {
        Parent NextScene = FXMLLoader.load(getClass().getResource(fxml));
        stage.getScene().setRoot(NextScene);
    }

    public static void main(String[] args) {
        launch();
    }
}