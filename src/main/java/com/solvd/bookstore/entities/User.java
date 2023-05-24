package com.solvd.bookstore.entities;

import com.solvd.bookstore.interfaces.ILoginable;
import com.solvd.bookstore.linkedList.UsersLinkedList;

import java.util.Objects;

public abstract class User extends Person implements ILoginable {
    private String username;
    private String password;

    public User(String name, String emailAddress, String username, String password) {
        super(name, emailAddress);
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean changeUserName(String username, String newUsername) {
        if (getUsername().equals(username)) {
            setUsername(newUsername);
            return true;
        }
        return false;
    }


    @Override
    public boolean changePassword(String oldPassword, String newPassword) {
        if (this.getPassword().equals(password)) {
            this.setPassword(newPassword);
            return true;
        }
        return false;
    }

    public static User login(String username, String password, UsersLinkedList<User> users) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                if (user.getPassword().equals(password)) {
                    return user;
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass()!= o.getClass()) return false;
        if (!super.equals(o)) return false;

        User user = (User) o;

        if (!username.equals(user.username)) return false;
        return password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getPassword());
    }
}
