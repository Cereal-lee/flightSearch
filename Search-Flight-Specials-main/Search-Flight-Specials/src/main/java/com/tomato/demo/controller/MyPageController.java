package com.tomato.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.tomato.demo.constant.urlConstant.UrlConstant;
import ch.qos.logback.core.model.Model;

@Controller
public class MyPageController {
  
  @GetMapping(UrlConstant.MYPAGE)
  public String myPageView(Model model) {
    return "myPageView";
  }

}
