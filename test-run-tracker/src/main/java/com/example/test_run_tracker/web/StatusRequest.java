package com.example.test_run_tracker.web;

import com.example.test_run_tracker.domain.RunStatus;
import jakarta.validation.constraints.NotNull;

public record StatusRequest(@NotNull RunStatus status) {
}
