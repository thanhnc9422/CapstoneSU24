package fpt.CapstoneSU24.dto.Certificate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class CreateCertificateRequest {
    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must be less than 100 characters")
    public String name;
    @NotNull(message = "file is not null")
    public List<String> file;
    @NotNull(message = "Issuance date is required")
    public long issuanceDate;
    @NotBlank(message = "Issuance authority is required")
    @Size(max = 100, message = "Issuance authority must be less than 100 characters")
    public String issuanceAuthority;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getFile() {
        return file;
    }

    public long getIssuanceDate() {
        return issuanceDate;
    }

    public String getIssuanceAuthority() {
        return issuanceAuthority;
    }

    public void setIssuanceAuthority(String issuanceAuthority) {
        this.issuanceAuthority = issuanceAuthority;
    }

}
