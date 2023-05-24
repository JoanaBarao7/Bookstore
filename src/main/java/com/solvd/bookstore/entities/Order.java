package com.solvd.bookstore.entities;

import com.solvd.bookstore.interfaces.IPaymentMethod;

import java.time.LocalDate;
import java.util.ArrayList;

public class Order {

    private double totalPrice;
    private ArrayList<Book> books;

    private LocalDate orderDate;

    private IPaymentMethod paymentMethod;

    private Shipping shipping;

    public Order(ArrayList<Book> books, IPaymentMethod paymentMethod, Shipping shipping, double totalPrice) {
        this.books = books;
        this.orderDate = LocalDate.now();
        this.paymentMethod = paymentMethod;
        this.shipping = shipping;
        this.totalPrice = totalPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public IPaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(IPaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Shipping getShipping() {
        return shipping;
    }

    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Order{" +
                "totalPrice=" + totalPrice +
                ", books=" + books +
                ", orderDate=" + orderDate +
                ", paymentMethod=" + paymentMethod +
                ", shipping=" + shipping +
                '}';
    }
}
