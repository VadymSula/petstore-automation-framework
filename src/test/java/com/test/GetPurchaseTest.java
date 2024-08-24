package com.test;

import com.test.data.OrderIdDP;
import com.test.requests.StoreController;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.test.constants.ErrorMessage.CODE_RESPONSE_NOT_EQUALS_TO_EXPECTED;

public class GetPurchaseTest {
    private final StoreController storeController = new StoreController();

    @Test(dataProvider = "Positive", dataProviderClass = OrderIdDP.class)
    public void getPurchaseByIDPositiveTest(long orderId) {
        Response response = storeController.getPurchaseByID(orderId);
        int actualResponseCode = response.getStatusCode();
        Assert.assertEquals(actualResponseCode, HttpStatus.SC_OK, CODE_RESPONSE_NOT_EQUALS_TO_EXPECTED);
    }

    @Test(dataProvider = "Negative", dataProviderClass = OrderIdDP.class)
    public void getPurchaseByIDNegativeTest(long orderId) {
        Response response = storeController.getPurchaseByID(orderId);
        int actualResponseCode = response.getStatusCode();
        Assert.assertEquals(actualResponseCode, HttpStatus.SC_BAD_REQUEST, CODE_RESPONSE_NOT_EQUALS_TO_EXPECTED);
    }
}
