package fpt.CapstoneSU24.dto;

import java.util.List;

public class ListCertificateDTOResponse {
    private String certificateName;
    private String issuingAuthority;
    private List<String> images;
    private long issuanceDate;

    public ListCertificateDTOResponse(String certificateName, String issuingAuthority, List<String> images, long issuanceDate) {
        this.certificateName = certificateName;
        this.issuingAuthority = issuingAuthority;
        this.images = images;
        this.issuanceDate = issuanceDate;
    }

    public String getCertificateName() {
        return certificateName;
    }

    public void setCertificateName(String certificateName) {
        this.certificateName = certificateName;
    }

    public String getIssuingAuthority() {
        return issuingAuthority;
    }

    public void setIssuingAuthority(String issuingAuthority) {
        this.issuingAuthority = issuingAuthority;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public long getIssuanceDate() {
        return issuanceDate;
    }

    public void setIssuanceDate(long issuanceDate) {
        this.issuanceDate = issuanceDate;
    }
}
