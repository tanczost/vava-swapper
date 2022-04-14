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
    private ToggleGroup top;
    private Filter filter = Filter.getInstance();

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
            filter.setDateFrom(Instant.from(localDateFrom.atStartOfDay(ZoneId.systemDefault())));
        }

        if (dpTo.getValue() != null) {
            LocalDate localDateTo = dpTo.getValue();
            filter.setDateTo(Instant.from(localDateTo.atStartOfDay(ZoneId.systemDefault())));
        }

        if (category.getSelectedToggle() != null) {
            RadioButton selectedRadioButton = (RadioButton) category.getSelectedToggle();
            filter.setCategory(selectedRadioButton.getText());
        }
        //TODO prepojit na BE ked je kategoria a TOP
        if (category.getSelectedToggle() != null && top.getSelectedToggle() != null) {
            System.out.println(category.getSelectedToggle());
            System.out.println(top.getSelectedToggle());
        }
        RadioButton selectedRadioButton = (RadioButton) category.getSelectedToggle();
        Category.getInstance().setNameOfCategory(selectedRadioButton.getText().toLowerCase(Locale.ROOT));
        SwitchScreen.changeScreen("views/categoryPage.fxml");
    }

    @FXML
    private void backToCategory() throws IOException {
        SwitchScreen.changeScreen("views/categoryPage.fxml");
    }

    public void resetFilter() {
        dpFrom.setValue(LocalDate.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault())));
        dpTo.setValue(LocalDate.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault())));
        category.selectToggle(null);
        top.selectToggle(null);
    }
}
