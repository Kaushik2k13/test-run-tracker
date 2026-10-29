package com.example.test_run_tracker.web;

import com.example.test_run_tracker.domain.TestResult;
import com.example.test_run_tracker.domain.TestResultRepository;
import com.example.test_run_tracker.domain.TestRun;
import com.example.test_run_tracker.domain.TestRunRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/runs/{runId}/results")
public class TestResultController {

    private final TestResultRepository results;
    private final TestRunRepository runs;

    public TestResultController(TestResultRepository results, TestRunRepository runs){
        this.results = results;
        this.runs = runs;
    }

    @GetMapping
    public Page<ResultResponse> list(@PathVariable Long runId, Pageable pageable){
        findRun(runId);
        return results.findByRunId(runId, pageable).map(ResultResponse::from);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultResponse add(@PathVariable Long runId, @Valid @RequestBody ResultRequest request){
        TestRun run = findRun(runId);
        TestResult saved = results.save(
                new TestResult(run, request.testName(), request.outcome(), request.durationMs()));
        return ResultResponse.from(saved);

    }

    private TestRun findRun(Long runId){
        return runs.findById(runId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Run " + runId + "not found"));
    }
}
