package models;

import java.time.Instant;

public class Filter {
    private static Filter instance = null;
    private  Instant dateFrom = null;
    private  Instant dateTo = null;
    private  String category = null;
    private  String searchInput = null;
    private  boolean isTop = false;

    public static Filter getInstance() {
        if (instance == null) {
            instance = new Filter();
        }
        return instance;
    }

    public Instant getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Instant dateFrom) {
        instance.dateFrom = dateFrom;
    }

    public Instant getDateTo() {
        return dateTo;
    }

    public void setDateTo(Instant dateTo) {
        instance.dateTo = dateTo;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        instance.category = category;
    }

    public void setSearchInput(String searchInput) {
        instance.searchInput = searchInput;
    }

    public String getSearchInput() {
        return instance.searchInput;
    }
    public boolean getIsTop() {
        return isTop;
    }

    public void setIsTop(boolean isTop) {
        instance.isTop = isTop;
    }

    public void resetFilterValues() {
        dateFrom = null;
        dateTo = null;
        category = null;
        searchInput = null;
        isTop = false;
    }

}
