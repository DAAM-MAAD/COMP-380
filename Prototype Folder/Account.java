

import java.util.ArrayList;

public class Account {

    // class variables
    private int accountID;
    private String userName;
    private String accountPassword;
    //private ArrayList<String> accountHistory;
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
    public void setAccountPassword(String s) {
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
}
