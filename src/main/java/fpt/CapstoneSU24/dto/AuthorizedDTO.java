package fpt.CapstoneSU24.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthorizedDTO {
    @JsonIgnore
    private int authorizedId;

  //  @NotBlank(message = "Authorized name is required")
    @Size(min = 5, message = "Authorized name must be less than 5 characters")
    @Pattern(regexp = "^[a-zA-Z0-9\\p{L}\\p{M}\\s.-]+$",
            message = "Authorized name must not contain special characters")
    private String authorizedName;

    @NotBlank(message = "Authorized email is required")
    @Email(message = "Authorized email should be valid")
    private String authorizedEmail;

    @JsonIgnore
    //@NotBlank(message = "Assign person is required")
    @Size(min = 5, message = "Assign person must be more  than 5 characters")
    @Pattern(regexp = "^[a-zA-Z0-9\\p{L}\\p{M}\\s.-]+$",
            message = "AAssign person must not contain special characters")
    private String assignPerson;

    @NotBlank(message = "Assign person email is required")
    @Email(message = "Assign person email should be valid")
    private String assignPersonMail;

    private LocationDTO location;

    // @NotBlank(message = "Description is required")
    @Size(max = 50, message = "Description must be more  than 50 characters")
    private String description;

    //@NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^(\\+?[0-9]{10,15})?$", message = "Phone number should be valid")
    private String phoneNumber;

  @NotBlank(message = "Product recognition is required")
    @Size(max = 10, message = "ProductRecognition must be 10 characters")
    private String productRecognition;

    private String OTP;



}
