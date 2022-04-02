package models;

import java.time.Instant;
import java.time.LocalDate;

public class Filter {
    private static Instant dateFrom = Instant.from(LocalDate.of(2022, 1, 1));
    private static Instant dateTo = Instant.from(LocalDate.now());
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
