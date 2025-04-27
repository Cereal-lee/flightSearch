package com.tomato.demo.controller;

import java.util.Optional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.tomato.demo.constant.messagesConstant.SignupPageMsgConstant;
import com.tomato.demo.constant.urlConstant.UrlConstant;
import com.tomato.demo.entity.UsersEntity;
import com.tomato.demo.form.SignupForm;
import com.tomato.demo.service.SignupService;
import com.tomato.demo.util.AppUtil;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SignupController {

  @Autowired
  private final SignupService signupService;


  /** message.property */
  private final MessageSource messagrSource;

  /**
   * 会員登録画面表示
   * 
   * @author ミン
   * @param model
   * @param form 入力
   * @return signupView.html
   */
  @GetMapping(UrlConstant.SIGNUP)
  public String signupView(Model model, SignupForm form) {
    return "signupView";
  }

  /**
   * 会員登録
   * 
   * @author ミン
   * @param model
   * @param form 入力
   * @return signupView.html
   */
  @PostMapping(UrlConstant.SIGNUP)
//   public String signup(Model model, @Valid SignupForm form, BindingResult bindingResult,
//   RedirectAttributes reAttributes) {
  public String signup(Model model, @Valid SignupForm form, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "signupView";
    }
    String message;
    Optional<UsersEntity> userInfo = signupService.signupUser(form);

    message = editMessage(userInfo);
    if (!userInfo.isEmpty()) {
      model.addAttribute("message", message);
//      return "redirect:/signupView";
    } else {
      model.addAttribute("errorMsg", message);
    }
    return "signupView";

  }

  /**
   * userInfoの結果によってMessageを返還するメソッド
   * 
   * @param userInfo ユーザー登録結果
   * @return message
   */
  private String editMessage(Optional<UsersEntity> userInfo) {
    String message;
    if (userInfo.isEmpty()) {
      message = AppUtil.getMessage(messagrSource, SignupPageMsgConstant.SIGNUP_EXISTED_EMAIL);
    } else {
      message = AppUtil.getMessage(messagrSource, SignupPageMsgConstant.SIGNUP_SUCCESSFUL);
    }
    return message;
  }

  // @ResponseStatus(HttpStatus.BAD_REQUEST)
  // @ExceptionHandler(MethodArgumentNotValidException.class)
  // public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
  //
  // Map<String, String> errors = new HashedMap<>();
  //
  // }

}
