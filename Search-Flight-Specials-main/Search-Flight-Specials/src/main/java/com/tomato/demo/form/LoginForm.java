package com.tomato.demo.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * ログイン画面データフォーム
 * 
 * @author ミン
 * 
 */
@Getter
@Setter
@AllArgsConstructor
public class LoginForm {

  private String email;

  private String password;
}
