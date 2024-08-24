package com.test.core;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class DeleteRequest extends ApiClient {
    public Response doDelete(String endpoint) {
        return given()
                .spec(setRequestSpecification())
                .contentType(ContentType.JSON)
                .when()
                .delete(endpoint)
                .then()
                .spec(getResponseSpecification())
                .extract()
                .response();
    }
}
