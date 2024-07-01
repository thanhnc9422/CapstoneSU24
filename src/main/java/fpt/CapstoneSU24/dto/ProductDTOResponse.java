package fpt.CapstoneSU24.dto;

public class ProductDTOResponse {
    private String productName;
    private String description;
    private String avatar;

    public ProductDTOResponse(String productName, String description, String avatar) {
        this.productName = productName;
        this.description = description;
        this.avatar = avatar;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
