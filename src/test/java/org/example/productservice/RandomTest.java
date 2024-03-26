package org.example.productservice;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RandomTest {

    @Test
    void testAddition(){
        int i = 1+2;
        //assert i == 3;

        assertTrue(i == 3);
        assertEquals(3, i);
    }
}
