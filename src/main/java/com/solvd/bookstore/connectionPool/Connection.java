package com.solvd.bookstore.connectionPool;

public class Connection {
    private String url;
    private String username;
    private String password;

    public Connection(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
        // Additional code to establish the connection, if needed
    }

    // Add additional methods specific to your connection handling needs

    @Override
    public String toString() {
        return "Connection [url=" + url + ", username=" + username + "]";
    }
}
