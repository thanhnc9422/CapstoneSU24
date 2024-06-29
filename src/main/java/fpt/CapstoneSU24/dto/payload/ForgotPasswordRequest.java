package fpt.CapstoneSU24.dto.payload;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class ForgotPasswordRequest {
    @NotBlank(message = "The email is required")
    private String email;

    public String getEmail() {
        return email;
    }
}
