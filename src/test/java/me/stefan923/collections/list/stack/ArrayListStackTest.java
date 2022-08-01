package me.stefan923.collections.list.stack;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ArrayListStackTest {

    @Test
    public void testPush() {
        Integer[] expected = new Integer[]{ 10, 20, 40, 30, 50 };
        Stack<Integer> stack = new ArrayListStack<>(new Integer[]{ 10, 20, 40 });

        stack.push(30);
        stack.push(50);
        Integer[] actual = Arrays.stream(stack.toArray()).toArray(Integer[]::new);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void testPop() {
        Integer[] expectedStack = new Integer[]{ 10, 20, 40, 30 };
        int expectedPoppedElement = 50;
        Stack<Integer> stack = new ArrayListStack<>(new Integer[]{ 10, 20, 40, 30, 50 });

        int actualPoppedElement = stack.pop();
        Integer[] actualStack = Arrays.stream(stack.toArray()).toArray(Integer[]::new);

        assertArrayEquals(expectedStack, actualStack);
        assertEquals(expectedPoppedElement, actualPoppedElement);
    }

    @Test
    public void testAdd() {
        Integer[] expected = new Integer[]{ 10, 20, 40, 30 };
        Stack<Integer> stack = new ArrayListStack<>(new Integer[]{ 10, 20, 40 });

        stack.add(30);
        Integer[] actual = Arrays.stream(stack.toArray()).toArray(Integer[]::new);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void testAddAll() {
        Integer[] expected = new Integer[]{ 10, 20, 40, 30, 70, 15, 100 };
        Stack<Integer> stack1 = new ArrayListStack<>(new Integer[]{ 10, 20, 40 });
        Stack<Integer> stack2 = new ArrayListStack<>(new Integer[]{ 30, 70, 15, 100 });

        stack1.addAll(stack2);
        Integer[] actual = Arrays.stream(stack1.toArray()).toArray(Integer[]::new);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void testRemove() {
        Integer[] expected = new Integer[]{ 10, 40 };
        Stack<Integer> stack = new ArrayListStack<>(new Integer[]{ 10, 20, 40 });

        stack.remove(20);
        Integer[] actual = Arrays.stream(stack.toArray()).toArray(Integer[]::new);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void testClear() {
        Integer[] expected = new Integer[]{};
        Stack<Integer> stack = new ArrayListStack<>(new Integer[]{ 10, 20, 40 });

        stack.clear();
        Integer[] actual = Arrays.stream(stack.toArray()).toArray(Integer[]::new);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void testSize() {
        int expected = 6;
        Stack<Integer> stack = new ArrayListStack<>(new Integer[]{ 10, 20, 70, 40, 30, 15 });

        int actual = stack.size();

        assertEquals(expected, actual);
    }

    @Test
    public void testFindFirst() {
        int expected = 60;
        Stack<Integer> integers = new ArrayListStack<>(new Integer[]{ 10, 30, 60, 40, 70, 100 });

        int actual = integers.findFirst(e -> e > 50).orElse(0);

        assertEquals(expected, actual);
    }

    @Test
    public void testFindLast() {
        int expected = 100;
        Stack<Integer> integers = new ArrayListStack<>(new Integer[]{ 10, 30, 60, 40, 70, 100 });

        int actual = integers.findLast(e -> e > 50).orElse(0);

        assertEquals(expected, actual);
    }

    @Test
    public void testFindAll() {
        Integer[] expected = new Integer[]{ 60, 70, 100 };
        Stack<Integer> integers = new ArrayListStack<>(new Integer[]{ 10, 30, 60, 40, 70, 100 });

        Integer[] actual = Arrays.stream(integers.findAll(e -> e > 50)).toArray(Integer[]::new);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void testHas_whenTrue() {
        Stack<Integer> integers = new ArrayListStack<>(new Integer[]{ 10, 30, 60, 40, 70, 100 });

        boolean actual = integers.has(60);

        assertTrue(actual);
    }

    @Test
    public void testHas_whenFalse() {
        Stack<Integer> integers = new ArrayListStack<>(new Integer[]{ 10, 30, 60, 40, 70, 100 });

        boolean actual = integers.has(50);

        assertFalse(actual);
    }

    @Test
    public void testToArray() {
        Integer[] expected = new Integer[]{ 10, 20, 30 };
        Integer[] actual = Arrays.stream(new ArrayListStack<>(new Integer[]{ 10, 20, 30 }).toArray()).toArray(Integer[]::new);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void testSort() {
        Integer[] expected = new Integer[]{ 10, 30, 40, 60, 70, 100 };
        Stack<Integer> integers = new ArrayListStack<>(new Integer[]{ 10, 30, 60, 40, 70, 100 });

        integers.sort(Integer::compareTo);
        Integer[] actual = Arrays.stream(integers.toArray()).toArray(Integer[]::new);

        assertArrayEquals(expected, actual);
    }
    
}
