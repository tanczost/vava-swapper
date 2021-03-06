package com.example.swapper;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.common.LogManager;
import services.db.PostgresConnection;

import java.io.IOException;
import java.util.Locale;

public class SwapperApplication extends Application {
    public static Stage stage;
    //needed for initialization
    private static LogManager logManager = LogManager.getInstance();

    @Override
    public void start(Stage primaryStage) throws IOException {
        PostgresConnection.initializePostgresqlDatabase();
        Locale.setDefault(new Locale("en", "EN"));
        stage = primaryStage;
        FXMLLoader fxmlLoader = new FXMLLoader(SwapperApplication.class.getResource("views/LandingPage.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 1024, 576);
        primaryStage.setTitle("Swapper!");
        primaryStage.setScene(scene);
        scene.getRoot().setStyle("-fx-font-family: 'serif'");
        primaryStage.show();
    }

    public void changeScene(String fxml) throws IOException {
        Parent NextScene = FXMLLoader.load(getClass().getResource(fxml));
        NextScene.setStyle("-fx-font-family: 'serif'");
        stage.getScene().setRoot(NextScene);
    }

    public static LogManager getLogManager(){
        return  logManager;
    }

    public static void main(String[] args) {
        launch();
    }
}