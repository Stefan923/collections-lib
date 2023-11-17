package me.stefan923.collections.map;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class HashMapTest {

    @Test
    public void testPutAndGet() {
        HashMap<String, Integer> hashMap = new HashMap<>();
        String[] mapKeys = new String[]{ "John", "Anne", "Justin", "Jake" };
        int[] mapValues = new int[]{ 10, 20, 30, 40 };

        int[] indexes = new int[]{ 2, 0, 3, 1 };

        for (int index = 0; index < mapKeys.length; ++index) {
            hashMap.put(mapKeys[index], mapValues[index]);
        }

        for (int index : indexes) {
            Optional<Integer> returnedValue = hashMap.get(mapKeys[index]);

            assertTrue(returnedValue.isPresent());
            assertEquals(mapValues[index], returnedValue.get());
        }
    }

    @Test
    public void testRemove_whenContained() {
        HashMap<String, Integer> hashMap = new HashMap<>();
        String[] mapKeys = new String[]{ "John", "Anne", "Justin", "Justin", "Jake" };
        int[] mapValues = new int[]{ 10, 20, 70, 30, 40 };

        int[] indexes = new int[]{ 3, 0, 4, 1 };

        for (int index = 0; index < mapKeys.length; ++index) {
            hashMap.put(mapKeys[index], mapValues[index]);
        }

        hashMap.remove(mapKeys[2]);

        for (int index : indexes) {
            Optional<Integer> returnedValue = hashMap.get(mapKeys[index]);

            assertTrue(returnedValue.isPresent());
            assertEquals(mapValues[index], returnedValue.get());
        }

        hashMap.remove(mapKeys[3]);

        assertFalse(hashMap.get(mapKeys[3]).isPresent());
    }

    @Test
    public void testRemove_whenNotContained() {
        HashMap<String, Integer> hashMap = new HashMap<>();
        String[] mapKeys = new String[]{ "John", "Anne", "Justin", "Jake" };
        int[] mapValues = new int[]{ 10, 20, 70, 30, 40 };

        int[] indexes = new int[]{ 0, 3, 1 };

        for (int index = 0; index < mapKeys.length; ++index) {
            hashMap.put(mapKeys[index], mapValues[index]);
        }

        assertTrue(hashMap.remove(mapKeys[2]));

        for (int index : indexes) {
            Optional<Integer> returnedValue = hashMap.get(mapKeys[index]);

            assertTrue(returnedValue.isPresent());
            assertEquals(mapValues[index], returnedValue.get());
        }

        assertFalse(hashMap.remove(mapKeys[2]));

        assertFalse(hashMap.get(mapKeys[2]).isPresent());
    }

}
