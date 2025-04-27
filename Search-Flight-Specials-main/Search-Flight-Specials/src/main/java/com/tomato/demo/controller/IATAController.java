package com.tomato.demo.controller;

import org.springframework.web.bind.annotation.*;

import com.tomato.demo.constant.urlConstant.UrlConstant;
import com.tomato.demo.service.CSVtoIATAService;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(UrlConstant.API)
public class IATAController {

	private final CSVtoIATAService csvtoIATAService;

	public IATAController(CSVtoIATAService csvtoIATAService) {
		this.csvtoIATAService = csvtoIATAService;
	}

	@PostMapping(UrlConstant.SEARCHIATACODE)
	public List<Map<String, String>> searchIATAByKeyword(@RequestBody Map<String, String> request) {
		String keyword = request.get("keyword");
		return csvtoIATAService.searchIATAByKeyword(keyword);
	}
}