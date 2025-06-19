package com.insightbug.util;

public class BugSignatureRule {
    private String pattern;
    private String category;
    private String suggestedFix;

    public BugSignatureRule() {}

    public BugSignatureRule(String pattern, String category, String suggestedFix) {
        this.pattern = pattern;
        this.category = category;
        this.suggestedFix = suggestedFix;
    }

    public String getPattern() { return pattern; }
    public void setPattern(String pattern) { this.pattern = pattern; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getSuggestedFix() { return suggestedFix; }
    public void setSuggestedFix(String suggestedFix) { this.suggestedFix = suggestedFix; }
}
