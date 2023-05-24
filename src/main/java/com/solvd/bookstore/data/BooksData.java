package com.solvd.bookstore.data;

import com.solvd.bookstore.entities.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class BooksData {

    public static ArrayList<Book> books;



    public ArrayList<Book> setBooks(ArrayList<Book> books) {
        this.books = books;


        //  ArrayList<Book> books = new ArrayList<>();

        //add books
        books.add(
                new Book(
                        "To Kill a Mockingbird",
                        281,
                        new Author(
                                "Harper Lee",
                                "hlee@harpercollins.com",
                                "Nelle Harper Lee was an American novelist best known for her 1960 novel To Kill a Mockingbird."),
                        new Publisher(
                                "HarperCollins",
                                "info@harpercollins.com",
                                new Address("195 Broadway", "New York", "NY", "10007", "USA")),
                        9.99,
                        LocalDate.of(1960, 7, 11),
                        Category.FICTION,
                        "978-0446310789")
        );

        books.add(new Book(
                "The Great Gatsby",
                218,
                new Author(
                        "F. Scott Fitzgerald",
                        "fitzgerald@bookstore.com",
                        "F. Scott Fitzgerald was an American novelist and short-story writer, best known for his novel The Great Gatsby."),
                new Publisher(
                        "Scribner",
                        "scribner@bookstore.com",
                        new Address("123 Main St", "New York", "NY", "10001", "USA")),
                10.99,
                LocalDate.of(1925, 4, 10),
                Category.FICTION,
                "978-0743273565"
        ));

        books.add(new Book(
                "The Hitchhiker's Guide to the Galaxy",
                215,
                new Author(
                        "Douglas Adams",
                        "adams@bookstore.com",
                        "Douglas Adams was an English author, best known for his science fiction series The Hitchhiker's Guide to the Galaxy."),
                new Publisher(
                        "Pan Books",
                        "panbooks@bookstore.com",
                        new Address("456 Elm St", "London", "N1", "0XA", "UK")),
                8.99,
                LocalDate.of(1979, 10, 12),
                Category.NON_FICTION,
                "978-0330508537"
        ));

        books.add(new Book(
                "The Catcher in the Rye",
                234,
                new Author(
                        "J.D. Salinger",
                        "salinger@bookstore.com",
                        "J.D. Salinger was an American author, best known for his novel The Catcher in the Rye."),
                new Publisher(
                        "Little, Brown and Company",
                        "littlebrown@bookstore.com",
                        new Address("789 Oak St", "Boston", "MA", "02108", "USA")),
                9.99,
                LocalDate.of(1951, 7, 16),
                Category.NON_FICTION,

                "978-0316769174"
        ));

        books.add(new Book(
                "The Lord of the Rings: The Fellowship of the Ring",
                198,
                new Author(
                        "J.R.R. Tolkien",

                        "tolkien@bookstore.com",
                        "J.R.R. Tolkien was an American author, best known for his novel The Lord of the Rings: The Fellowship of the Ring."),
                new Publisher(
                        "J.R.R. Tolkien",

                        "tolkien@bookstore.com",
                        new Address("123 Main St", "New York", "NY", "10001", "USA")),
                10.99,
                LocalDate.of(1960, 7, 11),
                Category.FICTION,
                "978-0446310789"
        ));

        return books;
    }

    public static ArrayList<Book> getBooks() {
        return books;
    }
}










