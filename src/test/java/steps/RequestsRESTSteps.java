package steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.*;
import static specs.SpecRestApi.defaultLoggingRequestSpec;

public class RequestsRESTSteps {
    @Step("Get list of data")
    public static Response getAllData() {
        return given()
                .spec(defaultLoggingRequestSpec)
                .when()
                .get()
                .then()
                .statusCode(SC_OK)
                .extract()
                .response();
    }

    @Step("Get specific data by Id")
    public static Response getDataById(int id) {
        return given()
                .spec(defaultLoggingRequestSpec)
                .when()
                .get(String.valueOf(id))
                .then()
                .statusCode(SC_OK)
                .extract()
                .response();
    }

    @Step("Post Request to create data")
    public static Response sendPostRequestToCreateData(String jsonBody) {
        return given()
                .spec(defaultLoggingRequestSpec)
                .when()
                .post(jsonBody)
                .then()
                .statusCode(SC_CREATED)
                .extract()
                .response();
    }

    @Step("Post Request with incorrect Data")
    public static Response sendPostRequestWithIncorrectData(String jsonBody) {
        return given()
                .spec(defaultLoggingRequestSpec)
                .when()
                .post(jsonBody)
                .then()
                .statusCode(SC_BAD_REQUEST)
                .extract()
                .response();
    }

    @Step("Post Request to update data")
    public static Response sendPostRequestToUpdateData(String jsonBody) {
        return given()
                .spec(defaultLoggingRequestSpec)
                .when()
                .post(jsonBody)
                .then()
                .statusCode(SC_OK)
                .extract()
                .response();
    }

    @Step("Put Request to delete data")
    public static Response sendDeleteRequest(int id) {
        return given()
                .spec(defaultLoggingRequestSpec)
                .when()
                .delete(String.valueOf(id))
                .then()
                .statusCode(SC_NO_CONTENT)
                .extract()
                .response();
    }
}
