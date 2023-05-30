package com.solvd.bookstore.lambdas;

import com.solvd.bookstore.entities.Author;
import com.solvd.bookstore.entities.Book;
import com.solvd.bookstore.interfaces.IFilterByPrice;

import java.util.ArrayList;
import java.util.List;

public class lambdaFunction implements IFilterByPrice{

        public static void main(String[] args) {
            List<Book> books = new ArrayList<>();
            books.add(new Book("Harry Potter and the Philosopher's Stone", new Author("J.K.Rowling"), 19.99));
            books.add(new Book("The Shining", new Author("Stephen King"), 9.99));
            books.add(new Book("Murder on the Orient Express", new Author("Agatha Christie"), 24.99));
            books.add(new Book("The Great Gatsby", new Author("F. Scott Fitzgerald"), 22.99));

            IFilterByPrice filter = book -> book.getPrice() < 20.0;

            List<Book> filteredBooks = filterBooks(books, filter);
            for (Book book : filteredBooks) {
                System.out.println(book.getTitle());
            }
        }

        private static List<Book> filterBooks(List<Book> books, IFilterByPrice filter) {
            List<Book> filtered = new ArrayList<>();
            for (Book book : books) {
                if (filter.filter(book)) {
                    filtered.add(book);
                }
            }
            return filtered;
        }

    @Override
    public boolean filter(Book book) {
        return false;
    }
}


