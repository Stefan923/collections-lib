package me.stefan923.collections.tree;

import me.stefan923.collections.Collection;

import java.util.Arrays;
import java.util.Optional;
import java.util.Stack;
import java.util.function.Predicate;

public class BinarySearchTree<E extends Comparable<E>> implements Tree<E> {

    private Node<E> root;
    private int size;

    public BinarySearchTree() {
        root = null;
        size = 0;
    }

    public BinarySearchTree(E[] elements) {
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
        for (Object element : collection.toArray()) {
            if (!add((E) element)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean remove(E element) {
        Node<E> currentNode = root;
        Node<E> parentNode = null;
        while (currentNode != null && currentNode.key != element) {
            parentNode = currentNode;
            if (currentNode.key != null && currentNode.key.compareTo(element) > 0) {
                currentNode = currentNode.left;
            } else {
                currentNode = currentNode.right;
            }
        }

        if (currentNode != null) {
            if (currentNode.left == null) {
                if (parentNode == null) {
                    root = currentNode.right;
                } else {
                    if (parentNode.left == currentNode) {
                        parentNode.left = currentNode.right;
                    } else {
                        parentNode.right = currentNode.right;
                    }
                }
            } else if (currentNode.right == null) {
                if (parentNode == null) {
                    root = currentNode.left;
                } else {
                    if (parentNode.left == currentNode) {
                        parentNode.left = currentNode.left;
                    } else {
                        parentNode.right = currentNode.left;
                    }
                }
            } else {
                Node<E> leftmostParentNode = currentNode;
                Node<E> leftmostNode = currentNode.right;
                while (leftmostNode.left != null) {
                    leftmostParentNode = leftmostNode;
                    leftmostNode = leftmostNode.left;
                }
                currentNode.key = leftmostNode.key;
                if (leftmostParentNode == currentNode) {
                    leftmostParentNode.right = null;
                } else {
                    leftmostParentNode.left = null;
                }
            }
            --size;
            return true;
        }

        return false;
    }

    @Override
    public void clear() {
        Stack<Node<E>> nodes = new Stack<>();
        Node<E> currentNode = root;
        while (currentNode != null || !nodes.isEmpty()) {
            while (currentNode != null) {
                nodes.push(currentNode);
                currentNode = currentNode.left;
            }
            currentNode = nodes.pop();
            Node<E> rightChild = currentNode.right;
            currentNode.left = null;
            currentNode.right = null;
            currentNode = rightChild;
        }
        root = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Optional<E> findFirst(Predicate<E> predicate) {
        Stack<Node<E>> nodes = new Stack<>();
        Node<E> currentNode = root;
        while (currentNode != null || !nodes.isEmpty()) {
            while (currentNode != null) {
                nodes.push(currentNode);
                currentNode = currentNode.left;
            }
            currentNode = nodes.pop();
            if (predicate.test(currentNode.key)) {
                return Optional.of(currentNode.key);
            }
            currentNode = currentNode.right;
        }
        return Optional.empty();
    }

    @Override
    public Optional<E> findLast(Predicate<E> predicate) {
        Node<E> lastFoundNode = null;

        Stack<Node<E>> nodes = new Stack<>();
        Node<E> currentNode = root;
        while (currentNode != null || !nodes.isEmpty()) {
            while (currentNode != null) {
                nodes.push(currentNode);
                currentNode = currentNode.left;
            }
            currentNode = nodes.pop();
            if (predicate.test(currentNode.key)) {
                lastFoundNode = currentNode;
            }
            currentNode = currentNode.right;
        }
        return lastFoundNode != null ? Optional.of(lastFoundNode.key) : Optional.empty();
    }

    @Override
    public Object[] findAll(Predicate<E> predicate) {
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
            if (predicate.test(currentNode.key)) {
                elements[index++] = currentNode.key;
            }
            currentNode = currentNode.right;
        }

        return Arrays.copyOf(elements, index);
    }

    @Override
    public boolean has(E element) {
        Node<E> currentNode = root;
        while (currentNode != null) {
            if (currentNode.key == element) {
                return true;
            }
            if (currentNode.key != null && currentNode.key.compareTo(element) > 0) {
                currentNode = currentNode.left;
            } else {
                currentNode = currentNode.right;
            }
        }
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
        private T key;

        private Node(T key) {
            this.key = key;
            this.left = null;
            this.right = null;
        }

    }

}
