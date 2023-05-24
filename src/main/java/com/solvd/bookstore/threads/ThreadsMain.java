package com.solvd.bookstore.threads;

public class ThreadsMain {
    public static void main(String[] args) {
        //Runnable and thread

        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Custom thread (runnable) message: " + Thread.currentThread().getId());
            }
        };

        new Thread(runnable).start();

        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Custom thread (thread) message: " + Thread.currentThread().getId());
        }).start();

        System.out.println("Main thread message: " + Thread.currentThread().getId());
    }
}

