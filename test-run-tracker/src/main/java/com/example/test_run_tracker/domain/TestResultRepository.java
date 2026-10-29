package com.example.test_run_tracker.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestResultRepository extends JpaRepository<TestResult, Long> {
    Page<TestResult> findByRunId(Long runId, Pageable pageable);
}
