package com.solvd.bookstore.entities;

public enum ShippingMethod {
    EXPRESS_DELIVERY(10.0, "Express"),
    STANDARD_DELIVERY(5.0, "Standard");

    private double cost;
    private String label;

    ShippingMethod(double cost, String label) {
        this.cost = cost;
        this.label = label;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
