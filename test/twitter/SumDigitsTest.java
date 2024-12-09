package twitter;

import static org.junit.Assert.*;
import org.junit.Test;

public class SumDigitsTest {

    @Test
    public void testZero() {
     
        assertEquals(0, SumDigits.sumOfDigits(0));
    }

    @Test
    public void testOne() {
        
        assertEquals(6, SumDigits.sumOfDigits(6));
    }

    @Test
    public void testMultiple() {
        
        assertEquals(9, SumDigits.sumOfDigits(234));
    }

    
    @Test
    public void testNegative() {
        
        assertEquals(6, SumDigits.sumOfDigits(-123)); 
    }
}
