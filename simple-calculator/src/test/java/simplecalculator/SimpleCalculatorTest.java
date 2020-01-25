package simplecalculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SimpleCalculatorTest {

    @Test
    void add(){
        Assertions.assertEquals(4, SimpleCalculator.add(1,3));
    }
}
