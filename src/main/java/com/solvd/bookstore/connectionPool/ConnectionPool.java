package com.solvd.bookstore.connectionPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
    private final int poolSize; // The maximum number of connections in the pool
    private final BlockingQueue<Connection> connections; // The collection to hold the connections
    private final Object lock = new Object(); // A lock object for synchronization
    private static ConnectionPool instance = null; // The singleton instance of the connection pool

    private ConnectionPool(int poolSize) {
        this.poolSize = poolSize;
        this.connections = new ArrayBlockingQueue<>(poolSize);
    }

    public static ConnectionPool getInstance(int poolSize) {
        if (instance == null) {
            synchronized (ConnectionPool.class) {
                if (instance == null) {
                    instance = new ConnectionPool(poolSize);
                }
            }
        }
        return instance;
    }

    private Connection createConnection() {
        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String username = "your-username";
        String password = "your-password";
        return new Connection(url, username, password);
    }

    public Connection getConnection() throws InterruptedException {
        return connections.take();
    }
    public void releaseConnection(Connection connection) {
        connections.offer(connection);
    }
    private void initializeConnections() {
        for (int i = 0; i < poolSize; i++) {
            Connection connection = createConnection(); // You can mock or create a real connection here
            connections.offer(connection);
        }
    }
}
