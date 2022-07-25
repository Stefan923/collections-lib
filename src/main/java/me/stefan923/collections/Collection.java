package me.stefan923.collections;

import java.util.Optional;
import java.util.function.Predicate;

public interface Collection<E> {

    boolean add(E element);
    boolean addAll(Collection<E> collection);
    boolean remove(E element);
    int size();
    Optional<E> findFirst(Predicate<E> predicate);
    Optional<E> findLast(Predicate<E> predicate);
    Object[] findAll(Predicate<E> predicate);
    boolean has(E element);
    Object[] toArray();

}
