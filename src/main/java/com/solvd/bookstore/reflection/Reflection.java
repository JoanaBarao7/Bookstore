package com.solvd.bookstore.reflection;

import com.solvd.bookstore.BookStore;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.logging.Logger;

public class Reflection {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        BookStore bookstore = new BookStore(); // Instantiate a BookStore object

        Class<?> bookStoreClass = BookStore.class;

        // Print fields
        Field[] fields = bookStoreClass.getDeclaredFields();
        Logger logger = Logger.getLogger(Reflection.class.getName());
        logger.info("Fields:");
        for (Field field : fields) {
            logger.info(field.getName() + "\n");
        }

        // Print methods
        Method[] methods = bookStoreClass.getDeclaredMethods();
        logger.info("Methods:");
        for (Method method : methods) {
            logger.info(method.getName() + "\n");

        }

        // Print constructors
        Constructor<?>[] constructors = bookStoreClass.getDeclaredConstructors();
        logger.info("Constructors:");
        for (Constructor<?> constructor : constructors) {
            logger.info(constructor.toString());
        }
    }
}
