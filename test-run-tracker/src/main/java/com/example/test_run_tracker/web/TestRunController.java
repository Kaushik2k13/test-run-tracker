package com.example.test_run_tracker.web;

import com.example.test_run_tracker.domain.*;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;

@RestController
@RequestMapping("/runs")
public class TestRunController {
    private final TestRunRepository runs;
    private final SuiteRepository suites;

    public TestRunController(TestRunRepository runs, SuiteRepository suites) {
        this.runs = runs;
        this.suites = suites;
    }

    @GetMapping
    public Page<RunResponse> list(Pageable pageable) {
        return runs.findAll(pageable).map(RunResponse::from);
    }

    @GetMapping("/{id}")
    public RunResponse get(@PathVariable Long id) {
        return RunResponse.from(find(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RunResponse create(@Valid @RequestBody RunRequest request) {
        Suite suite = suites.findById(request.suiteId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Suite " + request.suiteId() + " does not exist"));
        return RunResponse.from(runs.save(new TestRun(suite)));
    }

    @PutMapping("/{id}/status")
    public RunResponse updateStatus(@PathVariable Long id, @Valid @RequestBody StatusRequest request) {
        TestRun run = find(id);
        run.setStatus(request.status());
        if (request.status() == RunStatus.RUNNING) {
            run.setStartedAt(Instant.now());
        }
        if (request.status() == RunStatus.PASSED || request.status() == RunStatus.FAILED) {
            run.setFinishedAt(Instant.now());
        }
        return RunResponse.from(runs.save(run));
    }

    private TestRun find(Long id) {
        return runs.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Run " + id + " not found"));
    }
}
