package com.example.swapper;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import service.db.ProductDbServices;

import java.sql.ResultSet;
import java.sql.SQLException;
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
    private void applyFilters() throws SQLException {
        Instant timeStampFrom = null;
        Instant timeStampTo = null;
        String toogleGroupValue = null;

        // TODO connect to database
        if (dpFrom.getValue() != null) {
            LocalDate localDateFrom = dpFrom.getValue();
            timeStampFrom = Instant.from(localDateFrom.atStartOfDay(ZoneId.systemDefault()));
            System.out.println("From: " + timeStampFrom);
        }

        if (dpTo.getValue() != null) {
            LocalDate localDateTo = dpTo.getValue();
            timeStampTo = Instant.from(localDateTo.atStartOfDay(ZoneId.systemDefault()));
            System.out.println("To: " + timeStampTo);
        }

        if (category.getSelectedToggle() != null) {
            RadioButton selectedRadioButton = (RadioButton) category.getSelectedToggle();
            toogleGroupValue = selectedRadioButton.getText();
        }

        ResultSet filteredProductsList = ProductDbServices.getFilteredProducts(timeStampFrom, timeStampTo, toogleGroupValue);
    }
}
