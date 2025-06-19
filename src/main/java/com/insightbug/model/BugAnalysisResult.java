package com.insightbug.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
@Entity
public class BugAnalysisResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String severity;
    private String probableCause;
    private String recommendation;

    @OneToOne
    @JoinColumn(name = "bug_report_id")
    @JsonIgnore
    private BugReport bugReport;

    public BugAnalysisResult() {
    }

    public BugAnalysisResult(String severity, String probableCause, String recommendation, BugReport bugReport) {
        this.severity = severity;
        this.probableCause = probableCause;
        this.recommendation = recommendation;
        this.bugReport = bugReport;
    }

    public Long getId() {
        return id;
    }

    public String getSeverity() {
        return severity;
    }

    public String getProbableCause() {
        return probableCause;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public BugReport getBugReport() {
        return bugReport;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public void setProbableCause(String probableCause) {
        this.probableCause = probableCause;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public void setBugReport(BugReport bugReport) {
        this.bugReport = bugReport;
    }

}
