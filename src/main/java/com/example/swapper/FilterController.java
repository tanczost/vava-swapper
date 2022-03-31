package com.example.swapper;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class FilterController {
    @FXML
    private TextField tfMin;

    @FXML
    private TextField tfMax;

    @FXML
    private void applyFilters() throws Exception {
        System.out.println(tfMin.getText());
        System.out.println(tfMax.getText());
    }
}
