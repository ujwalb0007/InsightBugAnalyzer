package com.insightbug.repository;

import com.insightbug.model.BugReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BugReportRepository extends JpaRepository<BugReport, Long> {
    // You can add custom query methods here if needed later
}
