package com.example.stepanv.android_translater.model;

import java.util.List;

/**
 * Created by stepanv on 27.12.16.
 */

public class Translation {
  private int code;
  private String lang;
  private List<String> text;

  public Translation(int code, String lang, List<String> text) {
    this.code = code;
    this.lang = lang;
    this.text = text;
  }

  public int getCode() {
    return code;
  }

  public String getLang() {
    return lang;
  }

  public List<String> getText() {
    return text;
  }
}
