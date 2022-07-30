package me.stefan923.collections.tree;

import me.stefan923.collections.Collection;

import java.util.Optional;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;
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
            if (currentNode.key.compareTo(element) > 0) {
                if (currentNode.left == null) {
                    currentNode.left = newNode;
                    break;
                }
                currentNode = currentNode.left;
            } else {
                if (currentNode.right == null) {
                    currentNode.right = newNode;
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
        return computeTraversal(TraversalType.INORDER);
    }

    @Override
    public Object[] computeTraversal(TraversalType traversalType) {
        if (size == 0) {
            return new Object[0];
        }

        switch (traversalType) {
            case PREORDER:
                return computePreorderTraversal();
            case POSTORDER:
                return computePostorderTraversal();
            default:
                return computeInorderTraversal();
        }
    }

    private Object[] computeInorderTraversal() {
        Object[] elements = new Object[size];
        int index = 0;

        Stack<Node<E>> nodes = new Stack<>();
        Node<E> currentNode = root;
        while (currentNode != null || !nodes.isEmpty()) {
            while (currentNode != null) {
                nodes.push(currentNode);
                currentNode = currentNode.left;
            }
            currentNode = nodes.pop();
            elements[index++] = currentNode.key;
            currentNode = currentNode.right;
        }

        return elements;
    }

    private Object[] computePreorderTraversal() {
        Object[] elements = new Object[size];
        int index = 0;

        Stack<Node<E>> nodes = new Stack<>();
        nodes.push(root);
        while (!nodes.isEmpty()) {
            Node<E> currentNode = nodes.pop();
            elements[index++] = currentNode.key;
            if (currentNode.right != null) {
                nodes.push(currentNode.right);
            }
            if (currentNode.left != null) {
                nodes.push(currentNode.left);
            }
        }

        return elements;
    }

    private Object[] computePostorderTraversal() {
        Object[] elements = new Object[size];
        int index = 0;

        Stack<Node<E>> nodes = new Stack<>();
        Node<E> currentNode = root;
        while (currentNode != null || !nodes.isEmpty()) {
            while (currentNode != null) {
                if (currentNode.right != null) {
                    nodes.push(currentNode.right);
                }
                nodes.push(currentNode);
                currentNode = currentNode.left;
            }
            currentNode = nodes.pop();
            if (!nodes.empty() && currentNode.right == nodes.peek()) {
                nodes.pop();
                nodes.push(currentNode);
                currentNode = currentNode.right;
            } else {
                elements[index++] = currentNode.key;
                currentNode = null;
            }
        }

        return elements;
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
