package com.test.core;

import com.test.constants.Environment;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.log4j.Logger;

public class ApiClient {
    private static final Logger logger = Logger.getLogger(ApiClient.class);
    private static final String ACCEPT = "accept";
    private static final String APP_JSON = "application/json";

    protected RequestSpecification setRequestSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri(Environment.BASE_URI)
                .addHeader(ACCEPT, APP_JSON)
                .addFilter(new AllureRestAssured())
                .addFilter(new CustomLogFilter())
                .log(LogDetail.ALL)
                .build();
    }

    protected ResponseSpecification getResponseSpecification() {
        return new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .build();
    }

    private static class CustomLogFilter implements Filter {
        @Override
        public Response filter(FilterableRequestSpecification requestSpec,
                               FilterableResponseSpecification responseSpec,
                               FilterContext context) {
            logger.info("Request: " + requestSpec.getMethod() + " " + requestSpec.getURI());
            logger.info("Request Headers: " + requestSpec.getHeaders());
            logger.info("Request Body: " + requestSpec.getBody());

            Response response = context.next(requestSpec, responseSpec);

            logger.info("Response Status Code: " + response.getStatusCode());
            logger.info("Response Body: " + response.getBody().asString());
            logger.info("");

            return response;
        }
    }
}