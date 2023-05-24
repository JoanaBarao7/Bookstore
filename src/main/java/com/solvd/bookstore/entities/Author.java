package com.solvd.bookstore.entities;

import java.util.ArrayList;

public class Author extends Person {

    private String biography;

    public Author() {}

    public Author(String name, String emailAddress, String biography) {
        super(name, emailAddress);
        this.biography = biography;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public static <T extends Author> T getAuthorByName(String name, ArrayList<T> authors) {
        for (T author : authors) {
            if (author.getName().equalsIgnoreCase(name))
                return author;
        }
        return null;
    }

    @Override
    public String toString() {
        return "Author{" +
                "biography='" + biography + '\'' +
                '}';
    }
}
