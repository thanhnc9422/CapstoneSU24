package fpt.CapstoneSU24.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data

public class EventItemLogDTO {
    //  private int userId;
    private String address;
    private String city;
    private String country;
    private String coordinateX;
    private String coordinateY;
    private String descriptionItemLog;
    @JsonIgnore
    private int authorizedId;
    private String productRecognition;
    private int eventId;
    @JsonIgnore
    private String phoneParty;
    @JsonIgnore
    private String fullNameParty;
    @JsonIgnore
    private String emailParty;
    @JsonIgnore
    private byte[] imageItemLog;
    private int transportId;

    public int getTransportId() {
        return transportId;
    }

    public void setTransportId(int transportId) {
        this.transportId = transportId;
    }

    public EventItemLogDTO() {
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

    public String getDescriptionItemLog() {
        return descriptionItemLog;
    }

    public void setDescriptionItemLog(String descriptionItemLog) {
        this.descriptionItemLog = descriptionItemLog;
    }

    public int getAuthorizedId() {
        return authorizedId;
    }

    public void setAuthorizedId(int authorizedId) {
        this.authorizedId = authorizedId;
    }

    public String getProductRecognition() {
        return productRecognition;
    }

    public void setProductRecognition(String productRecognition) {
        this.productRecognition = productRecognition;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getPhoneParty() {
        return phoneParty;
    }

    public void setPhoneParty(String phoneParty) {
        this.phoneParty = phoneParty;
    }

    public String getFullNameParty() {
        return fullNameParty;
    }

    public void setFullNameParty(String fullNameParty) {
        this.fullNameParty = fullNameParty;
    }

    public String getEmailParty() {
        return emailParty;
    }

    public void setEmailParty(String emailParty) {
        this.emailParty = emailParty;
    }

    public byte[] getImageItemLog() {
        return imageItemLog;
    }

    public void setImageItemLog(byte[] imageItemLog) {
        this.imageItemLog = imageItemLog;
    }
}
