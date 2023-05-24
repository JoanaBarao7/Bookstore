package com.solvd.bookstore.entities;

import com.solvd.bookstore.data.BooksData;

import java.util.ArrayList;

public class Inventory {

    private ArrayList<Book> books;
    private BooksData booksData;

    public Inventory() {
        this.books = new ArrayList<>();
    }

    public void updateBooksData(){
        booksData.setBooks(books);
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public long getCountOfBook(String nameOfTheBook) {
        int count = 0;
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(nameOfTheBook)) {
                count++;
            }
        }
        return count;
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "books=" + books +
                '}';
    }
}