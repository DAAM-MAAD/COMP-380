
public class Room {

    // class variables
    private int roomNumber;
    private boolean availability;
    private int occupancy;
    private double roomPrice;
    private String roomType;
    private String amenities;
    private int accountID;
    private boolean cancelled;

    // constructor
    public Room() {};

    /**
     * create Room with provided parameters
     * @param num   room number
     * @param status    if room is occupied or not
     * @param occ   room occupancy
     * @param price room price
     * @param type  room type
     * @param amen  room amenities
     * @param aID   account id
     */
    public Room(int num, boolean status, int occ, double price, String type, String amen, int aID) {
        this.roomNumber = num;
        this.availability = status;
        this.occupancy = occ;
        this.roomPrice = price;
        this.roomType = type;
        this.amenities = amen;
        this.accountID = aID;
    }

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
    public double getRoomPrice() {
        return roomPrice;
    }
    public String getRoomType() {
        return roomType;
    }
    public String getAmenities() {
        return amenities;
    }
    public int getAccountID() {
        return accountID;
    }
    public boolean isCancelled() {
        return cancelled;
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
    public void setRoomPrice(double value) {
        this.roomPrice = value;
    }
    public void setRoomType(String type) {
        this.roomType = type;
    }
    public void setAmenities(String perks) {
        this.amenities = perks;
    }
    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
    // functions
    /**
     * Room data is formatted into a string for processing
     * @return string of formatted Room data
     */
    public String roomToString() {
        String str = getRoomNumber() + "\t\t\t, " + isAvailability() + "\t\t\t, "
                + getOccupancy() + "\t\t\t, " + getRoomPrice() + "\t, " + getRoomType() +
                "\t\t\t, " + getAmenities() + "\t\t\t, " + getAccountID();
        return str;
    }
    /**
     * write Room data from arraylist to file without tabs for file
     * @return string of formatted Room data without tabs
     */
    public String roomToStringToFile() {
        String str = getRoomNumber() + ", " + isAvailability() + ", "
                + getOccupancy() + ", " + getRoomPrice() + ", " + getRoomType() +
                ", " + getAmenities() + ", " + getAccountID();
        return str;
    }
}
