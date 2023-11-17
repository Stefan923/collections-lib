package me.stefan923.collections.map;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HashMap<K, V> implements Map<K, V> {

    private static final int MAX_ARRAY_CAPACITY = 1 << 30;
    private static final int DEFAULT_CAPACITY = 1 << 4;
    private static final float LOAD_FACTOR = 0.75f;

    private Node[] elements;

    private int capacity;
    private int size;

    public HashMap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.elements = new Node[capacity];
    }

    public HashMap() {
        this(DEFAULT_CAPACITY);
    }

    @Override
    public boolean put(K key, V value) {
        if (!checkLoadFactorOrExpand()) {
            return false;
        }

        int keyHashCode = key.hashCode();
        int hashCode = (keyHashCode & Integer.MAX_VALUE) % capacity;
        for (int i = 0; i < capacity; ++i) {
            int index = (hashCode + i) % capacity;
            if (elements[index] == null) {
                elements[index] = new Node<>(keyHashCode, key, value);
                ++size;

                return true;
            }
        }
        return false;
    }

    @Override
    public boolean remove(K key) {
        int keyHashCode = key.hashCode();
        int hashCode = (keyHashCode & Integer.MAX_VALUE) % capacity;
        for (int i = 0; i < capacity; ++i) {
            int index = (hashCode + i) % capacity;
            if (elements[index] != null && elements[index].getHashCode() == keyHashCode) {
                elements[index] = null;
                --size;

                return true;
            }
        }
        return false;
    }

    @Override
    public boolean has(K key) {
        int keyHashCode = key.hashCode();
        int hashCode = (keyHashCode & Integer.MAX_VALUE) % capacity;
        for (int i = 0; i < capacity; ++i) {
            int index = (hashCode + i) % capacity;
            if (elements[index] != null && elements[index].getHashCode() == keyHashCode) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Optional<V> get(K key) {
        int keyHashCode = key.hashCode();
        int hashCode = (keyHashCode & Integer.MAX_VALUE) % capacity;
        for (int i = 0; i < capacity; ++i) {
            int index = (hashCode + i) % capacity;
            if (elements[index] != null && elements[index].getHashCode() == keyHashCode) {
                return Optional.of((V) elements[index].getValue());
            }
        }
        return Optional.empty();
    }

    @Override
    public List<K> getKeys() {
        List<K> keys = new ArrayList<>();

        for (Node node : elements) {
            if (node != null) {
                keys.add((K) node.getKey());
            }
        }

        return keys;
    }

    @Override
    public List<Pair<K, V>> getEntries() {
        List<Pair<K, V>> entries = new ArrayList<>();

        for (Node node : elements) {
            if (node != null) {
                entries.add(new Pair<>((K) node.getKey(), (V) node.getValue()));
            }
        }

        return entries;
    }

    private boolean checkLoadFactorOrExpand() {
        if ((float) size / capacity >= LOAD_FACTOR) {
            int newCapacity = capacity << 1;
            if (newCapacity > MAX_ARRAY_CAPACITY) {
                return false;
            }

            expandArray(newCapacity);
        }

        return true;
    }

    private void expandArray(int newCapacity) {
        Node[] newElements = new Node[newCapacity];

        for (Node node : elements) {
            if (node == null) {
                continue;
            }

            for (int i = 0; i < newCapacity; ++i) {
                int index = (node.getHashCode() & Integer.MAX_VALUE) % newCapacity;
                if (newElements[index] == null) {
                    newElements[index] = node;
                }
            }
        }

        this.capacity = newCapacity;
        this.elements = newElements;
    }
}
