package com.solvd.bookstore.entities;

import com.solvd.bookstore.interfaces.IDiscountable;

public enum Coupon implements IDiscountable {
    OFF20(20, "OFF20"),
    OFF30(30, "OFF30");

    private final double discountInPercent;
    private final String code;

    Coupon(double discountInPercent, String code) {
        this.discountInPercent = discountInPercent;
        this.code = code;
    }

    public double getDiscountInPercent() {
        return discountInPercent;
    }

    public String getCode() {
        return code;
    }

    @Override
    public double calculateDiscount(double price) {
        return price - (price * discountInPercent / 100);
    }

    @Override
    public boolean codeMatches(String code) {
        return this.code.equals(code);
    }
}
