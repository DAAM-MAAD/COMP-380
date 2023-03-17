package com.com380;

import java.util.Date;

public class Reservation {
    private int reservationID;
    private int roomNumber;
    private int occupancy;
    private float totalCost;
    private int durationOfStay;
    private Date arrivalDate;
    private int accountID;

    // getters and setters

    public int getReservationID() {
        return reservationID;
    }
    public int getRoomNumber() {
        return roomNumber;
    }
    public int getOccupancy() {
        return occupancy;
    }
    public float getTotalCost() {
        return totalCost;
    }
    public int getDurationOfStay() {
        return durationOfStay;
    }
    public Date getArrivalDate() {
        return arrivalDate;
    }
    public int getAccountID() {
        return accountID;
    }

    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
    }
    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
    public void setOccupancy(int occupancy) {
        this.occupancy = occupancy;
    }
    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }
    public void setDurationOfStay(int durationOfStay) {
        this.durationOfStay = durationOfStay;
    }
    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }
    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }
}
