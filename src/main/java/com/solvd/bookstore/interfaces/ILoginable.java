package com.solvd.bookstore.interfaces;

public interface ILoginable {

    boolean changeUserName(String username, String newUserName);

    boolean changePassword(String oldPassword, String newPassword);



}
