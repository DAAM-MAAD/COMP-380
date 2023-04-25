

/**
 * Represents an Administration
 * @name Administration.java
 * @author Mathewos Yohannes
 * @reviewed Mathewos Yohannes, Aryaman Mehta
 * @version 1.0
 * @Date Sun Mar 5 17:50:38 2023 -0800
 * @since 1.0
 *
 * @Description Administration class is simply used to store the Administration username
 * and password.
 * @VIPfunctions None
 * @ClassMVPs (data structures, etc...) None
 * @algorithms None
 *
 */

public class Administration {
    private int adminID = 100;
    private String password = "password";

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
