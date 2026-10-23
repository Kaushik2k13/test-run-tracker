package com.example.test_run_tracker.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRunRepository extends JpaRepository<TestRun, Long> {
}
