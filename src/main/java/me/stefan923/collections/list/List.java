package me.stefan923.collections.list;

import me.stefan923.collections.Collection;

import java.util.Comparator;

public interface List<E> extends Collection<E> {

    void sort(Comparator<? super E> comparator);

}
