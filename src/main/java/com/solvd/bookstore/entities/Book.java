package com.solvd.bookstore.entities;

import com.solvd.bookstore.interfaces.ISearchable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Book implements ISearchable<Book> {

    private String title;
    private int pages;
    private Author author;
    private Publisher publisher;
    private double price;
    private LocalDate publishingYear;
    private Category category;
    private String isbn;

    public Book() {}

    public Book(String title, int pages, Author author, Publisher publisher, double price, LocalDate publishingYear, Category category, String isbn) {
        this.title = title;
        this.pages = pages;
        this.author = author;
        this.publisher = publisher;
        this.price = price;
        this.publishingYear = publishingYear;
        this.category = category;
        this.isbn = isbn;
    }

    @Override
    public boolean searchByKeyword(String keyword) {
        return this.title.contains(keyword) || this.author.toString().contains(keyword) || this.publisher.toString().contains(keyword);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getPublishingYear() {
        return publishingYear;
    }

    public void setPublishingYear(LocalDate publishingYear) {
        this.publishingYear = publishingYear;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }


    public static <T extends Book> T getBookByName(String title, ArrayList<T> books) {
        for (T book : books) {
            if (book.getTitle().equals(title)) {
                return book;
            }
        }
        return null;
    }

    public static Book getBookByIsbn(String isbn, ArrayList<Book> books) {
        for (Book book: books) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", isbn=" + isbn +
                ", pages=" + pages +
                ", author=" + author.getName() +
                ", publisher=" + publisher.getName() +
                ", price=" + price +
                ", publishingYear=" + publishingYear +
                ", category='" + category + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;
        return getTitle().equals(book.getTitle()) && getAuthor().equals(book.getAuthor()) && getPublisher().equals(book.getPublisher()) && getIsbn().equals(book.getIsbn());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getAuthor(), getPublisher(), getIsbn());
    }
}

