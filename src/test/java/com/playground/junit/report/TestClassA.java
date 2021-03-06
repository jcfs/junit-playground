package com.playground.junit.report;


import com.playground.junit.MappingTest;
import com.playground.junit.ReportGeneratorRunner;
import org.junit.Ignore;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(ReportGeneratorRunner.class)
public class TestClassA {

    @MappingTest(desc = "Test that maps X -> Y", rule = "F0001", type = "VdiTitle")
    public void testAdd1() {
        assertEquals(42, Integer.sum(19, 23));
    }

    @Ignore
    @MappingTest(desc = "Test that maps X -> Y", rule = "F0002", type = "VdiTitle")
    public void testAdd2() {
        assertEquals(42, Integer.sum(19, 23));
    }

    @MappingTest(desc = "Test that maps X -> Y", rule = "F0003", type = "VdiTitle")
    public void testAdd3() {
        assertEquals(42, Integer.sum(12, 23));
    }
}
