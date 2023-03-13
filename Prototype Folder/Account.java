package com.com380;

import java.util.ArrayList;

public class Account {

    // class variables
    private int accountID;
    private String accountPassword;
    private ArrayList<String> accountHistory;
    private String customerData;

    // constructor
    public Account(int accID, String pass, String customerInfo) {
        this.accountID = accID;
        this.accountPassword = pass;
        this.customerData = customerInfo;
    }

    // getters
    public int getAccountID() {
        return accountID;
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
    public void setAccountPassword(String s) {
        this.accountPassword = s;
    }

    // other methods



}
