package com.insightbug.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.insightbug.util.BugSignatureRule;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class SignatureClassifierService {
    private List<BugSignatureRule> rules = new ArrayList<>();

    @PostConstruct
    public void loadRules() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream is = getClass().getClassLoader().getResourceAsStream("rules.json");
            rules = mapper.readValue(is, new TypeReference<List<BugSignatureRule>>() {});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<BugSignatureRule> match(String stackTrace) {
        List<BugSignatureRule> matched = new ArrayList<>();
        for (BugSignatureRule rule : rules) {
            if (stackTrace.contains(rule.getPattern())) {
                matched.add(rule);
            }
        }
        return matched;
    }

}
