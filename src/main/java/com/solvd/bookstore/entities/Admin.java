package com.solvd.bookstore.entities;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Scanner;

public class Admin extends User {


    public Admin(String name, String emailAddress, String username, String password) {
        super(name, emailAddress, username, password);
    }

    public Book createBook() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the title of the new book.");
        String title = scanner.nextLine();

        System.out.println("Enter the number of the book's pages ");
        int numberOfPages = scanner.nextInt();

        Author author = createAuthor();

        Publisher publisher = createPublisher();

        System.out.println("What is the price of the book ");
        double price = scanner.nextDouble();

        System.out.println("What year was the book published?");
        int year = scanner.nextInt();
        LocalDate publishingDate = LocalDate.of(year, 1,1);

        scanner.nextLine();

        System.out.println("What is the category of the book?");
        String categoryName = scanner.nextLine();
        Category category = Category.getCategoryByName(categoryName);

        System.out.println("What is the isbn of the book?");
        String isbn = scanner.nextLine();

        return new Book(title, numberOfPages, author, publisher, price, publishingDate, category, isbn);

    }

    public Author createAuthor() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("What is the name of the author?");
        String name = scanner.nextLine();

        System.out.println("What is the biography of the author");
        String biography = scanner.nextLine();

        System.out.println("What is the email of the author?");
        String email = scanner.nextLine();

        return new Author(name, email, biography);
    }

    public Publisher createPublisher() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What is the name of the publisher?");
        String name = scanner.nextLine();
        System.out.println("What is the email of the publisher?");
        String email = scanner.nextLine();
        Address address = createAddress();
        System.out.println("What is the phone number of the publisher?");
        String phone = scanner.nextLine();

        return new Publisher(name, email, address);
    }

    public Address createAddress() {
        return null;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Admin admin) {
            return Objects.equals(this.getName(), admin.getName());
        }
        return false;
    }


}
