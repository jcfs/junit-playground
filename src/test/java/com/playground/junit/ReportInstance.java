package com.playground.junit;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import org.junit.runner.Description;

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

/**
 * SINGLETON INSTANCE
 */
public class ReportInstance {
    private static final ReportInstance INSTANCE = new ReportInstance();

    private Map<String, List<Report>> reportMap = new HashMap<>();

    public synchronized void writeReport() {
        try {
            MustacheFactory mf = new DefaultMustacheFactory();
            Mustache m = mf.compile("mapping-report.mustache");
            StringWriter writer = new StringWriter();
            m.execute(writer, reportMap).flush();
            String html = writer.toString();
            String directory = System.getProperty("user.dir");
            String fileName = "report.html";
            Path path = Paths.get(directory, fileName);
            Files.write(path, html.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            // what
        }
    }

    public synchronized void addReport(Description description, String message, Report.ReportStatus status) {
        MappingTest annotation = description.getAnnotation(MappingTest.class);

        Report report = new Report(description.getMethodName(), annotation.desc(), annotation.rule(), message, status);
        List<Report> reports = reportMap.computeIfAbsent(annotation.type(), s -> new ArrayList<>());

        if (!reports.contains(report)) {
            reports.add(report);
        }
    }

    public static ReportInstance instance() {
        return INSTANCE;
    }
}
