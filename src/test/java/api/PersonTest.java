package api;

import com.example.OnlineStore.dto.BookingDTO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class PersonTest {
    private final static String URL = "http://localhost:8080/";

    @Test
    public void checkPersonBookings() {
        Response response = RestAssured
                .given()
                .when()
                .contentType(ContentType.JSON)
                .get(URL + "person/1/bookings")
                .then()
                .log().all()
                .extract().response();
    }

    @Test
    public void addBookingTest() {
        BookingData bookingData = new BookingData("skirt", 2, 2);
        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(bookingData)
                .when()
                .post(URL + "person/1/addBooking")
                .then().log().all().extract().response();

    }
}