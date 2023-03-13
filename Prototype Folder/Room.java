package com.com380;

public class Room {

    // class variables
    private int roomNumber;
    private boolean availability;
    private int occupancy;
    private float roomPrice;
    private String roomType;
    private String amenities;

    // constructor
    public Room() {};

    // getters
    public int getRoomNumber() {
        return roomNumber;
    }
    public boolean isAvailability() {
        return availability;
    }
    public int getOccupancy() {
        return occupancy;
    }
    public float getRoomPrice() {
        return roomPrice;
    }
    public String getRoomType() {
        return roomType;
    }
    public String getAmenities() {
        return amenities;
    }

    // setters
    public void setRoomNumber(int n) {
        this.roomNumber = n;
    }
    public void setAvailability(boolean state) {
        this.availability = state;
    }
    public void setOccupancy(int n) {
        this.occupancy = n;
    }
    public void setRoomPrice(float value) {
        this.roomPrice = value;
    }
    public void setRoomType(String type) {
        this.roomType = type;
    }
    public void setAmenities(String perks) {
        this.amenities = perks;
    }


}
