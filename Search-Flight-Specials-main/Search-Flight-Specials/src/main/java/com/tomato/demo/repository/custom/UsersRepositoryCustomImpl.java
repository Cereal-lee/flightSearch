package com.tomato.demo.repository.custom;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UsersRepositoryCustomImpl implements UsersRepositoryCustom {

  @Autowired
  EntityManager manager;
  
  JPAQueryFactory query;
  
  
//  @Override
//  public Optional<UsersEntity> findByEmail(String email) {
//   
//    
//    return null;
//  }

}
