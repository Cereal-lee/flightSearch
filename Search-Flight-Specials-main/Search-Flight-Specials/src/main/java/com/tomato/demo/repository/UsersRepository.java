package com.tomato.demo.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.tomato.demo.entity.UsersEntity;


/**
 * Usersレポジトリーインターフェース
 * @author=ミン
 * 
 * UsersRepositoryインターフェース Repositoryが
 * インターフェースの理由はSpring Data JPSが基本的なCRUDメソッドを 提供するため
 * DBアクセスを行う
 * 
 */
@Repository
@EnableJpaRepositories
public interface UsersRepository extends JpaRepository<UsersEntity, String> {
  // UsersEntityはUsersEntityクラスの@Entity(name = "UsersEntity")を引用する
  // UserEntityを参照するには下記のように上位のパッケージから記述するべき
  // とりあえず長すぎるので後々プロパティーファイルに定義し管理しよう
  // @Query("SELECT u.email, u.userPassword FROM UsersEntity u WHERE u.email = :email")
  @Query("SELECT new com.tomato.demo.entity.UsersEntity(u.userId, u.email, u.userPassword, u.nickname) FROM UsersEntity u WHERE u.email = :email")
  public Optional<UsersEntity> findByEmail(@Param("email") String email);

//  @Query("SELECT new com.tomato.demo.entity.UsersEntity(u.userId, u.email, u.userPassword, u.nickname) FROM UsersEntity u WHERE u.email = :email")
//  public Optional<UsersEntity> findByEmail(Optional<UsersEntity> exEmail);
}
