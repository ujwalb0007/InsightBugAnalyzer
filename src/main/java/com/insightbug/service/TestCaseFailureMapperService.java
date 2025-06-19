package com.insightbug.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TestCaseFailureMapperService {

    private final Map<String, String> staticMappings;

    public TestCaseFailureMapperService() {
        staticMappings = new HashMap<>();
        loadStaticMappings();
    }

    private void loadStaticMappings() {
        staticMappings.put("LoginPageTest_shouldRedirectToDashboard", "BUG-001");
        staticMappings.put("PaymentGatewayTest_shouldHandleTimeout", "BUG-003");
        staticMappings.put("CartServiceTest_shouldRemoveItemCorrectly", "BUG-004");
        staticMappings.put("UserProfileTest_shouldUpdateInfo", "BUG-005");
        staticMappings.put("SearchTest_shouldReturnRelevantResults", "BUG-006");
    }

    public Map<String, String> mapFailureToBug(String testCase, String description) {
        String bugId = null;

        // 1. Static mapping
        bugId = staticMappings.get(testCase);
        if (bugId != null) return result(bugId, "Mapped via static name");

        // 2. Module keyword mapping
        if (testCase.contains("Login") || description.contains("auth")) return result("BUG-102", "Mapped via Login keyword");
        if (testCase.contains("Payment") || description.contains("transaction")) return result("BUG-101", "Mapped via Payment keyword");
        if (testCase.contains("Cart") || description.contains("shopping cart")) return result("BUG-103", "Mapped via Cart keyword");

        // 3. Assertion/Exception mapping
        if (description.contains("expected:<true> but was:<false>")) return result("BUG-201", "Assertion failure");
        if (description.contains("NullPointerException")) return result("BUG-202", "Null pointer issue");
        if (description.contains("IndexOutOfBoundsException")) return result("BUG-203", "Index error");

        // 4. HTTP error code mapping
        if (description.contains("HTTP 500")) return result("BUG-301", "Server error");
        if (description.contains("HTTP 403")) return result("BUG-302", "Forbidden");
        if (description.contains("HTTP 404")) return result("BUG-303", "Not Found");

        // 5. Infra & DB mapping
        if (description.contains("Connection refused")) return result("BUG-401", "Infra connection issue");
        if (description.contains("TimeoutException")) return result("BUG-402", "Timeout");

        // 6. Business logic mapping
        if (description.contains("GST calculation incorrect")) return result("BUG-501", "GST logic issue");
        if (description.contains("Coupon discount not applied")) return result("BUG-503", "Discount logic error");

        return result("UNMAPPED", "No rule matched");
    }

    private Map<String, String> result(String bugId, String reason) {
        Map<String, String> map = new HashMap<>();
        map.put("mappedBugId", bugId);
        map.put("reason", reason);
        return map;
    }
}
