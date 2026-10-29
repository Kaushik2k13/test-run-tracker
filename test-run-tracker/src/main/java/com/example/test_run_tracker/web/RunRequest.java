package com.example.test_run_tracker.web;

import jakarta.validation.constraints.NotNull;

public record RunRequest(@NotNull Long suiteId) {

}
