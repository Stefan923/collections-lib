package me.stefan923.collections.map;

import java.util.Optional;

public class HashMap<K, V> implements Map<K, V> {

    private static final int MAX_ARRAY_CAPACITY = Integer.MAX_VALUE - 8;

    private static final int DEFAULT_SIZE = 100;

    private Object[] elements;

    private int size;

    @Override
    public boolean put(K key, V value) {
        for (int i = 0; i < elements.length; ++i) {
            int index = (key.hashCode() + i) % elements.length;
            if (elements[index] == null) {
                elements[index] = value;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean remove(K key) {
        for (int i = 0; i < elements.length; ++i) {
            int index = (key.hashCode() + i) % elements.length;
            if (elements[index] != null) {
                elements[index] = null;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean has(K key) {
        for (int i = 0; i < elements.length; ++i) {
            int index = (key.hashCode() + i) % elements.length;
            if (elements[index] != null) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Optional<V> get(K key) {
        for (int i = 0; i < elements.length; ++i) {
            int index = (key.hashCode() + i) % elements.length;
            if (elements[index] != null) {
                return Optional.of((V) elements[index]);
            }
        }
        return Optional.empty();
    }
}
