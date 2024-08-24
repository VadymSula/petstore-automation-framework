package com.test;

import com.test.models.enums.OrderStatus;
import com.test.models.dto.OrderDto;
import com.test.models.dto.errors.ErrorDto;
import com.test.requests.StoreController;
import com.test.utils.ResponseParser;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.time.LocalDateTime;

import static com.test.constants.ErrorMessage.*;

public class DeletePurchaseOrderTest {
    private final StoreController storeController = new StoreController();
    private OrderDto responseAfterOrderCreation;

    @BeforeClass
    public void createAnOrderForPetWithValidData() {
        OrderDto orderDto = OrderDto.builder()
                .petId(1)
                .quantity(1)
                .shipDate(LocalDateTime.now().toString())
                .status(OrderStatus.DELIVERED.value)
                .complete(true)
                .build();
        responseAfterOrderCreation = ResponseParser.getEntity(storeController.postPlaceAnOrderForPet(orderDto), OrderDto.class);
    }

    @Test
    public void deletePurchaseOrderByIDTest() {
        Response response = storeController.deletePurchaseOrderByID(responseAfterOrderCreation.getId());
        int actualResponseCode = response.getStatusCode();
        Assert.assertEquals(actualResponseCode, HttpStatus.SC_OK, String.format("Response status code: %d isn't equals to expected: %d", actualResponseCode, HttpStatus.SC_OK));
    }

    @Test(dependsOnMethods = "deletePurchaseOrderByIDTest")
    public void deletePurchaseByIDWhichWasDeletedBeforeTest() {
        SoftAssert softAssert = new SoftAssert();
        Response response = storeController.deletePurchaseOrderByID(responseAfterOrderCreation.getId());
        int actualResponseCode = response.getStatusCode();
        String actualErrorMessage = ResponseParser.getEntity(response, ErrorDto.class).getMessage();
        softAssert.assertEquals(actualResponseCode, HttpStatus.SC_NOT_FOUND, CODE_RESPONSE_NOT_EQUALS_TO_EXPECTED);
        softAssert.assertEquals(actualErrorMessage, ORDER_NOT_FOUND, RESPONSE_MESSAGE_NOT_EQUALS_TO_EXPECTED);
        softAssert.assertAll();
    }

    @Parameters("zeroId")
    @Test
    public void deletePurchaseWithZeroIDTest(long orderId) {
        Response response = storeController.deletePurchaseOrderByID(orderId);
        int actualResponseCode = response.getStatusCode();
        Assert.assertEquals(actualResponseCode, HttpStatus.SC_BAD_REQUEST, CODE_RESPONSE_NOT_EQUALS_TO_EXPECTED);
    }
}
