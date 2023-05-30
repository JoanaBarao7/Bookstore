package com.solvd.bookstore.connectionPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
    private final int poolSize; // The maximum number of connections in the pool
    private final BlockingQueue<Connection> connections; // The collection to hold the connections
    private final Object lock = new Object(); // A lock object for synchronization
    private static ConnectionPool instance = null; // The singleton instance of the connection pool
    private int availableConnections; // The number of connections


    private ConnectionPool(int poolSize) {
        this.poolSize = poolSize;
        this.connections = new ArrayBlockingQueue<>(poolSize);
        this.availableConnections = poolSize;
    }

    public static ConnectionPool getInstance(int poolSize) {
        if (instance == null) {
            synchronized (ConnectionPool.class) {
                if (instance == null) {
                    instance = new ConnectionPool(poolSize);
                    instance.initializeConnections();
                }
            }
        }
        return instance;
    }

    private Connection createConnection() {
        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String username = "my-username";
        String password = "my-password";
        return new Connection(url, username, password);
    }

    public Connection getConnection() throws InterruptedException {
        synchronized (lock) {
            while (availableConnections == 0) {
                lock.wait();
            }
            Connection connection = connections.take();
            availableConnections--;
            return connection;
        }
    }

    public void releaseConnection(Connection connection) {
        synchronized (lock) {
            connections.offer(connection);
            availableConnections++;
            lock.notify();
        }
    }

    private void initializeConnections() {
        for (int i = 0; i < poolSize; i++) {
            Connection connection = createConnection(); // Mock or create a real connection here
            connections.offer(connection);
        }
    }
}
