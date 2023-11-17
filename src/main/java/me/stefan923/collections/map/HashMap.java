package me.stefan923.collections.map;

import java.util.Optional;

public class HashMap<K, V> implements Map<K, V> {

    private static final int MAX_ARRAY_CAPACITY = Integer.MAX_VALUE - 8;

    private static final int DEFAULT_SIZE = 100;

    private final Node[] elements;

    private int size;

    public HashMap(int size) {
        this.size = size;
        this.elements = new Node[size];
    }

    public HashMap() {
        this(DEFAULT_SIZE);
    }

    @Override
    public boolean put(K key, V value) {
        int hashCode = (key.hashCode() & Integer.MAX_VALUE) % size;
        for (int i = 0; i < size; ++i) {
            int index = (hashCode + i) % size;
            if (elements[index] == null) {
                elements[index] = new Node<>(hashCode, key, value);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean remove(K key) {
        int hashCode = (key.hashCode() & Integer.MAX_VALUE) % size;
        for (int i = 0; i < size; ++i) {
            int index = (hashCode + i) % size;
            if (elements[index] != null && elements[index].getHashCode() == hashCode) {
                elements[index] = null;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean has(K key) {
        int hashCode = (key.hashCode() & Integer.MAX_VALUE) % size;
        for (int i = 0; i < size; ++i) {
            int index = (hashCode + i) % size;
            if (elements[index] != null && elements[index].getHashCode() == hashCode) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Optional<V> get(K key) {
        int hashCode = (key.hashCode() & Integer.MAX_VALUE) % size;
        for (int i = 0; i < size; ++i) {
            int index = (hashCode + i) % size;
            if (elements[index] != null && elements[index].getHashCode() == hashCode) {
                return Optional.of((V) elements[index].getValue());
            }
        }
        return Optional.empty();
    }
}
