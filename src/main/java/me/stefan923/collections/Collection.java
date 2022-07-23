package me.stefan923.collections;

import java.util.Optional;
import java.util.function.Predicate;

public interface Collection<E> {

    E add(E element);
    boolean remove(E element);
    Optional<E> findFirst(Predicate<E> predicate);
    Optional<E> findLast(Predicate<E> predicate);
    Object[] findAll(Predicate<E> predicate);
    boolean has(E element);
    Object[] toArray();

}
