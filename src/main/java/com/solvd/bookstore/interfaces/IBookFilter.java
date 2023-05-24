package com.solvd.bookstore.interfaces;

public interface IBookFilter<T> {
    boolean test(T book);
}
