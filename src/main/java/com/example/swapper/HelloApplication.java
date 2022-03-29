package com.example.swapper;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Singleton;
import service.PostgresConnection;

import service.FileHandler;
import service.db.ProductDbServices;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;

public class HelloApplication extends Application {
    private static Stage stage;
    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("views/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1024, 576);
        primaryStage.setTitle("Swapper!");
        primaryStage.setScene(scene);
        scene.getRoot().setStyle("-fx-font-family: 'serif'");
        primaryStage.show();
        PostgresConnection.initializePostgresqlDatabase();
    }

    public void changeScene(String fxml) throws IOException {
        Parent NextScene = FXMLLoader.load(getClass().getResource(fxml));
        stage.getScene().setRoot(NextScene);
    }

    public static void main(String[] args) {
        launch();
    }
}