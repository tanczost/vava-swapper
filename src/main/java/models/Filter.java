package models;

import java.time.Instant;

public class Filter {
    private static Instant dateFrom;
    private static Instant dateTo;
    private static String category;

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
}
