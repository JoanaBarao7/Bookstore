package com.solvd.bookstore.entities;

import java.util.ArrayList;

public class ShoppingCart {

    private ArrayList<Book> books;

    private Coupon coupon;

    public ShoppingCart() {
        this.books = new ArrayList<>();

    }

    public void addBook(Book book) {
        books.add(book);
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public void emptyCart() {
        books = new ArrayList<>();
    }
    public double getTotalPrice() {
        double totalPrice = 0;
        for (Book book : books) {
            totalPrice += book.getPrice();
        }
        return totalPrice;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "books=" + books +
                ", coupon=" + coupon +
                '}';
    }
}

