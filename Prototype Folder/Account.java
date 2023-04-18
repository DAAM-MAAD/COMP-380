

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Account {

    // class variables
    private int accountID;
    private String userName;
    private String accountPassword;
    private String customerData;

    // constructor
    public Account(){}
    /**
     * create Account with provided parameters
     * @param accID account number assigned to this Account
     * @param userN account username assigned to this Account
     * @param pass account's password
     * @param customerInfo customer data in string format
     */
    public Account(int accID,String userN, String pass, String customerInfo) {
        this.accountID = accID;
        this.userName = userN;
        this.accountPassword = pass;
        this.customerData = customerInfo;
    }

    // getters
    public int getAccountID() {
        return accountID;
    }
    public String getUserName() {
        return userName;
    }
    public String getAccountPassword() {
        return accountPassword;
    }
    public String getCustomerData() {
        return customerData;
    }

    // setters
    public void setAccountID(int n) {
        this.accountID = n;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setAccountPassword(String s) throws Exception {
        if (s.length() < 8) {
            throw new Exception("Password must be at least 8 characters long.");
        }
        boolean hasUpperCase = false;
        boolean hasLowerCase = false;
        boolean hasDigit = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isUpperCase(c)) {
                hasUpperCase = true;
            } else if (Character.isLowerCase(c)) {
                hasLowerCase = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            }
        }
        if (!hasUpperCase || !hasLowerCase || !hasDigit) {
            throw new Exception("Password must contain at least one uppercase letter, one lowercase letter, and one digit.");
        }
        this.accountPassword = s;
    }
    public void setCustomerData(String customerData) {
        this.customerData = customerData;
    }

    // other methods
    /**
     * Account data is formatted into a string for processing
     * @return string of formatted Account data
     */
    public String accountToString() {
        String str = getAccountID() + " ," + getUserName()+ " ," + getAccountPassword() + " ," + getCustomerData();
        return str;
    }

    public void hashPassword() {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(accountPassword.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            accountPassword = hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * Authenticates the provided password against the account password hash
     * @param password the password to authenticate
     * @return true if the password is correct, false otherwise
     */
    public boolean authenticatePassword(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return accountPassword.equals(hexString.toString());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void updateAccountInfo(String userName, String customerData) {
        setUserName(userName);
        setCustomerData(customerData);
    }

    private ArrayList<String> accountHistory = new ArrayList<String>();

    public void addToAccountHistory(String historyItem) {
    accountHistory.add(historyItem);
    }

    public ArrayList<String> getAccountHistory() {
    return accountHistory;
}

}
