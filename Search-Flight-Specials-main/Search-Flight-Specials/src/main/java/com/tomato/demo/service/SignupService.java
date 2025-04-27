package com.tomato.demo.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.tomato.demo.entity.UsersEntity;
import com.tomato.demo.form.SignupForm;
import com.tomato.demo.repository.SignupRepository;
import com.tomato.demo.repository.UsersRepository;
import lombok.RequiredArgsConstructor;

/**
 * 会員ん登録Service
 * 
 * @author ミン
 * 
 */
@Service
@RequiredArgsConstructor
public class SignupService {
  @Autowired
  private final UsersRepository usersRepository;
  
  /** ユーザーじょうほうテーブルDAO */
  @Autowired
  private final SignupRepository signupRepository;
  
  
  /** password encode */
  final PasswordEncoder passwordEncoder;
  
  /**
   * ユーザー情報テーブル新規登録
   * 
   * @param form
   * @return 登録情報(ユーザー情報Emtity),既に同じユーザIDで登録がある場合はEmpty
   */
  @SuppressWarnings("null")
  public Optional<UsersEntity> signupUser(SignupForm form) {
    UsersEntity usersEntity = new UsersEntity();
    
    // 登録済みのemailなのか確認
    Optional<UsersEntity> userExisted = usersRepository.findByEmail(form.getEmail());
    if(userExisted.isPresent()) {
      return Optional.empty();
    }
    
    // 会員登録する前にpasswordをハッシュ化
    String encodePassword = passwordEncoder.encode(form.getPassword());
    // 第一引数がコピー元の情報, 第二引数はコピー先の情報
    // 下記のコードはformの情報をUsersEntityクラスにコピーした
    // 情報をリターンする
    usersEntity.setEmail(form.getEmail());
    usersEntity.setUserPassword(encodePassword);
    usersEntity.setNickname(form.getNickname());
    // dwadsd
    return  Optional.of(signupRepository.save(usersEntity));
  }
  
  /**
   * 会員登録済みのユーザーなのか確認
   * 
   * @param email
   * @return Boolean
   */
//  public  Optional<UsersEntity> isExEmail(String email) {
//    Optional<UsersEntity> result = usersRepository.findByEmail(isExEmail(email));
//    
//    return result;
//  }
  
}
