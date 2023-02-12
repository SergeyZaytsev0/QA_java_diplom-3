package api;

import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class Spec {

    public static final String BASE_URL = "https://stellarburgers.nomoreparties.site/api";

    public static final RequestSpecification getBaseSpec = given()
            .header("Content-Type", "application/json")
            .baseUri(BASE_URL);
}
