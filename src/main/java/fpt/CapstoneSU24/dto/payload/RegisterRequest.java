package fpt.CapstoneSU24.dto.payload;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
public class RegisterRequest {

    @NotBlank(message = "The email is required")
    @Email(message = "The email is not a valid email")
    private String email;
    @NotBlank(message = "The password is required")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!*()]).{8,}$", message = "Password must be 8 characters long and combination of uppercase letters, lowercase letters, numbers, special characters.")
    private String password;
    @NotBlank(message = "The firstName is required")
    @Pattern(regexp = "^[a-zA-Z\\p{L}\\p{M}\\s.-]+$",
            message = "firstName must not contain special characters")
    private String firstName;
    @Pattern(regexp = "^[a-zA-Z\\p{L}\\p{M}\\s.-]+$",
            message = "lastName must not contain special characters")
    @NotBlank(message = "The lastName is required")
    private String lastName;
    @NotBlank(message = "The address is required")
    private String address;
    @Pattern(regexp = "^[a-zA-Z0-9\\p{L}\\p{M}\\s.-]+$",
            message = "city must not contain special characters")
    @NotBlank(message = "The city is required")
    private String city;
    @Pattern(regexp = "^[a-zA-Z0-9\\p{L}\\p{M}\\s.-]+$",
            message = "country must not contain special characters")
    @NotBlank(message = "The country is required")
    private String country;
    @Pattern(regexp = "^[a-zA-Z0-9\\p{L}\\p{M}\\s.-]+$",
            message = "ward must not contain special characters")
    @NotBlank(message = "The ward is required")
    private String ward;
    @Pattern(regexp = "^[a-zA-Z0-9\\p{L}\\p{M}\\s.-]+$",
            message = "district must not contain special characters")
    @NotBlank(message = "The district is required")
    private String district;
    @NotNull(message = "The coordinateX is required")
    private Double coordinateX;
    @NotNull(message = "The coordinateY is required")
    private Double coordinateY;
    @Pattern(regexp = "\\d{10}", message = "Invalid phone number format")
    @NotBlank(message = "The phone is required")
    private String phone;
    @NotNull(message = "The orgName is required")
    private String orgName;
    @Pattern(regexp = "\\d{6}", message = "Invalid otpVerify number format")
    @NotBlank(message = "The otp is required")
    private String otpVerify;

    public String getOtpVerify() {
        return otpVerify;
    }
    public String getOrgName() {
        return orgName;
    }
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getWard() {
        return ward;
    }

    public String getDistrict() {
        return district;
    }

    public Double getCoordinateX() {
        return coordinateX;
    }

    public Double getCoordinateY() {
        return coordinateY;
    }

    public String getPhone() {
        return phone;
    }
}
