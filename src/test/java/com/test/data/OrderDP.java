package com.test.data;

import com.test.models.enums.OrderStatus;
import com.test.models.dto.OrderDto;
import org.testng.annotations.DataProvider;

import java.time.LocalDateTime;

public class OrderDP {
    @DataProvider(name = "OrderProvider (positive)")
    public static OrderDto[][] getValidDataForOrder() {
        return new OrderDto[][]{{OrderDto.builder()
                .petId(1)
                .quantity(1)
                .shipDate(LocalDateTime.now().toString())
                .status(OrderStatus.DELIVERED.value)
                .complete(true)
                .build()
        }, {OrderDto.builder()
                .petId(2)
                .quantity(1)
                .shipDate(LocalDateTime.now().toString())
                .status(OrderStatus.APPROVED.value)
                .complete(true)
                .build()
        }, {OrderDto.builder()
                .petId(3)
                .quantity(1)
                .shipDate(LocalDateTime.now().toString())
                .status(OrderStatus.PLACED.value)
                .complete(true)
                .build()
        }};
    }

    @DataProvider(name = "OrderProvider (negative)")
    public static OrderDto[][] getInValidDataForOrder() {
        return new OrderDto[][]{{OrderDto.builder()
                .petId(-1)
                .quantity(1)
                .shipDate(LocalDateTime.now().toString())
                .status(OrderStatus.DELIVERED.value)
                .complete(true)
                .build()
        }, {OrderDto.builder()
                .petId(2)
                .quantity(-1)
                .shipDate(LocalDateTime.now().toString())
                .status(OrderStatus.APPROVED.value)
                .complete(true)
                .build()
        }, {OrderDto.builder()
                .petId(3)
                .quantity(1)
                .shipDate(LocalDateTime.now().toString())
                .status("test")
                .complete(true)
                .build()
        }};
    }
}
