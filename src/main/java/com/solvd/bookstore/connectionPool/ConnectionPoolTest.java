package com.solvd.bookstore.connectionPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConnectionPoolTest {

    private static final int POOL_SIZE = 5; // The number of connections in the pool

    public static void main(String[] args) {
        // Create an instance of the connection pool
        ConnectionPool connectionPool = ConnectionPool.getInstance(POOL_SIZE);

        // Create an ExecutorService with 7 threads
        ExecutorService executorService = Executors.newFixedThreadPool(7);

        // Submit tasks to obtain and release connections concurrently
        for (int i = 0; i < 5; i++) {
            executorService.submit(() -> {
                try {
                    Connection connection = connectionPool.getConnection();
                    System.out.println("Connection obtained: " + connection);
                    // Perform some work with the connection
                    Thread.sleep(2000);
                    connectionPool.releaseConnection(connection);
                    System.out.println("Connection released: " + connection);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        // Wait for the connections to be obtained and released
        executorService.shutdown();
    }

}
