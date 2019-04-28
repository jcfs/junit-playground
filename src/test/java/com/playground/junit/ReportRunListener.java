package com.playground.junit;

import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

public class ReportRunListener extends RunListener {
    @Override
    public void testIgnored(Description description) {
        addReport(description, Report.ReportStatus.IGNORED);
    }

    @Override
    public void testFinished(Description description) {
        addReport(description, Report.ReportStatus.OK);
    }

    @Override
    public void testFailure(Failure failure) {
        addReport(failure.getDescription(), failure.getMessage(), Report.ReportStatus.FAIL);
    }

    private void addReport(Description description, Report.ReportStatus status) {
        addReport(description, null, status);
    }

    private void addReport(Description description, String message, Report.ReportStatus status) {
        ReportInstance.instance().addReport(description, message, status);
    }
}
