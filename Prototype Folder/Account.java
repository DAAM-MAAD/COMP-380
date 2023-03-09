package com.com380;

import java.util.ArrayList;

public class Account {

    // class variables
    private int accountID;
    private String accountPassword;
    private ArrayList<String> accountHistory;
    private String customerData;

    // constructor
    public Account() {

    }

    // getters

    public int getAccountID() {
        return accountID;
    }
    public String getAccountPassword() {
        return accountPassword;
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
