package com.insightbug.controller;

import com.insightbug.model.BugReport;
import com.insightbug.model.BugAnalysisResult;
import com.insightbug.service.BugAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bug")
public class BugAnalysisController {

    @Autowired
    private BugAnalysisService bugAnalysisService;

    @PostMapping("/analyze")
    public BugAnalysisResult analyzeBug(@RequestBody BugReport bugReport) {
        return bugAnalysisService.analyzeBug(bugReport);
    }
}
