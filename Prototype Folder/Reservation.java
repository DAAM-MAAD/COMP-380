/**
 * Represents a reservation
 * @name
 * @author Mathewos Yohannes, Aryaman Mehta
 * @reviewed Mathewos Yohannes, Aryaman Mehta
 * @version 1.0
 * @Date  Sun Mar 5 17:51:09 2023 -0800
 * @since 1.0
 *
 * @Description Reservation class is created when the user enters the "Make a Reservation" tag
 * in the GUI. From here, the user will provide the necessary information to complete
 * a reservation, which is then stored in an ArrayList in DatabaseH during run time.
 * When the user exits the GUI, the information from the Arraylist in DatabaseH is stored
 * in a local file and also in a table in SQL using the JDBC class.
 * During the activation of the GUI, the information stored in the SQL table or the local file
 * updates the main HashMap with its information.
 * @VIPfunctions
 * getArrivalDateToString(): format Date to string for the selected reservation date
 * getMadeDateToString(): format Date to string for when the reservation is made
 * reservationToString(): Reservation data is formatted into a string for processing
 * getReservationTotalCost(int reservationID): Calculate the total cost of reservation before taxes
 * @ClassMVPs (data structures, etc...) None
 * @algorithms None
 *
 */


import java.text.SimpleDateFormat;
import java.util.Date;

public class Reservation {
    private static final double TAX_RATE = .17;
    private int reservationID;
    private int roomNumber;
    private int occupancy;
    private double cost;
    private int durationOfStay;
    private Date arrivalDate;
    private Date dateMade;
    private int accountID;
    private boolean cancelled;
    private double totalCostWithTax;

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
        this.totalCostWithTax = getTotalCostWithTax();
    }

    public double getTotalCostWithTax() {
        return totalCostWithTax;
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
        this.totalCostWithTax = getTotalCostWithTax();
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
    public int getAccountID() {
        return accountID;
    }
    public boolean isCancelled() {
        return cancelled;
    }

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
        double totalCostWithTax = totalCost * (1 + TAX_RATE);
        String roundOff = String.format("%.2f", totalCostWithTax);
        this.totalCostWithTax = totalCostWithTax;
        return roundOff;
    }
    

    /**
     * Format reservation arrival date as a string
     * @param date reservation arrival date
     * @return formatted date string (e.g. "May 5, 2023")
     */
    public String formatArrivalDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy");
        String strDate = dateFormat.format(date);
        return strDate;
    }
    
    /**
     * Format reservation made date as a string
     * @param date reservation made date
     * @return formatted date string (e.g. "May 5, 2023")
     */

    public String formatMadeDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy");
        String strDate = dateFormat.format(date);
        return strDate;
    }
}
