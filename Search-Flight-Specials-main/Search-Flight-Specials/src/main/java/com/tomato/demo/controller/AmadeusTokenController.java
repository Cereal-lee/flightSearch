package com.tomato.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tomato.demo.config.PropertiesConfig;
import com.tomato.demo.constant.urlConstant.UrlConstant;

@RestController
@RequestMapping(UrlConstant.API)
public class AmadeusTokenController {
	
	private final PropertiesConfig amadeusConfig;
	
	public AmadeusTokenController(PropertiesConfig amadeusConfig) {
        this.amadeusConfig = amadeusConfig;
    }
	
	@GetMapping(UrlConstant.GETAMADEUSKEY)
    public ResponseEntity<Map<String, String>> getCredentials() {
        Map<String, String> credentials = new HashMap<>();
        credentials.put("key", amadeusConfig.getKey()); 
        credentials.put("secret", amadeusConfig.getSecret()); 
        
        return ResponseEntity.ok(credentials);
    }
}
