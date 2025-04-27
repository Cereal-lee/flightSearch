package com.tomato.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.tomato.demo.constant.urlConstant.UrlConstant;

@Controller
public class MainController {
	/**
	 * main画面を表示
	 * 
	 * @return main.html
	 */
	@GetMapping(UrlConstant.MAIN)
	public String mainView() {
		return "index";
	}
	
	@GetMapping(UrlConstant.SEARCHRESULTSPAGE)
	public String showSearchResults() {
	    return "searchResultsView"; 
	}
}