package api;

import api.pojo.Credentials;
import api.pojo.User;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static api.Spec.getBaseSpec;
import static org.apache.http.HttpStatus.SC_ACCEPTED;

public class AuthServices {
    private final String ROOT = "/auth";

    @Step("Obtain access token")
    public String accessToken(ValidatableResponse response) {
        return response
                .extract()
                .path("accessToken");
    }

    @Step("Perform registration")
    public ValidatableResponse registerUser(User user) {
        String REGISTER = ROOT + "/register";
        return getBaseSpec
                .body(user).log().all()
                .when()
                .post(REGISTER)
                .then().log().all();
    }

    @Step("Perform login")
    public ValidatableResponse loginUser(Credentials credentials) {
        String LOGIN = ROOT + "/login";
        return getBaseSpec
                .body(credentials).log().all()
                .when()
                .post(LOGIN)
                .then().log().all();
    }

    public String extractToken(String token) {
        return token.substring(7);
    }

    @Step("Delete user by token")
    public void deleteUser(String token) {
        String USER = ROOT + "/user";
        getBaseSpec
                .auth().oauth2(extractToken(token))
                .when()
                .delete(USER)
                .then()
                .assertThat()
                .statusCode(SC_ACCEPTED);
    }
}