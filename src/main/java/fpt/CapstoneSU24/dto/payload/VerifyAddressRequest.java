package fpt.CapstoneSU24.dto.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class VerifyAddressRequest {

    @NotBlank(message = "The address is required")
    private String address;

    public String getAddress() {
        return address;
    }
}
