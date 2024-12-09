
package twitter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BinarySearch {

    
    public static int binarySearch(int[] a, int t) {
    	// checking array if empty or null
        if (a == null || a.length == 0) {
            return -1; 
        }
        return binarySearch(a, t, 0, a.length - 1);
    }

    
    private static int binarySearch(int[] a, int t, int l, int h) {
    	// searching for integers
        if (l > h) {
            return -1; 
        }

        int mid = l + (h - l) / 2;

        if (a[mid] == t) {
            return mid; 
        } else if (a[mid] > t) {
        	// checking the first half
            return binarySearch(a, t, l, mid - 1); 
        } else {
        	//checking the second half
            return binarySearch(a, t, mid + 1, h); 
        }
    }

   
    public static int binarySearch(String[] a, String t) {
        if (a == null || a.length == 0 || t == null) {
            return -1; 
        }
        return binarySearch(a, t, 0, a.length - 1);
    }

   
    private static int binarySearch(String[] a, String t, int l, int h) {
    	// searching in string
        if (l > h) {
            return -1; 
        }

        int mid = l + (h - l) / 2;

        int comparison = a[mid].compareTo(t); 

        if (comparison == 0) {
            return mid; 
        } else if (comparison > 0) {
            return binarySearch(a, t, l, mid - 1); 
        } else {
            return binarySearch(a, t, mid + 1, h);
        }
    }

    
    public static int[] findOccur(int[] a, int t) {
    	// checking where the target integer is located
        if (a == null || a.length == 0) {
            return new int[0]; 
        }

        
        int index = binarySearch(a, t);
        if (index == -1) {
            return new int[0]; 
        }

        
        int[] result = new int[a.length];
        int c = 0;

       
        int l = index - 1;
        while (l >= 0 && a[l] == t) {
            result[c++] = l--;
        }

       
        result[c++] = index;

        
        int r = index + 1;
        while (r < a.length && a[r] == t) {
            result[c++] = r++;
        }

       
        int[] finalResult = new int[c];
        System.arraycopy(result, 0, finalResult, 0, c);
        // returning result

        return finalResult;
    }

    
    public static int[] findOccur(String[] a, String target) {
    	// checking the target string
        if (a == null || a.length == 0 || target == null) {
            return new int[0]; 
        }

        
        int in = binarySearch(a, target);
        if (in == -1) {
            return new int[0]; 
        }

       
        int[] result = new int[a.length];
        int count = 0;

       
        int left = in - 1;
        while (left >= 0 && a[left].equals(target)) {
            result[count++] = left--;
        }

        
        result[count++] = in;

        
        int right = in + 1;
        while (right < a.length && a[right].equals(target)) {
            result[count++] = right++;
        }

        
        int[] finalResult = new int[count];
        System.arraycopy(result, 0, finalResult, 0, count);
        // returning the value

        return finalResult;
    }

    
   
}
