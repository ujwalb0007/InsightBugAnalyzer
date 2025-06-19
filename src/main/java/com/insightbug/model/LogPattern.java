package com.insightbug.model;

public class LogPattern {
    private String pattern;
    private int count;
    private String lastSeen;

    public LogPattern() {}

    public LogPattern(String pattern, int count, String lastSeen) {
        this.pattern = pattern;
        this.count = count;
        this.lastSeen = lastSeen;
    }

    // Getters and Setters
    public String getPattern() { return pattern; }
    public void setPattern(String pattern) { this.pattern = pattern; }

    public int getCount() { return count; }
    public void setCount(int count) { this.count = count; }

    public String getLastSeen() { return lastSeen; }
    public void setLastSeen(String lastSeen) { this.lastSeen = lastSeen; }
}
