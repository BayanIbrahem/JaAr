package com.example.android.studio.ja_ar.enums.lang;

public enum Languages{
  en(0, "English"),
  ar(1, "Arabic"),
  ja(2, "Japanese"),
  sp(3, "Spanish"),
  fr(4, "French"),
  nan(-1, "No Language");
  
  public long lang_id = -1;
  public String name = "";
  Languages(long lang_id, String name){
    this.lang_id = lang_id;
  }
}
