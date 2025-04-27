package com.tomato.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;


/**
 * ユーザ情報テーブルEntity
 * DBのUSERテーブルから取得した情報を保管するクラス
 * 
 * @author ミン
 * 
 * 
 * 
 */
@Table(name = "Users") // table上のテーブル名を作成
@Entity(name = "UsersEntity")
@Getter
@Setter
@ToString
@AllArgsConstructor
public class UsersEntity {
  public UsersEntity() {
    // TODO 自動生成されたコンストラクター・スタブ
  }
  /**
   * ユーザID
   */
  @Id
//  @GeneratedValue(strategy = GenerationType.UUID)
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "USERID")
  private String userId;
  /** ユーザemail */
  @NonNull
  @Column(name = "EMAIL")
  private String email;
  /** ユーザパスワード */
  @NonNull
  @Column(name = "USERPASSWORD")
  private String userPassword;
  /** ユーザニックネーム */
  @NonNull
  @Column(name = "NICKNAME")
  private String nickname;

}


