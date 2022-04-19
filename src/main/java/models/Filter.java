package models;

import java.time.Instant;

public class Filter {
    private static Filter instance = null;
    private static Instant dateFrom = null;
    private static Instant dateTo = null;
    private static String category = null;
    private static String searchInput = null;
    private static boolean isTop = false;

    public static Filter getInstance() {
        if (instance == null) {
            instance = new Filter();
        }
        return instance;
    }

    public static Instant getDateFrom() {
        return dateFrom;
    }

    public static void setDateFrom(Instant dateFrom) {
        Filter.dateFrom = dateFrom;
    }

    public static Instant getDateTo() {
        return dateTo;
    }

    public static void setDateTo(Instant dateTo) {
        Filter.dateTo = dateTo;
    }

    public static String getCategory() {
        return category;
    }

    public static void setCategory(String category) {
        Filter.category = category;
    }

    public static void setSearchInput(String searchInput) {
        Filter.searchInput = searchInput;
    }

    public static String getSearchInput() {
        return Filter.searchInput;
    }
    public static boolean getIsTop() {
        return isTop;
    }

    public static void setIsTop(boolean isTop) {
        Filter.isTop = isTop;
    }

    public static void resetFilterValues() {
        dateFrom = null;
        dateTo = null;
        category = null;
        searchInput = null;
        isTop = false;
    }

}
