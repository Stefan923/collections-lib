package me.stefan923.collections.map;

import javafx.util.Pair;

import java.util.List;
import java.util.Optional;

public interface Map<K, V> {

    boolean put(K key, V value);
    boolean remove(K key);
    boolean has(K key);
    Optional<V> get(K key);
    List<K> getKeys();
    List<Pair<K, V>> getEntries();

}
