package com.tomato.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

/**
 * 
 * 
 */
@Configuration
public class BeanDefine {
  //@Configurationと@Beanセットで@Beanを付与したメソッドは
  //DIコンテナに登録されDI注入(@RequiredArgsConstructor配下で)
  //private finalしてインスタンス化)することができる
  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
  
  // 他のクラスでmapperを使えるようにBean定義を行う
  @Bean
  public Mapper mapper() {
      return DozerBeanMapperBuilder.buildDefault();
  }
}
