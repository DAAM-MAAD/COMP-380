
/**
 * Represents a customer
 * @name
 * @author Mathewos Yohannes
 * @reviewed Mathewos Yohannes, Aryaman Mehta
 * @version 1.0
 * @Date  Sun Mar 5 17:51:29 2023 -0800
 * @since 1.0
 *
 * @Description Customer class is created when a new Account class is created using the GUI.
 * When the user selects the "Create New User" button, all the information they provide,
 * will be stored as a Customer class for use by other classes such as DatabaseH.
 * @VIPfunctions
 * accountToString(): Converts Customer information to string for easy use and storage
 * in the hard files.
 *
 * @ClassMVPs (data structures, etc...) None
 * @algorithms None
 *
 */

public class Customer {

    // class variable
    private String customerName;
    private int customerAge;
    private String customerAddress;
    private String customerEmail;

    // constructors
    public Customer(){};

    /**
     * create Customer with provided parameters
     * @param cName customer's name
     * @param age customer's age
     * @param address customer's address
     * @param email customer's email
     */
    public Customer(String cName, int age, String address, String email) {
        this.customerName = cName;
        this.customerAge = age;
        this.customerAddress = address;
        this.customerEmail = email;
    }

    // getters
    public String getCustomerName() {
        return customerName;
    }
    public int getCustomerAge() {
        return customerAge;
    }
    public String getCustomerAddress() {
        return customerAddress;
    }
    public String getCustomerEmail() {
        return customerEmail;
    }

    // setters
    public void setCustomerName(String name) {
        this.customerName = name;
    }
    public void setCustomerAge(int age) {
        this.customerAge = age;
    }
    public void setCustomerAddress(String address) {
        this.customerAddress = address;
    }
    public void setCustomerEmail(String email) {
        this.customerEmail = email;
    }

    // functions
    /*
    make reservation - thru database class
    cancel reservation - thru database class
    modify reservation - thru database class
    see rooms and amenities - thru database class
    see rooms prices - thru database class
    make payment - thru database class
    recieve room confirmation - thru database class
    file complaint - thru database class
    leave rating - thru database class
     */

    /**
     * Customer data is formatted into a string for processing
     * @return string of formatted Customer data
     */
    public String customerToString() {
        String str = getCustomerName() + " " + getCustomerAge() + " " +
                getCustomerAddress() + " " + getCustomerEmail();
        return str;
    }
}
