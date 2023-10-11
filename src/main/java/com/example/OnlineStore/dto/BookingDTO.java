package com.example.OnlineStore.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class BookingDTO {

    @NotNull(message = "Order name should not be empty!")
    @Size(min = 3, max = 50, message = "Order name must be between 3 and 50 characters")
    private String orderName;

    @NotNull(message = "Price should not be empty!")
    @Min(1)
    @Max(9999)
    private int price;

    @NotNull(message = "Count should not be empty!")
    @Min(1)
    @Max(999)
    private int count;


    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
