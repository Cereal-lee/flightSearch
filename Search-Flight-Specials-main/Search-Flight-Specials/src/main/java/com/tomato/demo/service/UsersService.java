package com.tomato.demo.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tomato.demo.entity.UsersEntity;
import com.tomato.demo.repository.UsersRepository;
import lombok.RequiredArgsConstructor;

/**
 * Usersサービスクラス
 * 
 * @author=ミン
 * 
 * チェック処理やDBアクセス等の
 * ビジネスロジックを実装する
 * 
 */
// RequiredArgsConstructor：private final宣言したインターフェースに対して
// newしたものを注入するコンストラクタを実装する
@Service
@RequiredArgsConstructor 
public class UsersService {
  
  @Autowired
  private final UsersRepository userRepository;
  
  /**
   * ユーザIDでユーザー情報を取得
   * 
   * @param userId
   * @return userInfo
   * 
   */
  public Optional<UsersEntity> searchUserById(String userId) {
    
    return userRepository.findById(userId);
    
  }
  
  
  /**
   * ユーザemailでユーザー情報を取得
   * 
   * @param email
   * @return userInfo
   * 
   */
  public Optional<UsersEntity> searchUserByEmail(String email) {
    return userRepository.findByEmail(email);
    
  }
  
  
}
