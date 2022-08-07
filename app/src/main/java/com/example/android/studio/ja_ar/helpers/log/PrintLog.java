package com.example.android.studio.ja_ar.helpers.log;

import android.util.Log;

public class PrintLog{
  public static void error(String tag, String message){
    Log.println(Log.ERROR, tag, message);
  }
}
