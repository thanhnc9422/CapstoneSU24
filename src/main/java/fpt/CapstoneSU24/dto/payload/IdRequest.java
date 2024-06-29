package fpt.CapstoneSU24.dto.payload;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class IdRequest {
    @Digits(integer = 5, fraction = 0, message = "Invalid digit format")
    @NotNull(message = "The id is required")
    @Min(value = 1, message = "The id must be a positive number")
    private int id;

    public int getId() {
        return id;
    }
}
