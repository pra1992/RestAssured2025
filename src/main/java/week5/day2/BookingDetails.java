package week5.day2;

public class BookingDetails {
    private String Firstname;
    private String Lastname;
    private int Totalprice;
    private boolean Depositpaid;
    private BookingDates BookingDates;
    private String Additionalneeds;


    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String firstname) {
        this.Firstname = firstname;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String lastname) {
        this.Lastname = lastname;
    }

    public int getTotalprice() {
        return Totalprice;
    }

    public void setTotalprice(int totalprice) {
        this.Totalprice = totalprice;
    }

    public boolean isDepositpaid() {
        return Depositpaid;
    }

    public void setDepositpaid(boolean depositpaid) {
        this.Depositpaid = depositpaid;
    }

    public BookingDates getBookingDates() {
        return BookingDates;
    }

    public void setBookingDates(BookingDates bookingDates) {
        this.BookingDates = bookingDates;
    }

    public String getAdditionalneeds() {
        return Additionalneeds;
    }

    public void setAdditionalneeds(String additionalneeds) {
        this.Additionalneeds = additionalneeds;
    }
}
