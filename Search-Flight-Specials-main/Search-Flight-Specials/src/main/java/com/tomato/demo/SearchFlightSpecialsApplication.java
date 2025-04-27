package com.tomato.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.tomato.demo.service.CSVtoIATAService;

@SpringBootApplication
public class SearchFlightSpecialsApplication implements CommandLineRunner {

	@Autowired
    private CSVtoIATAService searchService;

    public static void main(String[] args) {
        SpringApplication.run(SearchFlightSpecialsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        searchService.doProcess();
    }
}

