package com.test.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.apache.log4j.Logger;

import java.io.IOException;

public class ResponseParser {
    private static final Logger LOGGER = Logger.getLogger(ResponseParser.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T getEntity(Response response, Class<T> tClass) {
        try {
            return objectMapper.readValue(response.getBody().print(), tClass);
        } catch (IOException e) {
            LOGGER.error(String.format("Failed to parse/mapping response for: %s", tClass.toString()));
            throw new RuntimeException(e);
        }
    }
}
