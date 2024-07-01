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
    private String firstName;
    @NotBlank(message = "The lastName is required")
    private String lastName;
    @NotBlank(message = "The address is required")
    private String address;
    @NotBlank(message = "The city is required")
    private String city;
    @NotBlank(message = "The country is required")
    private String country;
    @NotBlank(message = "The ward is required")
    private String ward;
    @NotBlank(message = "The district is required")
    private String district;
    @NotNull(message = "The coordinateX is required")
    private double coordinateX;
    @NotNull(message = "The coordinateY is required")
    private double coordinateY;
    @Pattern(regexp = "\\d{10}", message = "Invalid phone number format")
    @NotBlank(message = "The phone is required")
    private String phone;

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

    public double getCoordinateX() {
        return coordinateX;
    }

    public double getCoordinateY() {
        return coordinateY;
    }

    public String getPhone() {
        return phone;
    }
}
