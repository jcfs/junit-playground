package com.playground.junit;

import java.util.Objects;

public class Report {
    private String type;
    private String desc;
    private String rule;
    private String message;
    private ReportStatus status;

    public Report(String type, String desc, String rule, String message, ReportStatus status) {
        this.type = type;
        this.desc = desc;
        this.rule = rule;
        this.message = message;
        this.status = status;
    }

    public enum ReportStatus {
        OK, FAIL, IGNORED
    }

    public String getMessage() {
        return message;
    }

    public String getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    public String getRule() {
        return rule;
    }

    public ReportStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Report{" +
                "type='" + type + '\'' +
                ", desc='" + desc + '\'' +
                ", rule='" + rule + '\'' +
                ", message='" + message + '\'' +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return Objects.equals(type, report.type) &&
                Objects.equals(desc, report.desc) &&
                Objects.equals(rule, report.rule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, desc, rule, status);
    }
}
