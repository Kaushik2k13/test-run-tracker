package com.example.test_run_tracker.web;

import com.example.test_run_tracker.domain.Outcome;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ResultRequest(@NotBlank @Size(max = 200) String testName,
                            @NotNull Outcome outcome,
                            @NotNull @Min(0) Integer durationMs) {
}
