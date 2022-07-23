package me.stefan923.collections.list;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.function.Predicate;

public class ArrayList<E> implements List<E> {

    private static final int MAX_ARRAY_CAPACITY = Integer.MAX_VALUE - 8;

    private static final int DEFAULT_SIZE = 10;

    private Object[] elements;

    private int size;

    public ArrayList() {
        elements = new Object[DEFAULT_SIZE];
        size = 0;
    }

    public ArrayList(E[] elements) {
        this.elements = Arrays.copyOf(elements, elements.length);
        size = elements.length;
    }

    @Override
    public E add(E element) {
        ensureCapacity(size + 1);
        elements[size++] = element;

        return element;
    }

    @Override
    public boolean remove(E element) {
        for (int i = 0; i < size; ++i) {
            if (elements[i].equals(element)) {
                if (size - 1 - i >= 0) {
                    System.arraycopy(elements, i + 1, elements, i, size - 1 - i);
                    --size;

                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public Optional<E> findFirst(Predicate<E> predicate) {
        for (Object element : elements) {
            E elementToTest = (E) element;
            if (predicate.test(elementToTest)) {
                return Optional.of(elementToTest);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<E> findLast(Predicate<E> predicate) {
        for (int i = size - 1; i >= 0; --i) {
            E element = (E) elements[i];
            if (predicate.test(element)) {
                return Optional.of(element);
            }
        }
        return Optional.empty();
    }

    @Override
    public Object[] findAll(Predicate<E> predicate) {
        return Arrays.stream(elements).map(e -> (E) e).filter(predicate::test).toArray();
    }

    @Override
    public boolean has(E element) {
        for (int i = 0; i < size; ++i) {
            if (elements[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elements, size);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void sort(Comparator<? super E> comparator) {
        Arrays.sort((E[]) elements, 0, size, comparator);
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > elements.length) {
            growCapacity(minCapacity + (minCapacity >> 1));
        }
    }

    private void growCapacity(int newCapacity) {
        if (newCapacity < 0) {
            if (elements.length == MAX_ARRAY_CAPACITY) {
                throw new OutOfMemoryError();
            }
            newCapacity = MAX_ARRAY_CAPACITY;
        }
        elements = Arrays.copyOf(elements, Integer.min(newCapacity, MAX_ARRAY_CAPACITY));
    }
}
