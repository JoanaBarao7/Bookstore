package com.solvd.bookstore.entities;

import com.solvd.bookstore.interfaces.IPaymentMethod;

public class Paypal implements IPaymentMethod {

    private String email;
    private String password;

    public Paypal(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paying $" + amount + " using PayPal");

        // Logic to connect to PayPal API and process payment
    }

    @Override
    public String toString() {
        return "Paypal{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
