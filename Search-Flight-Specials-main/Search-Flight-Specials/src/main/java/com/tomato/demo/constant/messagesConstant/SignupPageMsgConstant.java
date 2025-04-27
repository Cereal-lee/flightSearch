package com.tomato.demo.constant.messagesConstant;

/**
 * エラーメッセージID定義クラス
 * 
 * @author ミン
 */
public class SignupPageMsgConstant {

  
  /**
   * 会員登録画面:既に登録済みのEMAILで会員登録が失敗した場合
   */
  public static final String SIGNUP_EXISTED_EMAIL="signup.existed.email";
  
  /**
   * 会員登録画面:会員登録が成功した場合
   */
  public static final String SIGNUP_SUCCESSFUL="signup.successful";
  
  /**
   * 会員登録画面:フォーム入力誤り
   */
  public static final String SIGNUP_INPUT_EMAIL_EMPTY="signup.input.email.empty";
  public static final String SIGNUP_INPUT_PASS_EMPTY="signup.input.pass.empty";
  public static final String SIGNUP_INPUT_NICKNAME_EMPTY="signup.input.nickname.empty";
}
