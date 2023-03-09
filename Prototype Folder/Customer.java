

public class Customer {

    // class variable
    private String customerName;
    private int customerAge;
    private String customerAddress;
    private String customerEmail;

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



}
