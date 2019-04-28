package com.playground.junit.report;


import com.playground.junit.MappingTest;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestClassA {

    @Test
    @MappingTest(desc = "Test that maps X -> Y", rule = "F0001", type = "VdiTitle")
    public void testAdd1() {
        assertEquals(42, Integer.sum(19, 23));
    }

    @Test
    @Ignore
    @MappingTest(desc = "Test that maps X -> Y", rule = "F0002", type = "VdiTitle")
    public void testAdd2() {
        assertEquals(42, Integer.sum(19, 23));
    }

    @Test
    @MappingTest(desc = "Test that maps X -> Y", rule = "F0003", type = "VdiTitle")
    public void testAdd3() {
        assertEquals(42, Integer.sum(12, 23));
    }
}
