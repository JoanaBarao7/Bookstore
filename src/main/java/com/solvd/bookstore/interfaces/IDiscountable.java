package com.solvd.bookstore.interfaces;

public interface IDiscountable {

    double calculateDiscount(double price);

    boolean codeMatches(String code);

}
