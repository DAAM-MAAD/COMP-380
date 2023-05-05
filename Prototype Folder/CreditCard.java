import java.util.Date;

/**
 * Represents a Credit Card
 * @name CreditCard.java
 * @author Mathewos Yohannes
 * @reviewed Mathewos Yohannes, Aryaman Mehta
 * @version 1.0
 * @Date Mon May 5 09:05:00 2023 - 0800
 * @since 1.0
 *
 * @Description Credit card class is simply used to store the Credit card information.
 * @VIPfunctions None
 * @ClassMVPs (data structures, etc...) None
 * @algorithms None
 *
 */
public class CreditCard {

    // private variables
    private String number;
    private String name;
    private String ccv;
    private Date expiration;

    /**
     * Constructor for credit card
     * @param no credit card number
     * @param nm credit card holder's name
     * @param backNum credit card CCV number
     * @param expires credit card expiration date
     */
    CreditCard(String no, String nm, String backNum, Date expires) {
        number = no;
        name = nm;
        ccv = backNum;
        expiration = expires;
    }
    // getters
    /**
     * getter for credit card number
     * @return credit card number
     */
    public String getNumber() { return number; }
    /**
     * getter for credit card holder's name
     * @return credit card holder's name
     */
    public String getName() { return name; }
    /**
     * getter for credit card cvv
     * @return credit card ccv
     */
    public String getCcv() { return ccv; }
    /**
     * getter for credit card expiration
     * @return credit card expiration
     */
    public Date getExpiration() { return expiration; }
    /**
     * print out credit details
     */
    public void printCard() {
        System.out.println("Number = " + getNumber());
        System.out.println("Name = " +  getName());
        System.out.println("Expiration = " + getExpiration());
    }
}
