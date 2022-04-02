package com.example.swapper;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class FilterController {
    @FXML
    private DatePicker dpFrom;

    @FXML
    private DatePicker dpTo;

    @FXML
    ToggleGroup category;

    @FXML
    private void applyFilters() {
        // TODO connect to database
        if (dpFrom.getValue() != null) {
            LocalDate localDateFrom = dpFrom.getValue();
            Instant timeStampFrom = Instant.from(localDateFrom.atStartOfDay(ZoneId.systemDefault()));
            System.out.println("From: " + timeStampFrom);
        }

        if (dpTo.getValue() != null) {
            LocalDate localDateTo = dpTo.getValue();
            Instant timeStampTo = Instant.from(localDateTo.atStartOfDay(ZoneId.systemDefault()));
            System.out.println("To: " + timeStampTo);
        }

        if (category.getSelectedToggle() != null) {
            RadioButton selectedRadioButton = (RadioButton) category.getSelectedToggle();
            String toogleGroupValue = selectedRadioButton.getText();
            System.out.println(toogleGroupValue);
        }

    }
}
