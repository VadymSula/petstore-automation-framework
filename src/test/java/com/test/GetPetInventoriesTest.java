package com.test;

import com.test.requests.StoreController;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.test.constants.ErrorMessage.CODE_RESPONSE_NOT_EQUALS_TO_EXPECTED;

public class GetPetInventoriesTest {

    @Test
    public void getPetInventoriesTest() {
        var storeController = new StoreController();
        Response response = storeController.getPetInventories();
        int actualResponseCode = response.getStatusCode();
        Assert.assertEquals(actualResponseCode, HttpStatus.SC_OK, CODE_RESPONSE_NOT_EQUALS_TO_EXPECTED);
    }
}
