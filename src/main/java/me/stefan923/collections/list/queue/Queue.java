package me.stefan923.collections.list.queue;

import me.stefan923.collections.list.List;

public interface Queue<E> extends List<E> {

    boolean push(E element);
    E pop();
    E peek();

}
