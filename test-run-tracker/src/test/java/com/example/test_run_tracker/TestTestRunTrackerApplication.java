package com.example.test_run_tracker;

import org.springframework.boot.SpringApplication;

public class TestTestRunTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.from(TestRunTrackerApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
