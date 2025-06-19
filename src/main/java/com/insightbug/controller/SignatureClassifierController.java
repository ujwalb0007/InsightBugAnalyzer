package com.insightbug.controller;
import com.insightbug.util.BugSignatureRule;
import com.insightbug.service.SignatureClassifierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/signature")
public class SignatureClassifierController {
    @Autowired
    private SignatureClassifierService classifierService;

    @PostMapping("/match")
    public List<BugSignatureRule> matchSignatures(@RequestBody String stackTrace) {
        return classifierService.match(stackTrace);
    }


}
