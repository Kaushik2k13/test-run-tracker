package com.example.test_run_tracker.web;

import com.example.test_run_tracker.domain.Suite;

import java.time.Instant;

public record SuiteResponse(Long id, String name, String owner, Instant createdAt) {
    public static SuiteResponse from(Suite suite){
        return new SuiteResponse(suite.getId(), suite.getName(), suite.getOwner(), suite.getCreatedAt());
    }
}
