package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import models.Filter;
import observer.Observer;
import observer.Subject;
import service.navigation.SwitchScreen;

import java.io.IOException;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;

public class FilterController extends Subject {
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
        this.attach(SwapperApplication.getLogManager());
        this.notifyObserver("Filters for products page loaded.", Observer.LEVEL.info);

        resetFilter();
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
        if (top.getSelectedToggle() != null) {
            filter.setIsTop(true);
        }

        SwitchScreen.changeScreen("views/CategoryPage.fxml");

        this.notifyObserver("Filters were applied.", Observer.LEVEL.info);
    }

    @FXML
    private void backToCategory() throws IOException {
        SwitchScreen.changeScreen("views/CategoryPage.fxml");
    }

    public void resetFilter() {
        filter.resetFilterValues();
        dpFrom.setValue(LocalDate.from(LocalDate.now().minusMonths(1)));
        dpTo.setValue(LocalDate.from(LocalDate.now().plusMonths(1)));
        category.selectToggle(null);
        top.selectToggle(null);

        this.notifyObserver("Filters were cleared.", Observer.LEVEL.info);
    }
}
