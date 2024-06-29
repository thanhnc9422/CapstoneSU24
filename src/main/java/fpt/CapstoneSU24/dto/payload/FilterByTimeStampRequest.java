package fpt.CapstoneSU24.dto.payload;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class FilterByTimeStampRequest {
    @Digits(integer = 10, fraction = 0, message = "Invalid digit format")
    @Pattern(regexp = "^(\\d{10}|\\d)$", message = "Invalid digit format")
    @NotNull(message = "The startDate is required")
    private long startDate;
    @Digits(integer = 10, fraction = 0, message = "Invalid digit format")
    @Pattern(regexp = "^(\\d{10}|\\d)$", message = "Invalid digit format")
    @NotNull(message = "The endDate is required")
    private long endDate;

    public long getStartDate() {
        return startDate;
    }

    public long getEndDate() {
        return endDate;
    }

    public boolean isValidDates() {
        return startDate < endDate;
    }
}
