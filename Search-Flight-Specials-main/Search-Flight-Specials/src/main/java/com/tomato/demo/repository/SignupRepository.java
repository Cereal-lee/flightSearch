package com.tomato.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tomato.demo.entity.UsersEntity;

public interface SignupRepository extends JpaRepository<UsersEntity, String>  {

}
