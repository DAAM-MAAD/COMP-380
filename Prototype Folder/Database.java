import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;

public class Database {

    // class variables
    private String roomsFile = "Rooms.txt";
    private String accountsFile = "Accounts.txt";
    // private String accountInfo;
    private int occupancy;
    private int hotelRoomMax = 10;
    private int numberOfAccounts = 5;

    private HashMap<Room, Account> db = new HashMap<Room, Account>();


    // constructor
    public Database() {
    };

    // HashMap
    /*
    + need upload Account.txt data to
     */



    // Regular
    // getters
    public String getRoomsFile() {
        return roomsFile;
    }
    public String getAccountsFile() {
        return this.accountsFile;
    }
    public int getNumberOfRooms() {
        return occupancy;
    }
    public int getNumberOfAccounts() {
        return numberOfAccounts;
    }
    public int getHotelRoomMax() {
        return hotelRoomMax;
    }
    public int getOccupancy() {
        return occupancy;
    }

    // setters
    public void setOccupancy(int n) {
        if (this.occupancy >= 0 && this.occupancy != getHotelRoomMax()) {    // test to make sure occupany does not g
            this.occupancy = this.occupancy + (n);
        }
        else if(this.occupancy == hotelRoomMax) {
            System.out.println("No Vacancy.");
        }
    }
    public void setNumberOfAccounts(int n) {
        this.numberOfAccounts = n;
    } // Remove. Don't need

    // other methods
    /*
    + add new account to account file - Done
    + remove account from account file - Done
    + check if account exists before adding - Done
    + read account file to array?
    + read room file to array?
     */
    private boolean accountExists(String info) throws IOException {
        return Files.lines(Paths.get(getAccountsFile())).anyMatch(l -> l.contains(info));

    }
    public void addAccount(Account acc) throws IOException {
        String info = accountToString(acc);
        if(accountExists(info)) {
            System.out.println("Account Exist. Can not add.");
            return;
        }
        else {
            String fileName = getAccountsFile();
            java.io.FileWriter fw = new java.io.FileWriter(fileName, true);
            PrintWriter outputFile = new PrintWriter(fw);
            outputFile.println(info);
            outputFile.close();

            setOccupancy(1);
        }
    }
    public void readAccountFile() throws FileNotFoundException {
        File file = new File(getAccountsFile());
        Scanner inputFile = new Scanner(file);

        while(inputFile.hasNext()) {
            String str = inputFile.nextLine();
            System.out.println(str);
        }
        inputFile.close();

    }
    public void removeAccount(int accID) throws IOException {
//Construct the new file that will later be renamed to the original filename.
        File inputFile = new File(getAccountsFile());
        File tempFile = new File("tempFile.txt");
        BufferedReader br = new BufferedReader(new FileReader(getAccountsFile()));
        PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
        String line = null;


        //Read from the original file and write to the new
        //unless content matches data to be removed.
        while ((line = br.readLine()) != null) {
            String[] ac = line.split(", ");

            // Remove empty lines
            if (line.equals("")) {
                continue;
            }

            // if accID equals account ID, remove from file
            if (!ac[0].equals(Integer.toString(accID))) {
                pw.println(line);
                pw.flush();
            }
        }
        pw.close();
        br.close();

        setOccupancy(-1);

        //Delete the original file
        if (!inputFile.delete()) {
            System.out.println("Could not delete file");
            return;
        }
        //Rename the new file to the filename the original file had.
        if (!tempFile.renameTo(inputFile))
            System.out.println("Could not rename file");
    }
    public String accountToString(Account acc) {
        int accountID = acc.getAccountID();
        String accountPassword = acc.getAccountPassword();
        String customerData = acc.getCustomerData();

        String accountInfo = accountID + ", " + accountPassword + ", " + customerData;

        return accountInfo;
    }

}
