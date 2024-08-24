package com.test.core;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class GetRequest extends ApiClient {
    public Response doGet(String endpoint) {
        return given()
                .spec(setRequestSpecification())
                .when()
                .get(endpoint)
                .then()
                .spec(getResponseSpecification())
                .extract()
                .response();
    }
}
