package com.example.test_run_tracker.web;

import com.example.test_run_tracker.domain.Outcome;
import com.example.test_run_tracker.domain.TestResult;

public record ResultResponse(Long id, String testName, Outcome outcome, Integer durationMs) {
    public static ResultResponse from(TestResult result){
        return new ResultResponse(result.getId(), result.getTestName(), result.getOutcome(), result.getDurationMs());
    }
}
