package com.example.swapper;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import models.Category;
import models.Filter;
import service.navigation.SwitchScreen;

import java.io.IOException;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;

public class FilterController {
    @FXML
    public Button btnBack;
    @FXML
    public Button btnResetFilter;
    @FXML
    public Button btnApplyFilter;
    @FXML
    private DatePicker dpFrom;
    @FXML
    private DatePicker dpTo;
    @FXML
    private ToggleGroup category;

    @FXML
    public void initialize() throws SQLException {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource_bundle");
        btnBack.setText(resourceBundle.getString("back"));
        btnResetFilter.setText(resourceBundle.getString("resetFilter"));
        btnApplyFilter.setText(resourceBundle.getString("applyFilter"));
    }

    @FXML
    private void applyFilters() throws IOException {
        if (dpFrom.getValue() != null) {
            LocalDate localDateFrom = dpFrom.getValue();
            Filter.setDateFrom(Instant.from(localDateFrom.atStartOfDay(ZoneId.systemDefault())));
        }

        if (dpTo.getValue() != null) {
            LocalDate localDateTo = dpTo.getValue();
            Filter.setDateTo(Instant.from(localDateTo.atStartOfDay(ZoneId.systemDefault())));
        }

        if (category.getSelectedToggle() != null) {
            RadioButton selectedRadioButton = (RadioButton) category.getSelectedToggle();
            Filter.setCategory(selectedRadioButton.getText());
        }
        //TODO filtrovat aj podla kategorie aj kategoria+top
        RadioButton selectedRadioButton = (RadioButton) category.getSelectedToggle();
        Category.setNameOfCategory(selectedRadioButton.getText().toLowerCase(Locale.ROOT));
        SwitchScreen.changeScreen("views/categoryPage.fxml");
    }

    @FXML
    private void backToCategory() throws IOException {
        SwitchScreen.changeScreen("views/categoryPage.fxml");
    }

    public void resetFilter() {
        dpFrom.setValue(LocalDate.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault())));
        dpTo.setValue(LocalDate.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault())));
        //TODO reset categorii(toggleGroup)
    }
}
