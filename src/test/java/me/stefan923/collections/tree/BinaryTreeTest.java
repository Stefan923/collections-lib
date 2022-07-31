package me.stefan923.collections.tree;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class BinaryTreeTest {

    @Test
    public void testAdd() {
        Integer[] expected = new Integer[]{ 10, 20, 30, 40 };
        Tree<Integer> tree = new BinaryTree<>(new Integer[]{ 10, 20, 40 });

        tree.add(30);
        Integer[] actual = Arrays.stream(tree.toArray()).toArray(Integer[]::new);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void testAddAll() {
        Integer[] expected = new Integer[]{ 10, 20, 30, 40, 50, 60, 70, 80};
        Tree<Integer> tree1 = new BinaryTree<>(new Integer[]{ 10, 30, 70, 20 });
        Tree<Integer> tree2 = new BinaryTree<>(new Integer[]{ 60, 40, 50, 80 });

        tree1.addAll(tree2);
        Integer[] actual = Arrays.stream(tree1.toArray()).toArray(Integer[]::new);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void testComputeTraversal_whenTraversalTypeIsInorder() {
        Integer[] expected = new Integer[]{ 5, 10, 20, 30, 40, 50 };
        Tree<Integer> tree = new BinaryTree<>(new Integer[]{ 20, 40, 10, 5, 30, 50 });

        Integer[] actual = Arrays.stream(tree.computeTraversal(TraversalType.INORDER)).toArray(Integer[]::new);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void testComputeTraversal_whenTraversalTypeIsPreorder() {
        Integer[] expected = new Integer[]{ 20, 10, 5, 40, 30, 50 };
        Tree<Integer> tree = new BinaryTree<>(new Integer[]{ 20, 40, 10, 5, 30, 50 });

        Integer[] actual = Arrays.stream(tree.computeTraversal(TraversalType.PREORDER)).toArray(Integer[]::new);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void testComputeTraversal_whenTraversalTypeIsPostorder() {
        Integer[] expected = new Integer[]{ 5, 10, 30, 50, 40, 20 };
        Tree<Integer> tree = new BinaryTree<>(new Integer[]{ 20, 40, 10, 5, 30, 50 });

        Integer[] actual = Arrays.stream(tree.computeTraversal(TraversalType.POSTORDER)).toArray(Integer[]::new);

        assertArrayEquals(expected, actual);
    }

}
