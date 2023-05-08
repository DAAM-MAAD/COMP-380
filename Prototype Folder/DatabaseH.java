
import java.io.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
// import javax.mail.*;
// import javax.mail.internet.*;
// import javax.activation.*;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Represents a Database
 * @name
 * @author Mathewos Yohannes, Aryaman Mehta, AB Paxtor Garcia
 * @reviewed Mathewos Yohannes, Aryaman Mehta
 * @version 1.0
 * @Date  Sun Mar 5 17:54:59 2023 -0800
 * @since 1.0
 *
 * @Description DatabaseH is the work horse of backend. The DatabaseH has the LinkedHashMap
 * that uses Room class as a key and the Account class as the value. As the hotel is a collection
 * of rooms that the user can reserve, the setup of the primary database as:
 * LinkedHashMap<`Room, Account>. DatabaseH also contains ArrayLists of the reservation data and the
 * account data. With these 3 data storages, customer data is stored in an account class, which is then
 * attached to a room through a reservation.
 * @VIPfunctions
 * getRoomByAccountID(int accID)
 * usage:- Get Room account ID from Database. Helper function
 * input:- account ID to be search from in database
 * output:- room number account is occupying
 *
 * getReservationBill(int reservationID)
 * usage:- Provide total cost of reservation with helper function.
 *         Helper function is getReservationTotalCost(int reservationID)
 * input:- reservation ID
 * output:- the total cost of reservation in string format
 *
 * readRoomToDB()
 * usage:- Reads data from "Rooms.csv" file to LinkedHashMap
 * input:- text file / will be changed to csv file
 * output:- Room class that is added to LinkedHashMap
 *
 * readAccFileToList()
 * usage:- Reads data from "Accounts.csv" file to arraylist
 * input:- text file / will be changed to csv file
 * output:- Account class that is added to ArrayList
 *
 * readResToList()
 * usage:- Reads data from the "Reservations.csv" file to arraylist
 * input:- text file / will be changed to csv file
 * output:- Reservation class that is added to ArrayList
 *
 * updateDBWithRes()
 * usage:- Data from the "Reservations.csv" file updates the Database
 * input:- ArrayLists containing Accounts and reservation. LinkedHashMap
 * output:- Data in the reservation ArrayList is added to the LinkedHashMap
 *
 * customerLogin(String userN, String pass)
 * usage:- used to very username and password of customer
 * input:- username and password
 * output:- boolean confirming match in files
 *
 * adminLogin(int adminID, String pass)
 * usage:- used to very username and password of admin
 * input:- username and password
 * output:- boolean confirming match in files
 *
 * addAccountToRoom(int roomNumber, int accountNumber)
 * usage:- Account class is added to Room in the Database
 * input:- number of room that account will be attached to.
 *         number of account that will be attached to room.
 * output:- account is assigned to room number
 *
 * removeAccountFromRoom(int roomNumber, int accountNumber)
 * usage:- Remove Account from the database and set Room's AccountID to null
 * input:- roomNumber that will have Account removed from.
 *         accountNumber that is being removed from database.
 * output:- account is removed from room number
 *
 * roomVacant(int roomNumber)
 * usage:- Database can check if a room is vacant
 * input:- roomNumber to check for vacancy
 * output:- true if Room is vacant else false if occupied
 *
 * checkRoomPrice(int roomNumber)
 * usage:- Check the room price by room number
 * input:- roomNumber for price check
 * output:- the room's price
 *
 * checkOccupancy(int roomNumber)
 * usage:- Check the room occupancy by room number
 * input:- roomNumber for occupancy
 * output:- the room's occupancy
 *
 * makeAccount(String userN, String passwd, Customer c)
 * usage:- Create an account with passed parameters.
 * input:- account password and customer class
 * output:- account is created with set data
 *
 * removeAccount(int acNumber)
 * usage:- remove Account from ArrayList of Accounts. At the same time,
 *         remove account from database which empties a room
 * input:- Account number to be removed
 * output:- Account is deleted from ArrayList
 *
 * changeRoom(int currentRoom, int newRoom)
 * usage:- Change rooms.
 * input:- current room and new room to change to.
 * output:- Accounts will be assigned to a new room.
 *
 * roomPriceRange(double min, double max)
 * usage:- Display all rooms in database within price range
 * input:- the minimum price of a room and the maximum price of a room
 * output:- a list of rooms within price range
 *
 * roomsByType(String type)
 * usage:- Display all rooms in database by room type
 * input:- the type of room to search
 * output:- list of rooms by type
 *
 * writeToRoomFile()
 * usage:- Write to file when program ends
 * input:- LinkedHashMap
 * output:- file with current data regarding rooms
 *
 * writeToAccountFile()
 * usage:- Write to file when program ends
 * input:- ArrayList
 * output:- file with current data regarding accounts
 *
 * writeToReservationFile()
 * usage:- Write to file when program ends
 * input:- ArrayList
 * output:- file with current data regarding reservations
 *
 * makeReservation(int accID, int roomNum, String arrivalDate, int stayLength)
 * usage:- function to make a reservation
 * input:- accountID connected to the reservation.
 *         room number for the reservation.
 * 	       length of reservation.
 * output:- reservation is added to reservation ArrayList
 *
 * cancelReservation(int reservationNumber)
 * usage:- cancel reservation with reservation number
 * input:- reservation number
 * output:- reservation is removed from reservation ArrayList
 *
 * sendEmailConfirmationAccountID(int accountID)
 * usage:- send email confirmation of account ID
 * input:- account ID
 * output:- email with confirmation information
 *
 * @ClassMVPs (data structures, etc...)
 * LinkedHashMap<`Room, Account> db which is the primary database
 * ArrayList<`Account> acList which is where Account information are stored
 * ArrayList<`Reservation> resList which is where Reservation information are stored
 *
 * @algorithms
 * The functions below utilize advanced for-loops to access their data:
 * getRoomByAccountID(int accID)
 * getReservationBill(int reservationID)
 * setNumberOfAccounts()
 * updateDBWithRes()
 * customerLogin(String userN, String pass)
 * adminLogin(int adminID, String pass)
 * addAccountToRoom(int roomNumber, int accountNumber)
 * addAccountToRoom(int roomNumber, int accountNumber)
 * removeAccountFromRoom(int roomNumber, int accountNumber)
 * roomVacant(int roomNumber)
 * checkRoomPrice(int roomNumber)
 * checkOccupancy(int roomNumber)
 * makeAccount(String userN, String passwd, Customer c)
 * removeAccount(int acNumber)
 * changeRoom(int currentRoom, int newRoom)
 * roomPriceRange(double min, double max)
 * roomsByType(String type)
 * writeToRoomFile()
 * writeToAccountFile()
 * writeToReservationFile()
 * cancelReservation(int reservationNumber)
 * sendEmailConfirmationAccountID(int accountID)
 *
 * The functions below utilize while loop until there is no next:
 * readRoomToDB()
 * readAccFileToList()
 * readResToList()
 *
 */
public class DatabaseH {

    // class variables
    private String roomsFile = "Rooms.csv";
    private String accountsFile = "Accounts.csv";
    private String reservationFile = "Reservations.csv";
    private int hotelRoomMax = 500;
    private int numberOfAccounts = 5;
    Connection connection = null;

    // email aids, subject to change for demo
    String emailFrom = "abpaxtorgarcia72@gmail.com";
    String host = "localhost";

    private JDBC jdbcCloser;

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
    public DatabaseH() throws FileNotFoundException, ParseException, SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/maad", "root", "MAAD_password");

        } catch (/*SQLException |*/ ClassNotFoundException ex) {
            System.out.println("An error occurred while connecting MySQL database");
            System.out.println("System will be working with written files.");
            ex.printStackTrace();
        }

        if (connection == null) {
            readRoomToDB();
            readAccFileToList();
            readResToList();
            updateDBWithRes();
            setNumberOfAccounts();
        } else {
            JDBC jdbc = new JDBC();
            jdbcCloser = jdbc;
            readRoomToDB();
            readAccFileToList();
            readResToList();
            updateDBWithRes();
            setNumberOfAccounts();
/*            jdbc.jdbcHash = db;
            jdbc.jdbcAcList = acList;
            jdbc.jdbcResList = resList;
            updateDBWithRes();
            jdbc.pullSQLToHash();*/
        }
    }

    // getters

    /**
     * getter for accountsFile name
     * @return accountsFile name
     */
    private String getAccountsFile() {
        return accountsFile;
    }
    /**
     * getter for reservationFile name
     * @return reservationFile name
     */
    private String getReservationFile() {
        return reservationFile;
    }
    /**
     * getter for roomsFile name
     * @return roomsFile name
     */
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
    public Account getAccount(String userName) {
        for (Account i : acList) {
            if (i.getUserName().equals(userName)) {
                return i;
            }
        }
        return null;
    }
    public int getAccountID(String userName) {
        for (Account i : acList) {
            if (i.getUserName().equals(userName)) {
                return i.getAccountID();
            }
        }
        return 0;
    }

    // getters for Databases
    /**
     * getter of LinkedHashMap<Room, Account>
     * @return LinkedHashMap<Room, Account>
     */
    public LinkedHashMap<Room, Account> getDb() {
        return db;
    }
    /**
     * getter of ArrayList<Account>
     * @return ArrayList<Account>
     */
    public ArrayList<Account> getAcList() {
        return acList;
    }

    /**
     * getter of ArrayList<Reservation>
     * @return ArrayList<Reservation>
     */
    public ArrayList<Reservation> getResList() {
        return resList;
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

    /**
     * setter numberOfAccounts after reading ArrayList<`Account>
     */
    private void setNumberOfAccounts() {
        int accs = 0;
        for (Account i : acList) {
            accs += 1;
        }
        this.numberOfAccounts = accs;
    }

    // Reading files to database and lists when starting program
    /**
     * Reads data from "Rooms.csv" file to LinkedHashMap
     *
     * @throws FileNotFoundException
     */
    private void readRoomToDB() throws FileNotFoundException {
        File file = new File(getRoomsFile());
        Scanner inputFile = new Scanner(file);

        inputFile.nextLine(); // Skipping the Header

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
     * Reads data from "Accounts.csv" file to arraylist
     *
     * @throws FileNotFoundException
     */
    private void readAccFileToList() throws FileNotFoundException {
        File file = new File(getAccountsFile());
        Scanner inputFile = new Scanner(file);

        inputFile.nextLine(); // Skipping the Header

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
     * Reads data from the "Reservations.csv" file to arraylist
     *
     * @throws FileNotFoundException
     * @throws ParseException
     */
    private void readResToList() throws FileNotFoundException, ParseException {
        File file = new File(getReservationFile());
        Scanner inputFile = new Scanner(file);

        inputFile.nextLine(); // Skipping the Header

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
    }
    /**
     * Data from the "Reservations.csv" file updates the Database
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
                }
            }
        }
    }

    // Login in verification

    /**
     * verify customer login information
     * @param userN customer login userName
     * @param pass customer login password
     * @return 1 if verified, 0 if failed
     */
    public boolean customerLogin(String userN, String pass) {
        for (Account a : acList) {
            if (a.getUserName().equals(userN) && a.getAccountPassword().equals(pass)) {
                return true;
            }
        }
        return false;
    }

    /**
     * verify admin login information
     * @param adminID admin login userName
     * @param pass admin login password
     * @return 1 if verified, 0 if failed
     */
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
     * Remove Account from the database and set Room's AccountID to null
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
     * @throws Exception
     */
    public void makeAccount(String userN, String passwd, Customer c) throws Exception {
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

    /**
     * console print out of DataBase
     */
    public void displayDB() {
        System.out.println("roomNumber\t, availability\t, occupancy\t," +
                " roomPrice\t, roomType\t\t\t, amenities\t\t\t, accountID\t, cancelled");

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

    /**
     * Display all Reservation in Reservation arraylist in string format
     */
    public void displayReservations() {
        for (Reservation i : resList) {
            System.out.println(i.reservationToString());
        }
    }

    // Write to file when program ends

    /**
     * write to Room File
     * @throws IOException
     */
    public void writeToRoomFile() throws IOException {

        String fileName = getRoomsFile();
        java.io.FileWriter fw = new java.io.FileWriter(fileName);
        PrintWriter outputFile = new PrintWriter(fw);
        outputFile.println("Room Number, Vacant, Occupancy, Price, Type, Amenities, AccountID");
        for (Room r : db.keySet()) {
            outputFile.println(r.roomToStringToFile().trim());
        }
        outputFile.close();
    }
    /**
     * write to Account File
     * @throws IOException
     */
    public void writeToAccountFile() throws IOException {
        String fileName = getAccountsFile();
        java.io.FileWriter fw = new java.io.FileWriter(fileName);
        PrintWriter outputFile = new PrintWriter(fw);
        outputFile.println("AccountID, UserName, AccountPassword, CustomerData");
        for (Account a : acList) {
            outputFile.println(a.accountToString());
        }
        outputFile.close();
    }
    /**
     * write to Reservation File
     * @throws IOException
     */
    public void writeToReservationFile() throws IOException {
        String fileName = getReservationFile();
        java.io.FileWriter fw = new java.io.FileWriter(fileName);
        PrintWriter outputFile = new PrintWriter(fw);
        outputFile.println("ResID, RoomNumber, Occupancy, RoomPrice, Stay(Days), ArrivalDate, CreatedDate, AccountId, Cancelled");
        for (Reservation r : resList) {
            outputFile.println(r.reservationToString());
        }
        outputFile.close();
    }

    // Write to SQL database when program ends
    public void writeToSQLAccount() throws SQLException {
        jdbcCloser.clearAccountSQLTable();
        for (Account i : acList) {
            jdbcCloser.insert(i);
        }
    }
    public void writeToSQLReservation() throws SQLException {
        jdbcCloser.clearReservationSQLTable();
        for (Reservation i : resList) {
            jdbcCloser.insert(i);
        }
    }
    public void writeToSQLRoom() throws SQLException {
        jdbcCloser.clearRoomSQLTable();
        for (Room i : db.keySet()) {
            jdbcCloser.insert(i);
        }
    }

    /**
     * combines writeToAccountFile(), writeToReservationFile(), and writeToRoomFile()
     * @throws IOException
     */
    public void closeDB() throws IOException, SQLException {
        // Write to file when closing
        writeToAccountFile();
        writeToReservationFile();
        writeToRoomFile();

        if (connection != null) {
            // Clearing SQL tables for writing
            jdbcCloser.removeSQLSAFE();
            jdbcCloser.clearAccountSQLTable();
            jdbcCloser.clearReservationSQLTable();
            jdbcCloser.clearRoomSQLTable();

            // Write to SQL when closing
/*            writeToSQLAccount();
            writeToSQLReservation();
            writeToSQLRoom();*/
        }
    }
    // Reservation changes

    /**
     * function to make a reservation
     * @param accID accountID connected to the reservation
     * @param roomNum room number for the reservation
     * @param arrivalDate the arrival date of the reservation
     * @param stayLength length of reservation
     * @throws ParseException
     */
    public void makeReservation(int accID, int roomNum, String arrivalDate, int stayLength) throws ParseException {
        // Generate a reservation number random with range from 100 to 999.
        int resNum = (int)Math.floor(Math.random() * (999 - 100 + 1) + 100);
        Room selectRoom = null;
        boolean change = false;

        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

        Format f = new SimpleDateFormat("MM/dd/yyyy");
        String strDate = f.format(new Date());
        Date today =new SimpleDateFormat("MM/dd/yyyy").parse(strDate);

        Date resDate =new SimpleDateFormat("MM/dd/yyyy").parse(arrivalDate);
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
        //Reservation newRes = new Reservation(resNum, accID, selectRoom, resDate, date1, stayLength);
        Reservation newRes = new Reservation(resNum, selectRoom.getRoomNumber(), selectRoom.getOccupancy(), selectRoom.getRoomPrice(), stayLength, resDate, today, accID, change);

        // Insert New Reservation to reservation list
        resList.add(newRes);
        // Update database with data from reservation array list
        updateDBWithRes();
        sendEmailConfirmationResID(resNum);
    }
    /**
     * Helper function that connects the GUI with the makeReservation function
     * @param accID account ID
     * @param arrival reservation arrival date
     * @param stayOfLength length of stay
     * @param numberOfGuests number of guests
     * @param roomClass selecting the type/price of room
     * @param roomType selecting the type of amenities/bed
     * @throws ParseException
     */
    public void reservationHelper(int accID, Date arrival, int stayOfLength,
                                  int numberOfGuests, String roomClass , String roomType) throws ParseException {
        // search for room based on number, $, bed
        boolean found = false;

        String[] roomClassArr = roomClass.split(" ", 2);

        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String arrivalDate = dateFormat.format(arrival);

        for (Room i: db.keySet()) {
            if (i.isAvailability() && i.getRoomType().equals(roomClassArr[0].toLowerCase()) &&
                    i.getAmenities().equals(roomType) && i.getOccupancy() >= numberOfGuests) {
                makeReservation(accID, i.getRoomNumber(), arrivalDate, stayOfLength);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("No rooms available with selected parameters.");
        }
    }

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
    public void sendEmailConfirmationAccountID(int accountID, int reservationID) {
        String emailTo = null;
        final String username = "maadhotel380@gmail.com";
        final String password = "qogschhjggmdmmyc";

        for (Account a : acList) {
            if (a.getAccountID() == accountID) {
                emailTo = a.getCustomerData().substring(a.getCustomerData().lastIndexOf(' ') + 1);
            }
        }

        // Below is the actual connection
        Properties properties = System.getProperties();
        // properties.setProperty("mail.smtp.host", host);
        properties.put("mail.smtp.user", username);

        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(properties ,new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        //session.setDebug(true);
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.addRecipients(Message.RecipientType.TO,
                    String.valueOf(new InternetAddress(emailTo)));
            message.setSubject("Email confirmation from MAAD Hotel.");
            message.setText("The is a confirmation email regarding your reservation. \n" +
                    "The following reservation has been assigned. " + reservationID +
                    "\n We hope you enjoy your stay at MAAD Hotel" +
                    "\n Best, " +
                    "\n MAAD HOTEL STAFF");
            Transport.send(message);
            System.out.println("Send message successfully.");
        } catch (MessagingException mex) {
            mex.printStackTrace();;
        }
        System.out.println(emailTo);

    }
    /**
     * send email confirmation of reservation ID
     * @param reservationID
     */
    public void sendEmailConfirmationResID(int reservationID) {
        int accountID;
        for (Reservation r : resList) {
            if (r.getReservationID() == reservationID) {
                accountID = r.getAccountID();
                sendEmailConfirmationAccountID(accountID, reservationID);
            }
        }
    }

    /**
     * function for credit card payment
     * @param card credit card
     */
    public String paymentCreditCard(CreditCard card) {
        String temp = card.getName() + "'s credit card ending in " +
                card.getNumber().substring(card.getNumber().length() - 4) + " is charged for reservation.";
        return temp;
    }
}
