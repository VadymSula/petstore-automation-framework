package com.test.core;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PostRequest extends ApiClient {
    public Response doPost(String endpoint, Object body) {
        return given()
                .spec(setRequestSpecification())
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post(endpoint)
                .then()
                .spec(getResponseSpecification())
                .extract()
                .response();
    }
}
