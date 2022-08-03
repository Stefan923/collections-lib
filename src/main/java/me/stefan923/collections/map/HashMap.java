package me.stefan923.collections.map;

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
        return false;
    }

    @Override
    public boolean has(K key) {
        return false;
    }

    @Override
    public boolean get(K key) {
        return false;
    }
}
