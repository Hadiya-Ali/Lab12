package twitter;

import org.junit.Test;

import static org.junit.Assert.*;



public class BinarySearchTest {
// searching for integer
    @Test
    public void testInt() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        assertEquals(6, BinarySearch.binarySearch(arr, 7));
    }
// searching for string
    @Test
    public void testString() {
        String[] arr = {"hi", "hello", "hey"};
        assertEquals(1, BinarySearch.binarySearch(arr, "hello"));
        assertEquals(-1, BinarySearch.binarySearch(arr, "salam"));
    }

// searching for empty array
    @Test
    public void testEmpty() {
        assertEquals(-1, BinarySearch.binarySearch(new int[]{}, 5));
    }
}

