package com.com380;

public class Administration {
    private int adminID;
    private String password;

    // constructor
    public Administration(){}

    // getters and setters
    public int getAdminID() {
        return adminID;
    }
    public String getPassword() {
        return password;
    }

    public void setAdminID(int administerID) {
        this.adminID = administerID;
    }
    public void setPassword(String passwd) {
        this.password = passwd;
    }

    // other functions
    public Report generateReport() {

        return null;
    };
    public boolean reservationCancel(int roomNum) {
        return false;
    }
    public boolean modifyReservation(Reservation n) {
        return false;
    }
    public void sendNotification(Reservation n) {

    }

}
