package com.test.requests;

import com.test.models.dto.OrderDto;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static com.test.constants.Endpoint.INVENTORY;
import static com.test.constants.Endpoint.ORDER;

public class StoreController extends BaseController {
    @Step("Get pet inventories by status")
    public Response getPetInventories() {
        return getRequest.doGet(INVENTORY);
    }

    @Step("Get purchase order by ID")
    public Response getPurchaseByID(long orderId) {
        return getRequest.doGet(ORDER + String.format("/%d", orderId));
    }

    @Step("Place an order for a pet")
    public Response postPlaceAnOrderForPet(OrderDto orderDto) {
        return postRequest.doPost(ORDER, orderDto);
    }

    @Step("Delete purchase order by ID")
    public Response deletePurchaseOrderByID(long orderId) {
        return deleteRequest.doDelete(ORDER + String.format("/%d", orderId));
    }
}
