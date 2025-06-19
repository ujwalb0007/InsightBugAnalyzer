package com.insightbug.controller;
import com.insightbug.model.LogPattern;
import com.insightbug.service.LogPatternService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/logs")
public class LogAnalyzerController {
    @Autowired
    private LogPatternService logPatternService;

    @GetMapping("/analyze")
    public List<LogPattern> analyzeLogs(@RequestParam(defaultValue = "logs/sample.log") String filePath) {
        return logPatternService.minePatterns(filePath);
    }

}
