package me.stefan923.collections.map;

public class Node<K, V> {
    private final int hashCode;
    private final K key;
    private V value;

    public Node(int hashCode, K key, V value) {
        this.hashCode = hashCode;
        this.key = key;
        this.value = value;
    }

    public int getHashCode() {
        return hashCode;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}
