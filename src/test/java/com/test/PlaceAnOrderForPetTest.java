package com.test;

import com.test.data.OrderDP;
import com.test.models.dto.OrderDto;
import com.test.requests.StoreController;
import com.test.utils.ResponseParser;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.test.constants.ErrorMessage.CODE_RESPONSE_NOT_EQUALS_TO_EXPECTED;
import static com.test.constants.ErrorMessage.RESPONSE_BODY_NOT_EQUALS_TO_EXPECTED;

public class PlaceAnOrderForPetTest {
    private final StoreController storeController = new StoreController();

    @Test(dataProvider = "OrderProvider (positive)", dataProviderClass = OrderDP.class)
    public void placeAnOrderForPetPositiveTest(OrderDto orderDto) {
        SoftAssert softAssert = new SoftAssert();
        Response response = storeController.postPlaceAnOrderForPet(orderDto);
        OrderDto orderResponse = ResponseParser.getEntity(response, OrderDto.class);
        int actualResponseCode = response.getStatusCode();
        softAssert.assertEquals(actualResponseCode, HttpStatus.SC_OK, CODE_RESPONSE_NOT_EQUALS_TO_EXPECTED);
        softAssert.assertEquals(orderResponse, orderDto, RESPONSE_BODY_NOT_EQUALS_TO_EXPECTED);
        softAssert.assertAll();
    }

    @Test(dataProvider = "OrderProvider (negative)", dataProviderClass = OrderDP.class)
    public void placeAnOrderForPetNegativeTest(OrderDto orderDto) {
        Response response = storeController.postPlaceAnOrderForPet(orderDto);
        int actualResponseCode = response.getStatusCode();
        Assert.assertEquals(actualResponseCode, HttpStatus.SC_BAD_REQUEST, CODE_RESPONSE_NOT_EQUALS_TO_EXPECTED);
    }
}
