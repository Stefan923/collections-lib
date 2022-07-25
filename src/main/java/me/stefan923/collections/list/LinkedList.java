package me.stefan923.collections.list;

import me.stefan923.collections.Collection;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.function.Predicate;

public class LinkedList<E> implements List<E> {

    private LinkedListNode<E> head;
    private LinkedListNode<E> tail;

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
            head = tail = new LinkedListNode<>(element);
            ++size;
            return true;
        }

        LinkedListNode<E> newElement = new LinkedListNode<>(element);
        tail.next = newElement;
        tail = newElement;
        ++size;

        return true;
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
        LinkedListNode<E> previousElement = null;
        LinkedListNode<E> currentElement = head;
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
        LinkedListNode<E> currentElement = head;
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
        LinkedListNode<E> currentElement = head;
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

        LinkedListNode<E> currentElement = head;
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
        LinkedListNode<E> currentElement = head;
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

        LinkedListNode<E> currentElement = head;
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

        LinkedListNode<E> sortedListHead = head;
        LinkedListNode<E> currentElement = head.next;
        sortedListHead.next = null;
        while (currentElement != null) {
            LinkedListNode<E> nextElement = currentElement.next;
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

    private LinkedListNode<E> sortedInsert(LinkedListNode<E> head, LinkedListNode<E> newElement,
                                           Comparator<? super E> comparator) {
        LinkedListNode<E> currentNode = head;
        LinkedListNode<E> previousNode = null;

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

    private static class LinkedListNode<T> {

        private final T key;
        private LinkedListNode<T> next;

        public LinkedListNode(T key) {
            this.key = key;
            this.next = null;
        }

    }

}
