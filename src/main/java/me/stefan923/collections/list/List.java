package me.stefan923.collections.list;

import me.stefan923.collections.Collection;

import java.util.function.Function;

public interface List<E extends Comparable<E>> extends Collection<E> {

    List<E> sort(Function<E, Integer> function);

}
