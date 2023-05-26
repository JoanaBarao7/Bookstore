package com.solvd.bookstore.interfaces;

import com.solvd.bookstore.entities.Book;

@FunctionalInterface
public interface IFilterByPrice {
  boolean filter(Book book);
}
