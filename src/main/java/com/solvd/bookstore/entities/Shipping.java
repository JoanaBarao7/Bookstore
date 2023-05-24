package com.solvd.bookstore.entities;

public class Shipping {

    private double cost;

    private ShippingMethod shippingMethod;

    private Address address;

    public Shipping(double cost, ShippingMethod shippingMethod, Address address) {
        this.cost = cost;
        this.shippingMethod = shippingMethod;
        this.address = address;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public ShippingMethod getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(ShippingMethod shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Shipping{" +
                "cost=" + cost +
                ", shippingMethod=" + shippingMethod +
                ", address=" + address +
                '}';
    }
}

