package date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public final class Dates {
    private Dates(){};
    public static LocalDate getDateByYear(String year) {

        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy")
                .parseDefaulting(ChronoField.MONTH_OF_YEAR,1)
                .parseDefaulting(ChronoField.DAY_OF_MONTH,1)
                .toFormatter();

        return LocalDate.parse(year, formatter);
    }
    
    public static boolean isCurrentYear(LocalDate createdDate, LocalDate currentYear) {
        LocalDate nextYear = currentYear.plusYears(1);
        return createdDate.isEqual(currentYear) || (createdDate.isBefore(nextYear) && createdDate.isAfter(currentYear));
    }
}
