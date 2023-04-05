

public class Report {
    int numberOfAccounts;
    int hotelOccupancy;
    double revenueTotal;
    int cancellations;
    int numberOfNewGuests;

    // filter by methods
    /*
    https://www.hotelogix.com/blog/8-must-have-hotel-reports-with-cloud-property-management-system/

    arrival report
    departure report
    manager flash report


     */

    // functions
    public int getRoomNumber(int accountNum) {
        return accountNum;
    }
    public int getHotelOccupancy() {
        return 0;
    }
    public Customer getCustomerInfo(Customer p) {
        return p;
    }


}
