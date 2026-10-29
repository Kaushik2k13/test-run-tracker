package com.example.test_run_tracker.web;

import com.example.test_run_tracker.domain.RunStatus;
import com.example.test_run_tracker.domain.TestRun;

import java.lang.classfile.Instruction;
import java.time.Instant;

public record RunResponse(Long id, Long suiteId, RunStatus status, Instant startedAt, Instant finishedAt) {
    public static RunResponse from(TestRun run) {
        return new RunResponse(run.getId(), run.getSuite().getId(), run.getStatus(),
                run.getStartedAt(), run.getFinishedAt());
    }
}
