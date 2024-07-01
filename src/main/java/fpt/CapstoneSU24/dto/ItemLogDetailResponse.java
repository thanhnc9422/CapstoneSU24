package fpt.CapstoneSU24.dto;

public class ItemLogDetailResponse {
private int itemLogId;
private String eventType;
private String partyFullname;

private String sender;
private String receiver;
private String PartyPhoneNumber;
private String addressInParty;
private double coordinateX;
private double coordinateY;
private double timeReceive;
private String descriptionItemLog;

    public String getAddressInParty() {
        return addressInParty;
    }

    public void setAddressInParty(String addressInParty) {
        this.addressInParty = addressInParty;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public int getItemLogId() {
        return itemLogId;
    }

    public void setItemLogId(int itemLogId) {
        this.itemLogId = itemLogId;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getPartyFullname() {
        return partyFullname;
    }

    public void setPartyFullname(String partyFullname) {
        this.partyFullname = partyFullname;
    }

    public String getPartyPhoneNumber() {
        return PartyPhoneNumber;
    }

    public void setPartyPhoneNumber(String partyPhoneNumber) {
        PartyPhoneNumber = partyPhoneNumber;
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

    public double getTimeReceive() {
        return timeReceive;
    }

    public void setTimeReceive(double timeReceive) {
        this.timeReceive = timeReceive;
    }

    public String getDescriptionItemLog() {
        return descriptionItemLog;
    }

    public void setDescriptionItemLog(String descriptionItemLog) {
        this.descriptionItemLog = descriptionItemLog;
    }
}
