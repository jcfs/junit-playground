package com.playground.junit;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportRunListener extends RunListener {
    private Map<String, List<Report>> reportMap = new HashMap<>();

    @Override
    public void testRunFinished(Result result) throws IOException {
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache m = mf.compile("mapping-report.mustache");

        StringWriter writer = new StringWriter();
        m.execute(writer, reportMap).flush();
        String html = writer.toString();

        String directory = System.getProperty("user.dir");
        String fileName = "report.html";

        Path path = Paths.get(directory, fileName);

        try {
            Files.write(path, html.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            // what
        }
    }

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
        MappingTest annotation = description.getAnnotation(MappingTest.class);
        Report report = new Report(annotation.type(), annotation.desc(), annotation.rule(), message, status);
        List<Report> reports = reportMap.computeIfAbsent(annotation.type(), s -> new ArrayList<>());

        if (!reports.contains(report)) {
            reports.add(report);
        }
    }
}
