package me.stefan923.collections.list;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class LinkedListTest {

    @Test
    public void testAdd() {
        Integer[] expected = new Integer[]{ 10, 20, 40, 30 };
        List<Integer> list = new LinkedList<>(new Integer[]{ 10, 20, 40 });

        list.add(30);
        Integer[] actual = Arrays.stream(list.toArray()).toArray(Integer[]::new);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void testRemove() {
        Integer[] expected = new Integer[]{ 10, 40 };
        List<Integer> list = new LinkedList<>(new Integer[]{ 10, 20, 40 });

        list.remove(20);
        Integer[] actual = Arrays.stream(list.toArray()).toArray(Integer[]::new);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void testSize() {
        int expected = 6;
        List<Integer> list = new LinkedList<>(new Integer[]{ 10, 20, 70, 40, 30, 15 });

        int actual = list.size();

        assertEquals(expected, actual);
    }

    @Test
    public void testFindFirst() {
        int expected = 60;
        List<Integer> integers = new LinkedList<>(new Integer[]{ 10, 30, 60, 40, 70, 100 });

        int actual = integers.findFirst(e -> e > 50).orElse(0);

        assertEquals(expected, actual);
    }

    @Test
    public void testFindLast() {
        int expected = 100;
        List<Integer> integers = new LinkedList<>(new Integer[]{ 10, 30, 60, 40, 70, 100 });

        int actual = integers.findLast(e -> e > 50).orElse(0);

        assertEquals(expected, actual);
    }

    @Test
    public void testFindAll() {
        Integer[] expected = new Integer[]{ 60, 70, 100 };
        List<Integer> integers = new LinkedList<>(new Integer[]{ 10, 30, 60, 40, 70, 100 });

        Integer[] actual = Arrays.stream(integers.findAll(e -> e > 50)).toArray(Integer[]::new);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void testHas_whenTrue() {
        List<Integer> integers = new LinkedList<>(new Integer[]{ 10, 30, 60, 40, 70, 100 });

        boolean actual = integers.has(60);

        assertTrue(actual);
    }

    @Test
    public void testHas_whenFalse() {
        List<Integer> integers = new LinkedList<>(new Integer[]{ 10, 30, 60, 40, 70, 100 });

        boolean actual = integers.has(50);

        assertFalse(actual);
    }

    @Test
    public void testToArray() {
        Integer[] expected = new Integer[]{ 10, 20, 30 };
        Integer[] actual = Arrays.stream(new LinkedList<>(new Integer[]{ 10, 20, 30 }).toArray()).toArray(Integer[]::new);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void testSort_whenListHasMoreThanOneElement() {
        Integer[] expected = new Integer[]{ 10, 30, 40, 60, 70, 100 };
        List<Integer> integers = new LinkedList<>(new Integer[]{ 30, 10, 60, 40, 70, 100 });

        integers.sort(Integer::compareTo);
        Integer[] actual = Arrays.stream(integers.toArray()).toArray(Integer[]::new);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void testSort_whenListHasOneElement() {
        Integer[] expected = new Integer[]{ 10 };
        List<Integer> integers = new LinkedList<>(new Integer[]{ 10 });

        integers.sort(Integer::compareTo);
        Integer[] actual = Arrays.stream(integers.toArray()).toArray(Integer[]::new);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void testSort_whenListIsEmpty() {
        Integer[] expected = new Integer[]{};
        List<Integer> integers = new LinkedList<>(new Integer[]{});

        integers.sort(Integer::compareTo);
        Integer[] actual = Arrays.stream(integers.toArray()).toArray(Integer[]::new);

        assertArrayEquals(expected, actual);
    }

}
