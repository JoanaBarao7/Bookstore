package com.solvd.bookstore.linkedList;

import com.solvd.bookstore.entities.User;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class UsersLinkedList<T extends User> implements Iterable<T> {

    private Node<T> head;

    public UsersLinkedList() {}

    public void addLast(T data) {
        Node<T> newNode = new Node<>(data);
        if (this.head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.getNextNode() != null) {
                current = current.getNextNode();
            }
            current.setNextNode(newNode);
        }
    }

    public void addFirst(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.setNextNode(head);
        head = newNode;
    }

    public void insertAt(int index, T data) {
        Node<T> insertNode = new Node<>(data);
        Node<T> node = head;
        for (int i = 0; i < index - 1; i++) {
            node = node.getNextNode();
        }
        insertNode.setNextNode(node.getNextNode());
        node.setNextNode(insertNode);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private Node<T> current = head;
            private Node<T> previous = null;
            private boolean canRemove = false;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T data = current.getData();
                current = current.getNextNode();
                return data;
            }

            @Override
            public void remove() {
                if (!canRemove) {
                    throw new IllegalStateException("next() method has not been called, or remove() has already been called after the last call to next().");
                }
                if (previous == null) {
                    head = current.getNextNode();
                } else {
                    previous.setNextNode(current.getNextNode());
                }
                current = previous;
                canRemove = false;
            }

        };
    }
}
