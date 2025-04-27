package com.tomato.demo.util;

import java.util.Locale;
import org.springframework.context.MessageSource;

/**
 * アプリケーション共通クラス
 * 
 * @author ミン
 * 
 */
public class AppUtil {
  
  /**
   * message.propertiesからmessage内容を取得するメソッド
   * 
   * @author ミン
   * @param massageSource
   * @param key 
   * @param params 「外部から置換変数を渡すことができる」
   * @return
   */
  // Object... => <データ型>で定義している引数を可変引数と呼ぶ 
  public static String getMessage(MessageSource massageSource, String key, Object... params) {
    return massageSource.getMessage(key, params, Locale.JAPANESE);
  }
}
