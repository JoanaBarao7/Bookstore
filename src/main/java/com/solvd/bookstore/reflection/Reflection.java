package com.solvd.bookstore.reflection;

import com.solvd.bookstore.BookStore;
import com.solvd.bookstore.entities.Address;
import com.solvd.bookstore.entities.Admin;
import com.solvd.bookstore.entities.Customer;
import com.solvd.bookstore.entities.User;
import com.solvd.bookstore.linkedList.UsersLinkedList;

import java.lang.reflect.Field;
import java.util.logging.Logger;

public class Reflection {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        BookStore bookstore = new BookStore(); // Instantiate a BookStore object

        Field usersField = BookStore.class.getDeclaredField("users");
        usersField.setAccessible(true);

        UsersLinkedList<User> users = (UsersLinkedList<User>) usersField.get(bookstore);

        User admin1 = new Admin(
                "admin1",
                "admin1@email.com",
                "admin1",
                "admin1" );

        User admin2 = new Admin(
                "admin2",
                "admin2@email.com",
                "admin2",
                "admin2" );

        User customer1 = new Customer(
                "customer1",
                "customer1@mail.com",
                "customer1",
                "customer1",
                new Address(
                        " Two Street",
                        "Los Angeles",
                        "CA",
                        "94487",
                        "USA")
        );


        User customer2 = new Customer(
                "customer2",
                "customer2@mail.com",
                "customer2",
                "customer2",
                new Address(
                        " One Street",
                        "New York",
                        "NY",
                        "98787",
                        "USA")
        );

        users.addFirst(admin1);
        users.addLast(customer2);
        users.insertAt(1, admin1);

        // Printing the modified users
        Logger logger = Logger.getLogger(Reflection.class.getName());
        for (User user : users) {
            logger.info(user.toString());
        }
    }
}
