package service.navigation;

import com.example.swapper.HelloApplication;
import javafx.fxml.FXML;

import java.io.IOException;

/**
 * Service that provides switching view
 */
public class SwitchScreen {
    /**
     * Method for screen change
     *
     * @param path Path to .fxml file we to to switch to
     * @throws IOException Exception when .fxml file not found
     */
    @FXML
    public static void newScreen(String path) throws IOException {
        HelloApplication main = new HelloApplication();
        main.changeScene(path);
    }
}
