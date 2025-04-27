//package com.tomato.demo.config;
//
//import org.springframework.context.MessageSource;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.support.ReloadableResourceBundleMessageSource;
//
//@Configuration
//public class WebConfig {
//
//    @Bean
//    MessageSource void formValiMsgSource() {
//    ReloadableResourceBundleMessageSource bean = new ReloadableResourceBundleMessageSource();
//    
//  //メッセージのプロパティファイル名（デフォルト）を指定します
//    //下記ではmessages.propertiesファイルがセットされます
//    bean.setBasename("classpath:ValidationMessage_ja");
//    //メッセージプロパティの文字コードを指定します
//    bean.setDefaultEncoding("UTF-8");
//    return bean;
//  }
//  
//}
