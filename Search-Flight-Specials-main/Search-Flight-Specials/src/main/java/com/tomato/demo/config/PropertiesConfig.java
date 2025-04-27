package com.tomato.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@PropertySource("classpath:application.properties")
@Data
public class PropertiesConfig {
	@Value("${amadeusAPI.key}")
	private String key;

	@Value("${amadeusAPI.secret}")
	private String secret;

	@Value("${csv.config.iata}")
	private int iataLength;
}