package me.stefan923.collections.list;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayListTest {

    @Test
    public void testAdd() {
        Integer[] expected = new Integer[]{ 10, 20, 40, 30 };
        ArrayList<Integer> list = new ArrayList<>(new Integer[]{ 10, 20, 40 });

        list.add(30);
        Integer[] actual = (Integer[]) list.toArray();

        assertArrayEquals(expected, actual);
    }

    @Test
    public void testRemove() {
        Integer[] expected = new Integer[]{ 10, 40 };
        ArrayList<Integer> list = new ArrayList<>(new Integer[]{ 10, 20, 40 });

        list.remove(20);
        Integer[] actual = (Integer[]) list.toArray();

        assertArrayEquals(expected, actual);
    }

    @Test
    public void testFindFirst() {
        int expected = 60;
        ArrayList<Integer> integers = new ArrayList<>(new Integer[]{ 10, 30, 60, 40, 70, 100 });

        int actual = integers.findFirst(e -> e > 50).orElse(0);

        assertEquals(expected, actual);
    }

    @Test
    public void testFindLast() {
        int expected = 100;
        ArrayList<Integer> integers = new ArrayList<>(new Integer[]{ 10, 30, 60, 40, 70, 100 });

        int actual = integers.findLast(e -> e > 50).orElse(0);

        assertEquals(expected, actual);
    }

    @Test
    public void testFindAll() {
        Integer[] expected = new Integer[]{ 60, 70, 100 };
        ArrayList<Integer> integers = new ArrayList<>(new Integer[]{ 10, 30, 60, 40, 70, 100 });

        Integer[] actual = Arrays.stream(integers.findAll(e -> e > 50)).toArray(Integer[]::new);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void testHas_whenTrue() {
        ArrayList<Integer> integers = new ArrayList<>(new Integer[]{ 10, 30, 60, 40, 70, 100 });

        boolean actual = integers.has(60);

        assertTrue(actual);
    }

    @Test
    public void testHas_whenFalse() {
        ArrayList<Integer> integers = new ArrayList<>(new Integer[]{ 10, 30, 60, 40, 70, 100 });

        boolean actual = integers.has(50);

        assertFalse(actual);
    }

    @Test
    public void testToArray() {
        Integer[] expected = new Integer[]{ 10, 20, 30 };
        Integer[] actual = (Integer[]) new ArrayList<>(new Integer[]{ 10, 20, 30 }).toArray();

        assertArrayEquals(expected, actual);
    }

    @Test
    public void testSort() {
        Integer[] expected = new Integer[]{ 10, 30, 40, 60, 70, 100 };
        ArrayList<Integer> integers = new ArrayList<>(new Integer[]{ 10, 30, 60, 40, 70, 100 });

        integers.sort(Integer::compareTo);
        Integer[] actual = Arrays.stream(integers.toArray()).toArray(Integer[]::new);

        assertArrayEquals(expected, actual);
    }

}
