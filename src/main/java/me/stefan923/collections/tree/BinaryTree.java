package me.stefan923.collections.tree;

import me.stefan923.collections.Collection;

import java.util.Optional;
import java.util.function.Predicate;

public class BinaryTree<E extends Comparable<E>> implements Tree<E> {

    private Node<E> root;
    private int size;

    public BinaryTree() {
        root = null;
        size = 0;
    }

    public BinaryTree(E[] elements) {
        for (E element : elements) {
            add(element);
        }
    }

    @Override
    public boolean add(E element) {
        Node<E> newNode = new Node<>(element);

        if (root == null) {
            root = newNode;
            size = 1;

            return true;
        }

        Node<E> currentNode = root;
        while (true) {
            if (currentNode.key.compareTo(element) <= 0) {
                if (currentNode.left == null) {
                    currentNode.left = root;
                    break;
                }
                currentNode = currentNode.left;
            } else {
                if (currentNode.right == null) {
                    currentNode.right = root;
                    break;
                }
                currentNode = currentNode.right;
            }
        }
        ++size;

        return true;
    }

    @Override
    public boolean addAll(Collection<E> collection) {
        return false;
    }

    @Override
    public boolean remove(E element) {
        return false;
    }

    @Override
    public void clear() {
        
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Optional<E> findFirst(Predicate<E> predicate) {
        return Optional.empty();
    }

    @Override
    public Optional<E> findLast(Predicate<E> predicate) {
        return Optional.empty();
    }

    @Override
    public Object[] findAll(Predicate<E> predicate) {
        return new Object[0];
    }

    @Override
    public boolean has(E element) {
        return false;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public Object[] computeTraversal(TraversalType traversalType) {
        return new Object[0];
    }

    private static class Node<T> {

        private Node<T> left;
        private Node<T> right;
        private final T key;

        private Node(T key) {
            this.key = key;
            this.left = null;
            this.right = null;
        }

    }

}
