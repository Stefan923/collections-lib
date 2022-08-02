package me.stefan923.collections.list.queue;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ArrayListQueueTest {

    @Test
    public void testPush() {
        Integer[] expected = new Integer[]{ 10, 20, 40, 30, 50 };
        Queue<Integer> queue = new ArrayListQueue<>(new Integer[]{ 10, 20, 40 });

        queue.push(30);
        queue.push(50);
        Integer[] actual = Arrays.stream(queue.toArray()).toArray(Integer[]::new);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void testAdd() {
        Integer[] expected = new Integer[]{ 10, 20, 40, 30 };
        Queue<Integer> queue = new ArrayListQueue<>(new Integer[]{ 10, 20, 40 });

        queue.add(30);
        Integer[] actual = Arrays.stream(queue.toArray()).toArray(Integer[]::new);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void testAddAll() {
        Integer[] expected = new Integer[]{ 10, 20, 40, 30, 70, 15, 100 };
        Queue<Integer> queue1 = new ArrayListQueue<>(new Integer[]{ 10, 20, 40 });
        Queue<Integer> queue2 = new ArrayListQueue<>(new Integer[]{ 30, 70, 15, 100 });

        queue1.addAll(queue2);
        Integer[] actual = Arrays.stream(queue1.toArray()).toArray(Integer[]::new);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void testRemove() {
        Integer[] expected = new Integer[]{ 10, 40 };
        Queue<Integer> queue = new ArrayListQueue<>(new Integer[]{ 10, 20, 40 });

        queue.remove(20);
        Integer[] actual = Arrays.stream(queue.toArray()).toArray(Integer[]::new);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void testClear() {
        Integer[] expected = new Integer[]{};
        Queue<Integer> queue = new ArrayListQueue<>(new Integer[]{ 10, 20, 40 });

        queue.clear();
        Integer[] actual = Arrays.stream(queue.toArray()).toArray(Integer[]::new);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void testSize() {
        int expected = 6;
        Queue<Integer> queue = new ArrayListQueue<>(new Integer[]{ 10, 20, 70, 40, 30, 15 });

        int actual = queue.size();

        assertEquals(expected, actual);
    }

    @Test
    public void testFindFirst() {
        int expected = 60;
        Queue<Integer> integers = new ArrayListQueue<>(new Integer[]{ 10, 30, 60, 40, 70, 100 });

        int actual = integers.findFirst(e -> e > 50).orElse(0);

        assertEquals(expected, actual);
    }

    @Test
    public void testFindLast() {
        int expected = 100;
        Queue<Integer> integers = new ArrayListQueue<>(new Integer[]{ 10, 30, 60, 40, 70, 100 });

        int actual = integers.findLast(e -> e > 50).orElse(0);

        assertEquals(expected, actual);
    }

    @Test
    public void testFindAll() {
        Integer[] expected = new Integer[]{ 60, 70, 100 };
        Queue<Integer> integers = new ArrayListQueue<>(new Integer[]{ 10, 30, 60, 40, 70, 100 });

        Integer[] actual = Arrays.stream(integers.findAll(e -> e > 50)).toArray(Integer[]::new);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void testHas_whenTrue() {
        Queue<Integer> integers = new ArrayListQueue<>(new Integer[]{ 10, 30, 60, 40, 70, 100 });

        boolean actual = integers.has(60);

        assertTrue(actual);
    }

    @Test
    public void testHas_whenFalse() {
        Queue<Integer> integers = new ArrayListQueue<>(new Integer[]{ 10, 30, 60, 40, 70, 100 });

        boolean actual = integers.has(50);

        assertFalse(actual);
    }

    @Test
    public void testToArray() {
        Integer[] expected = new Integer[]{ 10, 20, 30 };
        Integer[] actual = Arrays.stream(new ArrayListQueue<>(new Integer[]{ 10, 20, 30 }).toArray()).toArray(Integer[]::new);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void testSort() {
        Integer[] expected = new Integer[]{ 10, 30, 40, 60, 70, 100 };
        Queue<Integer> integers = new ArrayListQueue<>(new Integer[]{ 10, 30, 60, 40, 70, 100 });

        integers.sort(Integer::compareTo);
        Integer[] actual = Arrays.stream(integers.toArray()).toArray(Integer[]::new);

        assertArrayEquals(expected, actual);
    }

}
