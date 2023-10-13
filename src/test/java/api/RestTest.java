package api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class RestTest {

    private static final RequestSpecification REQ_SPEC =
            new RequestSpecBuilder()
                    .setBaseUri("http://localhost:8080")
                    .setContentType(ContentType.JSON)
                    .build();

    @Test
    public void getBookings() {
        List<BookingPojo> bookings = given()
                .spec(REQ_SPEC)
                .basePath("/person/2/bookings")
                .when().get()
                .then().statusCode(200)
                .extract().jsonPath().getList("", BookingPojo.class);

        assertThat(bookings).extracting(BookingPojo::getOrderName).contains("iphone");
    }

    @Test
    public void createPersonTest() {
        PersonPojo personPojo = new PersonPojo();
        personPojo.setUsername("Anton");
        personPojo.setPassword("12345");

        String response = given()
                .spec(REQ_SPEC)
                .basePath("/auth/registration")
                .body(personPojo)
                .when().get()
                .then().extract().as(String.class);

        assertThat(response)
                .isEqualTo("OK");
    }

    @Test
    public void failedCreatePersonTest() {
        PersonPojo personPojo = new PersonPojo();
        personPojo.setUsername("Anton");
        personPojo.setPassword("12345");

        String response = given()
                .spec(REQ_SPEC)
                .basePath("/auth/registration")
                .body(personPojo)
                .when().get()
                .then().extract().as(String.class);

        assertThat(response)
                .isEqualTo("NOT_ACCEPTABLE");
    }

    @Test
    public void loginPerson() {
        PersonPojo personPojo = new PersonPojo();
        personPojo.setUsername("Illia");
        personPojo.setPassword("qwe");

        PersonPojo response = given()
                .spec(REQ_SPEC)
                .basePath("/auth/login")
                .body(personPojo)
                .when().get()
                .then().extract().as(PersonPojo.class);

        assertThat(response)
                .extracting(PersonPojo::getUsername).isEqualTo("Illia");
    }

    @Test
    public void addBookingTest() {
        BookingPojo bookingPojo = new BookingPojo();
        bookingPojo.setOrderName("Huawei");
        bookingPojo.setPrice(123);
        bookingPojo.setCount(3);

        String response = given()
                .spec(REQ_SPEC)
                .basePath("person/2/addBooking")
                .body(bookingPojo)
                .when().post()
                .then()
                .statusCode(200)
                .extract().as(String.class);

        assertThat(response).isEqualTo("OK");
    }


    @Test
    public void getBookingTest() {
        BookingPojo response = given()
                .spec(REQ_SPEC)
                .pathParam("orderName", "iphone")
                .basePath("booking/{orderName}")
                .when().get()
                .then().extract().as(BookingPojo.class);

        assertThat(response)
                .extracting(BookingPojo::getOrderName).isEqualTo("iphone");
    }

    @Test
    public void deleteBookingTest() {
        String response = given()
                .spec(REQ_SPEC)
                .pathParam("orderName", "telephone")
                .basePath("booking/{orderName}/delete")
                .when().post()
                .then().extract().as(String.class);

        assertThat(response)
                .isEqualTo("OK");
    }
}
