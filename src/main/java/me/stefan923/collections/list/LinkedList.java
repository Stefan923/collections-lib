package me.stefan923.collections.list;

import me.stefan923.collections.Collection;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.function.Predicate;

public class LinkedList<E> implements List<E> {

    private Node<E> head;
    private Node<E> tail;

    private int size;

    public LinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public LinkedList(E[] elements) {
        for (E element : elements) {
            add(element);
        }
        this.size = elements.length;
    }

    @Override
    public boolean add(E element) {
        if (head == null) {
            head = tail = new Node<>(element);
            ++size;
            return true;
        }

        Node<E> newElement = new Node<>(element);
        tail.next = newElement;
        tail = newElement;
        ++size;

        return true;
    }

    @Override
    public void clear() {
        Node<E> currentNode = head;
        Node<E> nextNode;
        while (currentNode != null) {
            nextNode = currentNode.next;
            currentNode.next = null;
            currentNode = nextNode;
        }
        head = tail = null;
        size = 0;
    }

    @Override
    public boolean addAll(Collection<E> collection) {
        for (Object element : collection.toArray()) {
            if (!add((E) element)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean remove(E element) {
        Node<E> previousElement = null;
        Node<E> currentElement = head;
        while (currentElement != null) {
            if (currentElement.key.equals(element)) {
                if (previousElement == null) {
                    head = tail = null;
                } else {
                    previousElement.next = currentElement.next;
                    if (tail == currentElement) {
                        tail = previousElement;
                    }
                }
                --size;
                return true;
            }
            previousElement = currentElement;
            currentElement = currentElement.next;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Optional<E> findFirst(Predicate<E> predicate) {
        Node<E> currentElement = head;
        while (currentElement != null) {
            if (predicate.test(currentElement.key)) {
                return Optional.of(currentElement.key);
            }
            currentElement = currentElement.next;
        }
        return Optional.empty();
    }

    @Override
    public Optional<E> findLast(Predicate<E> predicate) {
        Node<E> currentElement = head;
        E latestElementFound = null;
        while (currentElement != null) {
            if (predicate.test(currentElement.key)) {
                latestElementFound = currentElement.key;
            }
            currentElement = currentElement.next;
        }
        return Optional.ofNullable(latestElementFound);
    }

    @Override
    public Object[] findAll(Predicate<E> predicate) {
        Object[] foundElements = new Object[size];
        int index = 0;

        Node<E> currentElement = head;
        while (currentElement != null) {
            if (predicate.test(currentElement.key)) {
                foundElements[index++] = currentElement.key;
            }
            currentElement = currentElement.next;
        }
        return Arrays.copyOf(foundElements, index);
    }

    @Override
    public boolean has(E element) {
        Node<E> currentElement = head;
        while (currentElement != null) {
            if (currentElement.key.equals(element)) {
                return true;
            }
            currentElement = currentElement.next;
        }
        return false;
    }

    @Override
    public Object[] toArray() {
        Object[] elements = new Object[size];
        int index = 0;

        Node<E> currentElement = head;
        while (currentElement != null) {
            elements[index++] = currentElement.key;
            currentElement = currentElement.next;
        }

        return elements;
    }

    @Override
    public void sort(Comparator<? super E> comparator) {
        if (head == null) {
            return;
        }

        Node<E> sortedListHead = head;
        Node<E> currentElement = head.next;
        sortedListHead.next = null;
        while (currentElement != null) {
            Node<E> nextElement = currentElement.next;
            sortedListHead = sortedInsert(sortedListHead, currentElement, comparator);
            currentElement = nextElement;
        }

        head = sortedListHead;
        currentElement = sortedListHead;
        while (currentElement.next != null) {
            currentElement = currentElement.next;
        }
        tail = currentElement;
    }

    private Node<E> sortedInsert(Node<E> head, Node<E> newElement,
                                 Comparator<? super E> comparator) {
        Node<E> currentNode = head;
        Node<E> previousNode = null;

        if (currentNode == null) {
            newElement.next = null;
            return newElement;
        }

        while (currentNode != null) {
            if (comparator.compare(newElement.key, currentNode.key) < 0) {
                if (previousNode != null) {
                    previousNode.next = newElement;
                }
                newElement.next = currentNode;
                return (head == currentNode) ? newElement : head;
            }
            previousNode = currentNode;
            currentNode = currentNode.next;
        }

        previousNode.next = newElement;
        newElement.next = null;
        return head;
    }

    private static class Node<T> {

        private final T key;
        private Node<T> next;

        public Node(T key) {
            this.key = key;
            this.next = null;
        }

    }

}
