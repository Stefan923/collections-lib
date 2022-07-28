package me.stefan923.collections.list;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayListTest {

    @Test
    public void testAdd() {
        Integer[] expected = new Integer[]{ 10, 20, 40, 30 };
        List<Integer> list = new ArrayList<>(new Integer[]{ 10, 20, 40 });

        list.add(30);
        Integer[] actual = Arrays.stream(list.toArray()).toArray(Integer[]::new);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void testAddAll() {
        Integer[] expected = new Integer[]{ 10, 20, 40, 30, 70, 15, 100 };
        List<Integer> list1 = new ArrayList<>(new Integer[]{ 10, 20, 40 });
        List<Integer> list2 = new ArrayList<>(new Integer[]{ 30, 70, 15, 100 });

        list1.addAll(list2);
        Integer[] actual = Arrays.stream(list1.toArray()).toArray(Integer[]::new);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void testRemove() {
        Integer[] expected = new Integer[]{ 10, 40 };
        List<Integer> list = new ArrayList<>(new Integer[]{ 10, 20, 40 });

        list.remove(20);
        Integer[] actual = Arrays.stream(list.toArray()).toArray(Integer[]::new);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void testClear() {
        Integer[] expected = new Integer[]{};
        List<Integer> list = new ArrayList<>(new Integer[]{ 10, 20, 40 });

        list.clear();
        Integer[] actual = Arrays.stream(list.toArray()).toArray(Integer[]::new);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void testSize() {
        int expected = 6;
        List<Integer> list = new ArrayList<>(new Integer[]{ 10, 20, 70, 40, 30, 15 });

        int actual = list.size();

        assertEquals(expected, actual);
    }

    @Test
    public void testFindFirst() {
        int expected = 60;
        List<Integer> integers = new ArrayList<>(new Integer[]{ 10, 30, 60, 40, 70, 100 });

        int actual = integers.findFirst(e -> e > 50).orElse(0);

        assertEquals(expected, actual);
    }

    @Test
    public void testFindLast() {
        int expected = 100;
        List<Integer> integers = new ArrayList<>(new Integer[]{ 10, 30, 60, 40, 70, 100 });

        int actual = integers.findLast(e -> e > 50).orElse(0);

        assertEquals(expected, actual);
    }

    @Test
    public void testFindAll() {
        Integer[] expected = new Integer[]{ 60, 70, 100 };
        List<Integer> integers = new ArrayList<>(new Integer[]{ 10, 30, 60, 40, 70, 100 });

        Integer[] actual = Arrays.stream(integers.findAll(e -> e > 50)).toArray(Integer[]::new);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void testHas_whenTrue() {
        List<Integer> integers = new ArrayList<>(new Integer[]{ 10, 30, 60, 40, 70, 100 });

        boolean actual = integers.has(60);

        assertTrue(actual);
    }

    @Test
    public void testHas_whenFalse() {
        List<Integer> integers = new ArrayList<>(new Integer[]{ 10, 30, 60, 40, 70, 100 });

        boolean actual = integers.has(50);

        assertFalse(actual);
    }

    @Test
    public void testToArray() {
        Integer[] expected = new Integer[]{ 10, 20, 30 };
        Integer[] actual = Arrays.stream(new ArrayList<>(new Integer[]{ 10, 20, 30 }).toArray()).toArray(Integer[]::new);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void testSort() {
        Integer[] expected = new Integer[]{ 10, 30, 40, 60, 70, 100 };
        List<Integer> integers = new ArrayList<>(new Integer[]{ 10, 30, 60, 40, 70, 100 });

        integers.sort(Integer::compareTo);
        Integer[] actual = Arrays.stream(integers.toArray()).toArray(Integer[]::new);

        assertArrayEquals(expected, actual);
    }

}
