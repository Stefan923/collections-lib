package me.stefan923.collections.map;

import java.util.Optional;

public interface Map<K, V> {

    boolean put(K key, V value);
    boolean remove(K key);
    boolean has(K key);
    Optional<V> get(K key);

}
