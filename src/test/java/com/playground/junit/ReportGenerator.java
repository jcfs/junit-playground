package com.playground.junit;

import com.playground.junit.report.TestClassA;
import com.playground.junit.report.TestClassB;
import org.junit.Test;
import org.junit.runner.*;

public class ReportGenerator {

    @Test
    public void runAll() {
        JUnitCore core = new JUnitCore();
        core.addListener(new ReportRunListener());
        Request request = Request.classes(TestClassA.class, TestClassB.class);
        request.filterWith(Description.createSuiteDescription("mapping", MappingTest.class));
        core.run(request);
    }
}
