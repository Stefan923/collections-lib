package me.stefan923.collections.map;

public interface Map<K, V> {

    boolean put(K key, V value);
    boolean remove(K key);
    boolean has(K key);
    V get(K key);

}
