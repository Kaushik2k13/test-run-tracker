package com.example.test_run_tracker.web;

import com.example.test_run_tracker.domain.Suite;
import com.example.test_run_tracker.domain.SuiteRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/suites")
public class SuiteController {
    private final SuiteRepository suites;

    public SuiteController(SuiteRepository suites){
        this.suites=suites;
    }

    @GetMapping
    public Page<SuiteResponse> list(Pageable pageable) {
        return suites.findAll(pageable).map(SuiteResponse::from);
    }

    @GetMapping("/{id}")
    public SuiteResponse get(@PathVariable Long id) {
        return SuiteResponse.from(find(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SuiteResponse create(@Valid @RequestBody SuiteRequest request){
        Suite saved = suites.save(new Suite(request.name(), request.owner()));
        return SuiteResponse.from(saved);
    }
    @PutMapping("/{id}")
    public SuiteResponse update(@PathVariable Long id, @Valid @RequestBody SuiteRequest request){
        Suite suite = find(id);
        suite.setName(request.name());
        suite.setOwner(request.owner());
        return SuiteResponse.from(suites.save(suite));
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        suites.delete(find(id));
    }
    private Suite find(Long id){
        return suites.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Suite" + id + " not found"));
    }
}
