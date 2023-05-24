package com.solvd.bookstore.entities;

import java.util.ArrayList;

public class Publisher extends Person{
        private Address address;

        public Publisher() {}

        public Publisher(String name, String email, Address address) {
            super(name,email);
            this.address = address;
        }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public static <T extends Publisher> T getPublisherByName(String name, ArrayList<T> publishers) {
        for (T publisher : publishers) {
            if (publisher.getName().equalsIgnoreCase(name))
                return publisher;
        }
        return null;
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "address=" + address +
                '}';
    }
}
