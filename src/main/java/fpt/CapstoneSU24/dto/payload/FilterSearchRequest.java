package fpt.CapstoneSU24.dto.payload;

import jakarta.validation.constraints.*;

public class FilterSearchRequest {
    @Digits(integer = 5, fraction = 0, message = "Invalid digit format")
    @NotNull(message = "The pageSize is required")
    private int pageSize;
    @Digits(integer = 5, fraction = 0, message = "Invalid digit format")
    @NotNull(message = "The pageNumber is required")
    private int pageNumber;
    @Digits(integer = 10, fraction = 0, message = "Invalid digit format")
    @NotNull(message = "The startDate is required")
    private long startDate;
    @Digits(integer = 10, fraction = 0, message = "Invalid digit format")
    @NotNull(message = "The endDate is required")
    private long endDate;
    @NotNull(message = "The name is not null")
    private String name;
    @NotNull(message = "The type is not null")
    private String type;

    public int getPageSize() {
        return pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public long getStartDate() {
        return startDate;
    }

    public long getEndDate() {
        return endDate;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
