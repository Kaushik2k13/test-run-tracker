package com.example.test_run_tracker.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TestSuiteRepository extends JpaRepository<TestResult, Long> {
}
