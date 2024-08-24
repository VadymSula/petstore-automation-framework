package com.test.data;

import org.testng.annotations.DataProvider;

public class OrderIdDP {
    @DataProvider(name = "Positive")
    public Object[][] getOrderIdsForPositiveCheck() {
        return new Integer[][]{{1}, {2}, {9}, {10}};
    }

    @DataProvider(name = "Negative")
    public Object[][] getOrderIdsForNegativeCheck() {
        return new Integer[][]{{0}, {-1}, {11}};
    }
}
