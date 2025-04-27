package com.tomato.demo.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import com.tomato.demo.constant.urlConstant.UrlConstant;
import com.tomato.demo.form.LoginForm;
import com.tomato.demo.service.UsersService;
import lombok.RequiredArgsConstructor;

/**
 * Loginコントローラークラス
 * 
 * @author=ミン
 * 
 * @ 画面からの入力値を受け取る Serviceクラスを呼び出す 遷移先の画面を呼び出す
 * 
 */
@Controller
@RequiredArgsConstructor
public class LoginController {

  @Autowired
  private final UsersService usersService;

  /** password encoder */
  private final PasswordEncoder passwordEncoder;

  /** message.property */
  private final MessageSource messagrSource;

  /**
   * セッション情報 セッションからエラー情報を取得
   */
  private final HttpSession httpSession;

  /**
   * login画面を表示
   * 
   * @return loginView.html
   *
   */
  @GetMapping(UrlConstant.LOGIN)
  public String loginView(Model model, LoginForm form) {
    return "loginView";
  }


  /**
   * ログインが失敗した後の画面表示
   * 
   * @param validationエラーが発生した場合errorが語尾について遷移する
   * @return loginView.html
   */
  @GetMapping(value = UrlConstant.LOGIN, params = "error")
  public String loginWithErrorView(Model model, @Valid LoginForm form,
      BindingResult bindingResult) {
    Exception errorinfo =
        (Exception) httpSession.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    
    if(!errorinfo.getMessage().isEmpty()) {
      model.addAttribute("errorMsg", errorinfo.getMessage());
    }
    return "loginView";
  }

}
