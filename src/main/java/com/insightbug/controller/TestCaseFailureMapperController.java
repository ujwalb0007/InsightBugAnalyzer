package com.insightbug.controller;

import com.insightbug .service.TestCaseFailureMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/test-mapper")
public class TestCaseFailureMapperController {

    @Autowired
    private TestCaseFailureMapperService testCaseFailureMapperService;

    @PostMapping("/map")
    public Map<String, String> mapTestFailure(@RequestBody Map<String, String> input) {
        String testCase = input.getOrDefault("testCase", "");
        String description = input.getOrDefault("description", "");
        return testCaseFailureMapperService.mapFailureToBug(testCase, description);
    }
}
