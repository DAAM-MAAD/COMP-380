

import java.io.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DatabaseH {

    // class variables
    private String roomsFile = "Rooms.txt";
    private String accountsFile = "Accounts.txt";
    private String reservationFile = "Reservations.txt";
    // private String accountInfo;
    private int occupancy;
    private int hotelRoomMax = 10;
    private int numberOfAccounts = 5;

    // Databases
    private LinkedHashMap<Room, Account> db = new LinkedHashMap<>();
    private ArrayList<Account> acList = new ArrayList<>();
    private ArrayList<Reservation> resList = new ArrayList<>();

    // constructor

    /**
     * Create Database.
     * Read room file into LinkedHashMap as key
     * Read account file into array list
     * Read reservation file into array list
     * Update LinkedHashMap values when reviewing reservation array list
     * Update the number of accounts private variable
     * @throws FileNotFoundException
     * @throws ParseException
     */
    public DatabaseH() throws FileNotFoundException, ParseException {
        readRoomToDB();
        readAccFileToList();
        readResToList();
        updateDBWithRes();
        setNumberOfAccounts();
    }

    // getters
    private String getAccountsFile() {
        return accountsFile;
    }
    private String getReservationFile() {
        return reservationFile;
    }
    private String getRoomsFile() {
        return roomsFile;
    }
    /**
     * Get Room account ID from Database. Helper function
     *
     * @param accID account ID to be search from in database
     * @return room number account is occupying
     */
    public int getRoomByAccountID(int accID) {
        for (Room r : db.keySet()) {
            if (r.getAccountID() == accID) {
                return r.getRoomNumber();
            }
        }
        return 0;
    }

    /**
     * Provide total cost of reservation with helper function.
     * Helper function is getReservationTotalCost(int reservationID).
     * @param reservationID reservation ID
     * @return the total cost of reservation in string format.
     */
    public String getReservationBill(int reservationID) {
        for (Reservation r : resList) {
            if (r.getReservationID() == reservationID) {
                return r.getReservationTotalCost(reservationID);
            }
        }
        return "There is no reservation with that ID.";
    }

    // setters
    private void setNumberOfAccounts() {
        int accs = 0;
        for (Account i : acList) {
            accs += 1;
        }
        this.numberOfAccounts = accs;
    }
    /*
    insert account to database when customer selects room - DONE
    read reservation file and update database in constructor - DONE
    remove account from database by room number and account ID - DONE
    is room available - DONE
    find rooms occupancy - DONE
    search for room by X - DONE
     */

    // Reading files to database and lists when starting program
    /**
     * Reads data from "Rooms.txt" file to LinkedHashMap
     *
     * @throws FileNotFoundException
     */
    private void readRoomToDB() throws FileNotFoundException {
        File file = new File(getRoomsFile());
        Scanner inputFile = new Scanner(file);

        while (inputFile.hasNext()) {
            String[] str = inputFile.nextLine().split(",");
            int roomNumber = Integer.parseInt(str[0].trim());
            boolean availability = Boolean.parseBoolean(str[1].trim());
            int occupancy = Integer.parseInt(str[2].trim());
            double roomPrice = Double.parseDouble(str[3].trim());
            String roomType = str[4].trim();
            String amenities = str[5].trim();
            int customerID = Integer.parseInt(str[6].trim());

            Room readRoom = new Room(roomNumber, availability, occupancy,
                    roomPrice, roomType, amenities, customerID);

            if (!str.equals("")) {
                db.put(readRoom, null);
            }
        }
        inputFile.close();
    }
    /**
     * Reads data from "Accounts.txt" file to arraylist
     *
     * @throws FileNotFoundException
     */
    private void readAccFileToList() throws FileNotFoundException {
        File file = new File(getAccountsFile());
        Scanner inputFile = new Scanner(file);

        while (inputFile.hasNext()) {
            String[] str = inputFile.nextLine().split(",");
            int accountID = Integer.parseInt(str[0].trim());
            String userName = str[1].trim();
            String accountPassword = str[2].trim();
            String customerData = str[3].trim();

            if (!str.equals("")) {
                Account ac = new Account(accountID, userName, accountPassword, customerData);
                acList.add(ac);
            }
        }
        inputFile.close();
    }
    /**
     * Reads data from the "Reservations.txt" file to arraylist
     *
     * @throws FileNotFoundException
     * @throws ParseException
     */
    private void readResToList() throws FileNotFoundException, ParseException {
        File file = new File(getReservationFile());
        Scanner inputFile = new Scanner(file);

        while (inputFile.hasNext()) {
            String[] str = inputFile.nextLine().split(",");
            //reservationID, roomNumber, occupancy, totalCost, durationOfStay, arrivalDate, madeDate, accountID
            int reservationID = Integer.parseInt(str[0].trim());
            int roomNumber = Integer.parseInt(str[1].trim());
            int occupancy = Integer.parseInt(str[2].trim());
            double totalCost = Double.parseDouble(str[3].trim());
            int durationOfStay = Integer.parseInt(str[4].trim());
            Date arrivalDate = new SimpleDateFormat("M/dd/y").parse(str[5].trim());
            Date resMade = new SimpleDateFormat("M/dd/y").parse(str[6].trim());
            int accountID = Integer.parseInt(str[7].trim());
            boolean cancel = Boolean.parseBoolean(str[8].trim());

            if (!str.equals("")) {
                Reservation res = new Reservation(reservationID, roomNumber, occupancy,
                        totalCost, durationOfStay, arrivalDate, resMade, accountID, cancel);
                resList.add(res);
            }
        }
        inputFile.close();
        // not complete

    }
    /**
     * Data from the "Reservations.txt" file updates the Database
     */
    private void updateDBWithRes() {
        for (Room r : db.keySet()) {
            for (Account a : acList) {
                for (Reservation res : resList) {
                    int rNum = res.getRoomNumber();
                    int aID = res.getAccountID();
                    boolean cancel = res.isCancelled();

                    if (aID == a.getAccountID() && rNum == r.getRoomNumber() && !cancel)  {
                        r.setAccountID(a.getAccountID());
                        r.setAvailability(false);
                        db.replace(r, a);
                    }
                    else if (cancel == true) {
                        continue;
                    }
                }
            }
        }
    }

    // Login in verification
    public boolean customerLogin(String userN, String pass) {
        for (Account a : acList) {
            if (a.getUserName().equals(userN) && a.getAccountPassword().equals(pass)) {
                return true;
            }
        }
        return false;
    }

    public boolean adminLogin(int adminID, String pass) {
        Administration ad = new Administration();
        if (adminID == ad.getAdminID() && pass.equals(ad.getPassword())) {
            return true;
        }
        return false;
    }


    // Alter database

    /**
     * Account class is added to Room in the Database
     *
     * @param roomNumber    number of room that account will be attached to
     * @param accountNumber number of account that will be attached to room
     */
    public void addAccountToRoom(int roomNumber, int accountNumber) {
        Account acc = new Account();
        for (Account a : acList) {
            if (accountNumber == a.getAccountID()) {
                acc = a;
            }
        }
        for (Room r : db.keySet()) {
            if (r.getRoomNumber() == roomNumber) {
                r.setAccountID(accountNumber);
                r.setAvailability(false);
                db.replace(r, acc);
            }
        }
    }
    /**
     * Remove Account from the database and set Room's AccountID to 0
     *
     * @param roomNumber    roomNumber that will have Account removed from
     * @param accountNumber accountNumber that is being removed from database
     */
    public void removeAccountFromRoom(int roomNumber, int accountNumber) {
        Account temp = new Account();
        for (Room r : db.keySet()) {
            if (r.getRoomNumber() == roomNumber && r.getAccountID() == accountNumber) {
                r.setAccountID(0);
                r.setAvailability(true);
            }
        }
    }
    /**
     * Database can check if a room is vacant
     * @param roomNumber roomNumber to check for vacancy
     * @return true if Room is vacant else false if occupied
     */
    public boolean roomVacant(int roomNumber) {
        for (Room r : db.keySet()) {
            if (r.getRoomNumber() == roomNumber && r.isAvailability()) {
                return true;
            }
        }
        return false;
    }
    /**
     * Check the room price by room number
     *
     * @param roomNumber roomNumber for price check
     * @return the room's price
     */
    public double checkRoomPrice(int roomNumber) {
        for (Room r : db.keySet()) {
            if (r.getRoomNumber() == roomNumber) {
                return r.getRoomPrice();
            }
        }
        return 0;

    }
    /**
     * Check the room occupancy by room number
     *
     * @param roomNumber roomNumber for occupancy
     * @return the room's occupancy
     */
    public int checkOccupancy(int roomNumber) {
        for (Room r : db.keySet()) {
            if (r.getRoomNumber() == roomNumber) {
                return r.getOccupancy();
            }
        }
        return 0;
    }
    /**
     * Create an account with passed parameters.
     * @param passwd    account password
     * @param c         customer class
     */
    public void makeAccount(String userN, String passwd, Customer c) {
        int newAccountNumber = (int)Math.floor(Math.random() * (99 - 10 + 1) + 10);

        for (Account a : acList) {
            if (a.getAccountID() == newAccountNumber)
            {
                newAccountNumber = (int)Math.floor(Math.random() * (99 - 10 + 1) + 10);
            }
        }
        Account acc = new Account();
        acc.setAccountID(newAccountNumber);
        acc.setUserName(userN);
        acc.setAccountPassword(passwd);
        acc.setCustomerData(c.customerToString());
        acList.add(acc);
    }
    /**
     * remove Account from ArrayList of Accounts. At the same time,
     * remove account from database which empties a room
     *
     * @param acNumber Account number to be removed
     */
    public void removeAccount(int acNumber) {
        int n = -1;
        for (Account i : acList) {
            n += 1;

            if (i.getAccountID() == acNumber) {
                acList.set(n, new Account());
                System.out.println("Account #:" + acNumber + " has been removed.\n");

            }
        }
        if (acNumber < 0 || acNumber > n + 1) {
            System.out.println("There is no Account with ID: " + acNumber + "\n");
        }
        int roomNum = getRoomByAccountID(acNumber);
        if (roomNum != 0) {
            removeAccountFromRoom(roomNum, acNumber);
            System.out.println("Account #:" + acNumber +
                    " has been removed from Room #:" + roomNum + "\n");
        }
        else {
            System.out.println("Account has no reservation(s) to delete from Database.\n");
        }

    }

    /**
     * Change rooms.
     * @param currentRoom   current room
     * @param newRoom       new room to change to
     */
    public void changeRoom(int currentRoom, int newRoom) {
        int accountID = 0;
        Account acc = new Account();
        double newRoomCost = 0.0;
        int newOccupancy = 0;

        /**
         * get the current room's Account ID and Account
         */
        for (Room i : db.keySet()) {
            if (i.getRoomNumber() == currentRoom) {
                accountID = i.getAccountID();
                acc = db.get(i);
            }
        }

        /**
         * check if new room is free to move into.
         * if free, store the new room's price and occupancy into local variable.
         * add account to new room in database from previous for-loop
         */
        for (Room r : db.keySet()) {
            if (r.getRoomNumber() == newRoom) {
                if (!r.isAvailability()) {
                    System.out.println("Selected room is unavailable." +
                            " Please select another room.");
                }
                else {
                    r.setAccountID(accountID);
                    newRoomCost = r.getRoomPrice();
                    newOccupancy = r.getOccupancy();
                    db.replace(r, acc);
                }
            }
        }
        /**
         * if new room is free, remove Account from Room in database
         */
        for (Room j : db.keySet()) {
            if (j.getRoomNumber() == currentRoom && accountID != 0) {
                j.setAccountID(0);
                removeAccountFromRoom(j.getRoomNumber(), j.getAccountID());
            }
        }
        /**
         * update reservation list with changes by adding new room number,
         * new occupancy and new room cost to reservation data
         */
        for (Reservation k : resList) {
            if (k.getRoomNumber() == currentRoom) {
                k.setRoomNumber(newRoom);
                k.setOccupancy(newOccupancy);
                k.setCost(newRoomCost);
            }
        }
    }

    // Displaying Data
    public void displayDB() {
        System.out.println("roomNumber\t, availability\t, occupancy\t," +
                " roomPrice\t, roomType\t\t\t, amenities\t\t\t\t\t, accountID");

        for (Room r : db.keySet()) {
            String room = r.roomToString();
            Account acc = db.get(r);
            String account;
            if (r.getAccountID() == 0) {
                account = "No Account Attached.";
            }
            else if (db.get(r) == null) {
                account = "ATTENTION: Account has been cancelled.";
            }
            else {
                account = acc.accountToString();
            }
            System.out.println(room + ", \t\t" + account);

        }
    }
    /**
     * Display all rooms in database by availability
     *
     */
    public void displayAvailableRooms() {
        System.out.println("\nAvailable Rooms: ");
        for (Room r: db.keySet()) {
            if(r.isAvailability()) {
                System.out.println(r.roomToString());
            }
        }
    }
    /**
     * Display all rooms in database within price range
     * @param min the minimum price of a room
     * @param max the maximum price of a room
     */
    public void roomPriceRange(double min, double max) {
        System.out.println("\nRooms price between $" + min + " - $" + max + ":");
        for (Room r: db.keySet()) {
            if (min <=  r.getRoomPrice() && r.getRoomPrice() <= max) {
                System.out.println(r.roomToString());
            }
        }
    }
    /**
     * Display all rooms in database by room type
     * @param type the type of room to search
     */
    public void roomsByType(String type) {
        System.out.println("\nRooms - " + type);
        for (Room r: db.keySet()) {
            if (r.getRoomType().equals(type)) {
                System.out.println(r.roomToString());
            }
        }
    }
    /**
     * Display all Accounts in Account arraylist in string format
     *
     */
    public void displayAccounts() {
        for (Account i : acList) {
            System.out.println(i.accountToString());
        }
    }
    // Display reservations
    public void displayReservations() {
        for (Reservation i : resList) {
            System.out.println(i.reservationToString());
        }
    }

    // Write to file when program ends
    public void writeToRoomFile() throws IOException {

        String fileName = getRoomsFile();
        java.io.FileWriter fw = new java.io.FileWriter(fileName);
        PrintWriter outputFile = new PrintWriter(fw);
        for (Room r : db.keySet()) {
            outputFile.println(r.roomToStringToFile().trim());
        }
        outputFile.close();
    }
    public void writeToAccountFile() throws IOException {
        String fileName = getAccountsFile();
        java.io.FileWriter fw = new java.io.FileWriter(fileName);
        PrintWriter outputFile = new PrintWriter(fw);
        for (Account a : acList) {
            outputFile.println(a.accountToString());
        }
        outputFile.close();
    }
    public void writeToReservationFile() throws IOException {
        String fileName = getReservationFile();
        java.io.FileWriter fw = new java.io.FileWriter(fileName);
        PrintWriter outputFile = new PrintWriter(fw);
        for (Reservation r : resList) {
            outputFile.println(r.reservationToString());
        }
        outputFile.close();
    }

    // Reservation changes
    /*
    When removing a reservation, free room, remove accountId from room, then set
    reservation as cancelled
     */

    /*
        check if room is available with roomVacant.
        If not available, print statement that room is not available.
        If room is available:
            generate reservation number
            insert into reservation arraylist
            insert accountNum in main database

     */
    public void makeReservation(int accID, int roomNum, String arrivalDate, int stayLength) throws ParseException {
        // Generate a reservation number random with range from 100 to 999.
        int resNum = (int)Math.floor(Math.random() * (999 - 100 + 1) + 100);
        Room selectRoom = null;

        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

        Format f = new SimpleDateFormat("MM/dd/yyyy");
        String strDate = f.format(new Date());
        Date date1=new SimpleDateFormat("MM/dd/yyyy").parse(strDate);

        Date resDate =new SimpleDateFormat("MMddyyyy").parse(arrivalDate);
        System.out.println("Reservation made today for: " + formatter.format(resDate));

        // Checking if reservation number is already in use
        if (roomVacant(roomNum)) {
            for (Reservation r : resList) {
                if (r.getReservationID() == resNum)
                {
                    resNum = (int)Math.floor(Math.random() * (999 - 100 + 1) + 100);
                }
            }
        }
        else {
            System.out.println("Room selected is not vacant.");
            return;
        }
        // Store Room in local variable
        for (Room r : db.keySet()) {
            if (r.getRoomNumber() == roomNum) {
                selectRoom = r;
            }
        }
        Reservation newRes = new Reservation(resNum, accID, selectRoom, resDate, date1, stayLength);

        // Insert New Reservation to reservation list
        resList.add(newRes);
        // Update database with data from reservation array list
        updateDBWithRes();
    }
    public void updateRoomWithAcIDInDB(int roomNumber, int accID) {}


    /**
     * cancel reservation with reservation number
     * @param reservationNumber reservation number
     */
    public void cancelReservation(int reservationNumber) {
        int roomNumber = 0;
        int accountNumber = 0;
        for (Reservation i : resList) {
            if (i.getReservationID() == reservationNumber) {
                roomNumber = i.getRoomNumber();
                accountNumber = i.getAccountID();
                i.setCancelled(true);
            }
        }
        for (Room r : db.keySet()) {
            if (r.getRoomNumber() == roomNumber) {
                r.setAvailability(true);
                r.setAccountID(0);
                removeAccountFromRoom(roomNumber, accountNumber);
            }
        }
        System.out.println("\nReservation #:" + reservationNumber + " has been cancelled.\n");
    }
    /**
     * send email confirmation of account ID
     * @param accountID account ID
     */
    public void sendEmailConfirmationAccountID(int accountID) {
        String email;
        for (Account a : acList) {
            if (a.getAccountID() == accountID) {
                email = a.getCustomerData().substring(a.getCustomerData().lastIndexOf(' ') + 1);
                System.out.println(email);
            }
        }
    }
    /**
     * send email confirmation of reservation ID
     * @param reservationID
     */
    public void sendEmailConfirmationResID(int reservationID) {
        String email;
        int accountID;
        for (Reservation r : resList) {
            if (r.getReservationID() == reservationID) {
                accountID = r.getAccountID();
                sendEmailConfirmationAccountID(accountID);
            }
        }
    }

}
