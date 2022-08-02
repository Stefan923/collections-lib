package me.stefan923.collections.list.queue;

import me.stefan923.collections.list.ArrayList;

public class ArrayListQueue<E> extends ArrayList<E> implements Queue<E> {

    public ArrayListQueue() {
        super();
    }

    public ArrayListQueue(E[] elements) {
        super(elements);
    }

    @Override
    public boolean push(E element) {
        return add(element);
    }

    @Override
    public E pop() {
        return removeFirst();
    }

    @Override
    public E peek() {
        return null;
    }

}
