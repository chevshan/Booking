package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class BookingTest {
    private final static String URL = "http://localhost:8080/";

    @Test
    public void checkBookingByOrderName() {
        Response response = RestAssured
                .given()
                .when()
                .contentType(ContentType.JSON)
                .get(URL + "booking/kish")
                .then()
                .log().all()
                .extract().response();
    }

    @Test
    public void checkDeleteBooking() {
        Response response = RestAssured
                .given()
                .when()
                .post(URL + "booking/telephone/delete")
                .then().log().all().extract().response();
    }
}
