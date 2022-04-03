package com.example.swapper;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import service.db.ProductDbServices;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
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

    private void applyFilters() throws IOException, SQLException {
        Instant timeStampFrom = null;
        Instant timeStampTo = null;
        String toogleGroupValue = null;
  
        if (dpFrom.getValue() != null) {
            LocalDate localDateFrom = dpFrom.getValue();
            timeStampFrom = Instant.from(localDateFrom.atStartOfDay(ZoneId.systemDefault()));
            Filter.setDateFrom(Instant.from(localDateFrom.atStartOfDay(ZoneId.systemDefault())));
        }

        if (dpTo.getValue() != null) {
            LocalDate localDateTo = dpTo.getValue();

            timeStampTo = Instant.from(localDateTo.atStartOfDay(ZoneId.systemDefault()));
            Filter.setDateTo(Instant.from(localDateTo.atStartOfDay(ZoneId.systemDefault())));
        }

        if (category.getSelectedToggle() != null) {
            RadioButton selectedRadioButton = (RadioButton) category.getSelectedToggle();
            toogleGroupValue = selectedRadioButton.getText();
            Filter.setCategory(selectedRadioButton.getText());
        }

        ResultSet filteredProductsList = ProductDbServices.getFilteredProducts(timeStampFrom, timeStampTo, toogleGroupValue);
  
        RadioButton selectedRadioButton = (RadioButton) category.getSelectedToggle();
        Category.setNameOfCategory(selectedRadioButton.getText().toLowerCase(Locale.ROOT));
        SwitchScreen.changeScreen("views/categoryPage.fxml");
    }
}
