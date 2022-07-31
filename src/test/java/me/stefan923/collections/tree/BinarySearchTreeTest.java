package me.stefan923.collections.tree;

import me.stefan923.collections.list.LinkedList;
import me.stefan923.collections.list.List;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinarySearchTreeTest {

    @Test
    public void testAdd() {
        Integer[] expected = new Integer[]{ 10, 20, 30, 40 };
        Tree<Integer> tree = new BinarySearchTree<>(new Integer[]{ 10, 20, 40 });

        tree.add(30);
        Integer[] actual = Arrays.stream(tree.toArray()).toArray(Integer[]::new);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void testAddAll() {
        Integer[] expected = new Integer[]{ 10, 20, 30, 40, 50, 60, 70, 80};
        Tree<Integer> tree1 = new BinarySearchTree<>(new Integer[]{ 10, 30, 70, 20 });
        Tree<Integer> tree2 = new BinarySearchTree<>(new Integer[]{ 60, 40, 50, 80 });

        tree1.addAll(tree2);
        Integer[] actual = Arrays.stream(tree1.toArray()).toArray(Integer[]::new);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void testRemove() {
        Integer[] expected = new Integer[]{ 10, 30, 40, 50, 60, 70 };
        Tree<Integer> tree = new BinarySearchTree<>(new Integer[]{ 50, 20, 10, 40, 30, 60, 70 });

        tree.remove(20);
        Integer[] actual = Arrays.stream(tree.toArray()).toArray(Integer[]::new);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void testClear() {
        Integer[] expected = new Integer[]{};
        Tree<Integer> tree = new BinarySearchTree<>(new Integer[]{ 50, 20, 10, 40, 30, 60, 70 });

        tree.clear();
        Integer[] actual = Arrays.stream(tree.toArray()).toArray(Integer[]::new);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void testSize() {
        int expected = 6;
        Tree<Integer> tree = new BinarySearchTree<>(new Integer[]{ 10, 20, 70, 40, 30, 15 });

        int actual = tree.size();

        assertEquals(expected, actual);
    }

    @Test
    public void testFindFirst() {
        int expected = 60;
        Tree<Integer> integers = new BinarySearchTree<>(new Integer[]{ 10, 30, 60, 40, 70, 100 });

        int actual = integers.findFirst(e -> e > 50).orElse(0);

        assertEquals(expected, actual);
    }

    @Test
    public void testComputeTraversal_whenTraversalTypeIsInorder() {
        Integer[] expected = new Integer[]{ 5, 10, 20, 30, 40, 50 };
        Tree<Integer> tree = new BinarySearchTree<>(new Integer[]{ 20, 40, 10, 5, 30, 50 });

        Integer[] actual = Arrays.stream(tree.computeTraversal(TraversalType.INORDER)).toArray(Integer[]::new);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void testComputeTraversal_whenTraversalTypeIsPreorder() {
        Integer[] expected = new Integer[]{ 20, 10, 5, 40, 30, 50 };
        Tree<Integer> tree = new BinarySearchTree<>(new Integer[]{ 20, 40, 10, 5, 30, 50 });

        Integer[] actual = Arrays.stream(tree.computeTraversal(TraversalType.PREORDER)).toArray(Integer[]::new);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void testComputeTraversal_whenTraversalTypeIsPostorder() {
        Integer[] expected = new Integer[]{ 5, 10, 30, 50, 40, 20 };
        Tree<Integer> tree = new BinarySearchTree<>(new Integer[]{ 20, 40, 10, 5, 30, 50 });

        Integer[] actual = Arrays.stream(tree.computeTraversal(TraversalType.POSTORDER)).toArray(Integer[]::new);

        assertArrayEquals(expected, actual);
    }

}
