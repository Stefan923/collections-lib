package me.stefan923.collections.map;

import me.stefan923.collections.Collection;

public interface Map<K, V> {

    boolean put(K key, V value);
    boolean remove(K key);
    boolean has(K key);
    boolean get(K key);

}
