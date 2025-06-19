package com.insightbug.controller;
import com.insightbug.service.MLImpactScoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/ml")
public class MLImpactController {
    @Autowired
    private MLImpactScoringService mlImpactScoringService;

    @PostMapping("/score")
    public Map<String, Object> scoreBug(@RequestBody Map<String, String> input) {
        String title = input.getOrDefault("title", "");
        String description = input.getOrDefault("description", "");
        double score = mlImpactScoringService.scoreBug(title, description);

        return Map.of(
                "impactScore", score,
                "message", "ML impact score calculated successfully"
        );
    }


}
