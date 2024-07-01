package fpt.CapstoneSU24.model;

import jakarta.persistence.*;

@Entity
@Table(name = "location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private int locationId;
    @Column(name = "address", columnDefinition = "nvarchar(255)")
    private String address;
    @Column(name = "city", columnDefinition = "nvarchar(50)")
    private String city;
    @Column(name = "country", columnDefinition = "nvarchar(50)")
    private String country;
    @Column(name = "coordinateX", columnDefinition = "nvarchar(50)")
    private double coordinateX;
    @Column(name = "coordinateY", columnDefinition = "nvarchar(50)")
    private double coordinateY;
    //manhdt
    //them quan huyen phuong xa
    @Column(name = "district", columnDefinition = "nvarchar(100)")
    private String district;
    @Column(name = "ward", columnDefinition = "nvarchar(100)")
    private String ward;


//    @Column(name = "coordinates", columnDefinition = "nvarchar(50)")
//    private String coordinates;

    public Location() {

    }

    public Location(int locationId, String address, String city, String country, double coordinateX, double coordinateY, String district, String ward) {
        this.locationId = locationId;
        this.address = address;
        this.city = city;
        this.country = country;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.district = district;
        this.ward = ward;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

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

    public double getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(double coordinateX) {
        this.coordinateX = coordinateX;
    }

    public double getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(double coordinateY) {
        this.coordinateY = coordinateY;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    //    }
}
