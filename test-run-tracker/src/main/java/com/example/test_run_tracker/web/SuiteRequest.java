package com.example.test_run_tracker.web;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SuiteRequest ( @NotBlank @Size(max = 100) String name,
                            @NotBlank @Size(max = 100) String owner){

}
