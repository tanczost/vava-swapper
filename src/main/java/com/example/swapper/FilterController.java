package com.example.swapper;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import models.Category;
import models.Filter;
import service.navigation.SwitchScreen;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Locale;

public class FilterController {
    @FXML
    private DatePicker dpFrom;

    @FXML
    private DatePicker dpTo;

    @FXML
    ToggleGroup category;

    @FXML
    private void applyFilters() throws IOException {
        // TODO connect to database
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

        RadioButton selectedRadioButton = (RadioButton) category.getSelectedToggle();
        Category.setNameOfCategory(selectedRadioButton.getText().toLowerCase(Locale.ROOT));
        SwitchScreen.changeScreen("views/categoryPage.fxml");

    }
}
