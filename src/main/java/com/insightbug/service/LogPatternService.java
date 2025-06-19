package com.insightbug.service;
import com.insightbug.model.LogPattern;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class LogPatternService {
    private final Map<String, LogPattern> patternMap = new HashMap<>();

    public List<LogPattern> minePatterns(String logFilePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(logFilePath))) {
            String line;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentTimestamp = sdf.format(new Date());

            while ((line = br.readLine()) != null) {
                String normalized = normalizeLine(line);
                if (normalized.length() > 5) {
                    LogPattern lp = patternMap.getOrDefault(normalized, new LogPattern(normalized, 0, currentTimestamp));
                    lp.setCount(lp.getCount() + 1);
                    lp.setLastSeen(currentTimestamp);
                    patternMap.put(normalized, lp);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ArrayList<>(patternMap.values());
    }

    private String normalizeLine(String line) {
        // Remove variable values like numbers and timestamps
        return line.replaceAll("\\d+", "X")
                .replaceAll("([0-9]{2,4}-){2,}[0-9]{2,4}", "")
                .replaceAll("\\s+", " ")
                .trim();
    }
}
