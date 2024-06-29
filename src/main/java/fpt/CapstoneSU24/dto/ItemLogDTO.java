package fpt.CapstoneSU24.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ItemLogDTO {
    @NotNull(message = "The quantity is required")
    @Min(value = 1, message = "The quantity must be at least 1")
    @Max(value = 10000, message = "The quantity must not exceed 10,000")
    private int quantity;
    @NotNull(message = "The adress is required")
    private String address;
    @NotNull(message = "The city is required")
    private String city;
    @NotNull(message = "The country is required")
    private String country;
    @NotNull(message = "The district is required")
    private String district;
    @NotNull(message = "The street is required")
    private String street;
    @NotNull(message = "The coordinateX is required")

    private String coordinateX;
    @NotNull(message = "The coordinateY is required")
   // @DecimalMin(value = "-180.0", message = "Longitude must be greater than or equal to -180.0")
   // @DecimalMax(value = "180.0", message = "Longitude must be less than or equal to 180.0")

    private String coordinateY;

    private String descriptionOrigin;

    @NotNull(message = "The productId is required")
    private int productId;

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

        public int getQuantity() {
                return quantity;
        }

        public void setQuantity(int quantity) {
                this.quantity = quantity;
        }
        // Getters and Setters
        public String getAddress() {
                return address;
        }

        public void setAddress(String address) {
                this.address = address;
        }

        public String getCity() {
                return city;
        }

        public void setCity(String city) {
                this.city = city;
        }

        public String getCountry() {
                return country;
        }

        public void setCountry(String country) {
                this.country = country;
        }

        public String getCoordinateX() {
                return coordinateX;
        }

        public void setCoordinateX(String coordinateX) {
                this.coordinateX = coordinateX;
        }

        public String getCoordinateY() {
                return coordinateY;
        }

        public void setCoordinateY(String coordinateY) {
                this.coordinateY = coordinateY;
        }


        public String getDescriptionOrigin() {
                return descriptionOrigin;
        }

        public void setDescriptionOrigin(String descriptionOrigin) {
                this.descriptionOrigin = descriptionOrigin;
        }

        public int getProductId() {
                return productId;
        }

        public void setProductId(int productId) {
                this.productId = productId;
        }
}
