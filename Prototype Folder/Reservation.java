

import java.text.SimpleDateFormat;
import java.util.Date;

public class Reservation {
    private int reservationID;
    private int roomNumber;
    private int occupancy;
    private double cost;
    private int durationOfStay;
    private Date arrivalDate;
    private Date dateMade;
    private int accountID;
    private boolean cancelled;

    // constructor
    public Reservation() {
    }
    public Reservation(int rID, int accID, Room roomPick, Date arrivalD, Date resMade, int stayLength){
        this.reservationID = rID;
        this.roomNumber = roomPick.getRoomNumber();
        this.occupancy = roomPick.getOccupancy();
        this.cost = roomPick.getRoomPrice();
        this.durationOfStay = stayLength;
        this.arrivalDate = arrivalD;
        this.dateMade = resMade;
        this.accountID = accID;
        this.cancelled = false;
    }

    /**
     * create Reservation with provided parameters
     *
     * @param rID   reservation ID
     * @param rNum  room number
     * @param occ   room occupancy
     * @param cost room cost
     * @param stay  duration of reservation in days
     * @param mmddyyyy  arrival date of reservation
     * @param resMade when the reservation was made
     * @param aID   account ID that made reservation
     * @param cancel    status of reservation regarding cancellation
     */
    public Reservation(int rID, int rNum, int occ, double cost, int stay,
                       Date mmddyyyy, Date resMade, int aID, boolean cancel) {
        this.reservationID = rID;
        this.roomNumber = rNum;
        this.occupancy = occ;
        this.cost = cost;
        this.durationOfStay = stay;
        this.arrivalDate = mmddyyyy;
        this.dateMade = resMade;
        this.accountID = aID;
        this.cancelled = cancel;
    }
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
    public double getCost() {
        return cost;
    }
    public int getDurationOfStay() {
        return durationOfStay;
    }
    public Date getArrivalDate() {
        return arrivalDate;
    }
    public Date getDateMade() { return dateMade;}
    /**
     * format Date to string
     * @return formatted Date in string
     */
    public String getArrivalDateToString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("M/dd/y");
        String strDate = dateFormat.format(arrivalDate);
        return strDate;
    }
    public String getMadeDateToString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("M/dd/y");
        String strDate = dateFormat.format(dateMade);
        return strDate;
    }
    public int getAccountID() {
        return accountID;
    }
    public boolean isCancelled() {
        return cancelled;
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
    public void setCost(double cost) {
        this.cost = cost;
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
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    // other functions

    /**
     * Reservation data is formatted into a string for processing
     * @return string of formatted Reservation data
     */
    public String reservationToString() {
        String str = getReservationID() + ", " + getRoomNumber() + ", " + getOccupancy() +
                ", " + getCost() + ", " + getDurationOfStay() + ", " + getArrivalDateToString() +
                ", " + getMadeDateToString() + ", " + getAccountID() + ", " + isCancelled();

        return str;
    }
    /**
     * Calculate the total cost of reservation before taxes
     * @param reservationID reservation ID
     * @return return total cost of reservation [cost * duration]
     */
    public String getReservationTotalCost(int reservationID) {
        double totalCost = getCost() * getDurationOfStay();
        String roundOff = String.format("%.2f", totalCost);
        return roundOff;
    }
}
