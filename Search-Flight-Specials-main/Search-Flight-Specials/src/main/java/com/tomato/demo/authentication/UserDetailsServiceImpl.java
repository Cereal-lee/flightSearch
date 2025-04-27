package com.tomato.demo.authentication;

import java.util.Optional;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import com.tomato.demo.entity.UsersEntity;
import com.tomato.demo.repository.UsersRepository;
import lombok.RequiredArgsConstructor;

/**
 * 
 * Spring Securityを利用しログインする場合 実際のDBにあるユーザーデータを使用
 * 
 */
@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

  
  /**
   * ユーザー情報テーブル UsersRepository
   */
  private final UsersRepository userRepository;

  /**
   * username = ログイン画面からわたって来るemailの値
   * 
   * @param username ログインID
   * @throws UsernameNotFoundException
   */
  // ログイン画面から送られて来たusernameでDBから検索し該当レコードがない場合
  // UsernameNotFoundExceptionエラー発生
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<UsersEntity> userInfo = Optional.ofNullable(userRepository.findByEmail(username)
        .orElseThrow(() -> new UsernameNotFoundException(username)));
    // Userクラスはパスワードなど各フィールドのチェック処理に回され、チェック結果OKになった場合
    // セッションにBeanごと情報が保管される
    return User.withUsername(userInfo.get().getEmail()).password(userInfo.get().getUserPassword())
        .roles("USER").build();
  }


}
