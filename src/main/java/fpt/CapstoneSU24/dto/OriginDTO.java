package fpt.CapstoneSU24.dto;

public class OriginDTO {
    private String ProductName;
    private String ProductRecognition;
    private String OrgName;
    private String Phone;
    private String FullName;
    private long CreateAt;
    private String DescriptionProduct;
    private String DescriptionOrigin;
     private int Warranty;
 private double CoordinateX;
 private double CoordinateY;

    public double getCoordinateX() {
        return CoordinateX;
    }

    public void setCoordinateX(double coordinateX) {
        CoordinateX = coordinateX;
    }

    public double getCoordinateY() {
        return CoordinateY;
    }

    public void setCoordinateY(double coordinateY) {
        CoordinateY = coordinateY;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private String address;
     private String image;

    public OriginDTO() {
    }



    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductRecognition() {
        return ProductRecognition;
    }

    public void setProductRecognition(String productRecognition) {
        ProductRecognition = productRecognition;
    }

    public String getOrgName() {
        return OrgName;
    }

    public void setOrgName(String orgName) {
        OrgName = orgName;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public long getCreateAt() {
        return CreateAt;
    }

    public void setCreateAt(long createAt) {
        CreateAt = createAt;
    }

    public String getDescriptionProduct() {
        return DescriptionProduct;
    }

    public void setDescriptionProduct(String descriptionProduct) {
        DescriptionProduct = descriptionProduct;
    }

    public String getDescriptionOrigin() {
        return DescriptionOrigin;
    }

    public void setDescriptionOrigin(String descriptionOrigin) {
        DescriptionOrigin = descriptionOrigin;
    }

    public int getWarranty() {
        return Warranty;
    }

    public void setWarranty(int warranty) {
        Warranty = warranty;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
