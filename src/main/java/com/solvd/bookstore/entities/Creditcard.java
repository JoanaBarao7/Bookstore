package com.solvd.bookstore.entities;

import com.solvd.bookstore.interfaces.IPaymentMethod;

import java.time.LocalDate;


public class Creditcard implements IPaymentMethod {

    private String cardNumber;
    private LocalDate expirationDate;
    private String cvv;

    public Creditcard(String cardNumber, LocalDate expirationDate, String cvv) {
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " with credit card ending in " + cardNumber.substring(cardNumber.length() - 5));

        // Logic to connect to Stripe API and process payment
    }

    @Override
    public String toString() {
        return "Creditcard{" +
                "cardNumber='" + cardNumber + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", cvv='" + cvv + '\'' +
                '}';
    }
}
