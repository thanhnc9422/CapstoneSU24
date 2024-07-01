package fpt.CapstoneSU24.dto.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

public class UpdateStatusUserRequest {
    @NotNull(message = "The id is required")
    private int id;
    @NotNull(message = "The status is required")
    private int status;

    public int getId() {
        return id;
    }

    public int getStatus() {
        return status;
    }
}
