package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class AuthTest {
    private final static String URL = "http://localhost:8080/";

    @Test
    public void checkUserRegistration() {
        PersonData personData = new PersonData("oleg", "1234");
        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(personData)
                .when()
                .get(URL + "auth/registration")
                .then().log().all().extract().response();
    }

    @Test
    public void checkUserLogging() {
        PersonData personData = new PersonData("Garry", "1234");
        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(personData)
                .when()
                .get(URL + "auth/registration")
                .then().log().all().extract().response();
    }
}
