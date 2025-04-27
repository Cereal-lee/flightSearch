package com.tomato.demo.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.tomato.demo.constant.urlConstant.UrlConstant;
import lombok.RequiredArgsConstructor;

/**
 * Spring Securityカスタマイズ用
 */
@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {
  
  /** パスワードエンコーダー　*/
  private final PasswordEncoder passwordEncoder;
  
  /** ユーザー情報取得Service */
  private final UserDetailsService userDetailsService;
  
  /** メッセージ取得 */
  private final MessageSource messageSource;
  
  /** ユーザー名のname属性 */
  private final String USERNAME_PARAMETER = "email";

  /**
   * Spring Securityカスタマイズ用
   * 
   * @author ミン
   * 
   * @param http
   * @return
   * @throws Exception
   * 
   * 
   */
  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    // loginView画面からemailという名でパラメータを取得して来る
    // defaultSuccessUrl("/")):ログインに成功した場合遷移されるURL
    /**
     * URLの指定について
     * .requestMatcers : 任意のURLを指定
     * .anyRequest : requestMatcersで指定されているURL以外のURLが対象
     * 
     * 権限内容に応じたメソッドについて
     * .permiAll : user認証済みか関係なくアクセス可能
     * .denyAll : 認証済みでもアクセス不可
     * .authenticated : 認証済みの場合アクセス可
     * 
     */

    http.authorizeHttpRequests(
        ahtu -> ahtu.requestMatchers(UrlConstant.NO_AUTHENCATION).permitAll().anyRequest().authenticated())
        .formLogin(login -> login.loginPage(UrlConstant.LOGIN).usernameParameter(USERNAME_PARAMETER)
            .defaultSuccessUrl("/"));

    return http.build();
  }
  
  /**
   * Provider定義 
   * カスタマイズprovider情報
   * 
   * @return カスタマイズProvider情報
   */
  
  @Bean
  DaoAuthenticationProvider daoAuthenAuthConfigProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    
    provider.setUserDetailsService(userDetailsService);
    provider.setPasswordEncoder(passwordEncoder);
    provider.setMessageSource(messageSource);
    return provider;
  }
}
