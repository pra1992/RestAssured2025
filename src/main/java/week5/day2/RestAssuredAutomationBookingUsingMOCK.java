package week5.day2;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import  io.restassured.RestAssured.*;

public class RestAssuredAutomationBookingUsingMOCK {
    @Test
    public void createNewBooking(){
        BookingDates bookingDates = new BookingDates();
        BookingDetails bookingDetails = new BookingDetails();
        bookingDates.setCheckin("2018-01-01");
        bookingDates.setCheckout("2019-01-01");
        bookingDetails.setFirstname("Mark");
        bookingDetails.setLastname("Brown");
        bookingDetails.setTotalprice(111);
        bookingDetails.setDepositpaid(true);
        bookingDetails.setBookingDates(bookingDates);
        bookingDetails.setAdditionalneeds("Breakfast");
        RestAssured.given().baseUri("http://localhost:8080")
                .basePath("/booking")
                .header("Content-Type", "application/json")
                .body(bookingDetails)
                .log()
                .all()
                .when()
                .post()
                .then()
                .log()
                .all()
                .assertThat()
              //  .contentType("application/json")
                .statusCode(200)
                .statusLine(Matchers.equalTo("Created"));
    }
}
