package twitter;

import org.junit.Test;
import static org.junit.Assert.*;

public class MathParserTest {
// checking addition
    @Test
    public void testAddition() throws Exception {
        String expression = "2 + 3";
        double re = MathParser.evaluate(expression);
        assertEquals(5.0, re, 0.0001); 
    }
//checking operators
    @Test
    public void testOperators() throws Exception {
        String expression = "2 + 4 * 2";
        double re = MathParser.evaluate(expression);
        assertEquals(10.0, re, 0.0001); 
    }
// testing parenthesis
    @Test
    public void testParentheses() throws Exception {
        String expression = "(2 + 3) * 2";
        double result = MathParser.evaluate(expression);
        assertEquals(10.0, result, 0.0001);  
    }
}
