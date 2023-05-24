package com.solvd.bookstore.entities;

import java.util.ArrayList;
import java.util.Scanner;

public class Customer extends User {

    private ArrayList<Order> orders;
    private Address address;

    private ShoppingCart cart;

    public Customer(String name, String emailAddress, String username, String password, Address address) {
        super(name, emailAddress, username, password);
        this.address = address;
        this.cart = new ShoppingCart();
        this.orders = new ArrayList<>();
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public static Customer createCustomer() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your full name.");
        String name = scanner.nextLine();

        System.out.println("Enter your email address.");
        String email = scanner.nextLine();

        System.out.println("Enter your username.");
        String username = scanner.nextLine();

        System.out.println("Enter your password");
        String password = scanner.nextLine();

        System.out.println("Enter your street.");
        String street = scanner.nextLine();

        System.out.println("Enter your city.");
        String city = scanner.nextLine();

        System.out.println("Enter your state.");
        String state = scanner.nextLine();

        System.out.println("Enter your zip code");
        String zipCode = scanner.nextLine();

        System.out.println("Enter your country.");
        String country = scanner.nextLine();

        Address address = new Address(street, city, state, zipCode, country);

        return new Customer(name, email, username, password, address);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "orders=" + orders +
                ", address=" + address +
                ", cart=" + cart +
                '}';
    }
}
