package com.insightbug.service;

import com.insightbug.model.BugReport;
import com.insightbug.model.BugAnalysisResult;
import com.insightbug.repository.BugReportRepository;
import com.insightbug.util.BugRule;
import com.insightbug.util.BugRuleLoade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BugAnalysisService {

    @Autowired
    private BugReportRepository bugReportRepository;

    // Load rules from JSON at startup
    private final List<BugRule> rules = BugRuleLoade.loadRules();

    public BugAnalysisResult analyzeBug(BugReport bugReport) {
        // Save the bug report to DB (optional step)
        bugReportRepository.save(bugReport);

        final String description = bugReport.getDescription() != null ? bugReport.getDescription().toLowerCase() : "";
       final String stacktrace = bugReport.getStacktrace() != null ? bugReport.getStacktrace().toLowerCase() : "";

        for (BugRule rule : rules) {
            for (String keyword : rule.getKeywords()) {
                if (description.contains(keyword.toLowerCase()) || stacktrace.contains(keyword.toLowerCase())) {
                    return new BugAnalysisResult(
                            rule.getSeverity(),
                            rule.getCause(),
                            rule.getRecommendation(),
                            bugReport
                    );
                }
            }
        }

        // Default fallback if no rule matched
        return new BugAnalysisResult(
                "Unknown",
                "Unrecognized issue",
                "Please investigate manually.",
                bugReport
        );
    }
}
