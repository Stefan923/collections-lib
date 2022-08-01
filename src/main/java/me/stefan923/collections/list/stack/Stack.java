package me.stefan923.collections.list.stack;

import me.stefan923.collections.list.List;

public interface Stack<E> extends List<E> {

    boolean push(E element);
    E pop();
    E peek();

}
