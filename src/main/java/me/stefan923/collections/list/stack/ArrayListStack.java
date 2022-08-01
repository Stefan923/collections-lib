package me.stefan923.collections.list.stack;

import me.stefan923.collections.list.ArrayList;

public class ArrayListStack<E> extends ArrayList<E> implements Stack<E> {

    @Override
    public boolean push(E element) {
        return add(element);
    }

    @Override
    public E pop() {
        return null;
    }

    @Override
    public E peek() {
        return null;
    }
}
