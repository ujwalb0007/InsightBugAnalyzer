package com.insightbug.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
import java.util.List;
public class BugRuleLoade {
    public static List<BugRule> loadRules() {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final InputStream is = BugRuleLoade.class.getResourceAsStream("/bug_rules.json");
            return mapper.readValue(is, new TypeReference<List<BugRule>>() {});
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

}
