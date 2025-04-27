package com.tomato.demo.form;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * サインアップ時のデータフォーム
 * 
 * @author ミン
 * 
 */
@Getter
@Setter
@AllArgsConstructor
public class SignupForm {
  @Pattern(regexp = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]{3,30}+$",
      message = "正しい{0}形式ではありません。")
  private String email;
  
  @Pattern(regexp = "^[a-z0-9](?=.*[a-z])(?=.*[0-9]).{2,30}$",message = "{0}は数字と英子文字の2文字以上30文字以下です。")
  private String password;
  
  @Size(min = 2, max = 30, message = "{0}は2文字以上30文字以下です。")
  private String nickname;
  

}
