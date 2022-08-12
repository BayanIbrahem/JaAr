package com.example.android.studio.ja_ar.storage.data_store;

import android.app.Application;
import android.util.Log;

import androidx.preference.PreferenceDataStore;

public class SettingsDataStorePreference extends PreferenceDataStore{
  
  DataStoreManager dataStoreManager;
  
  public SettingsDataStorePreference(Application application){
    dataStoreManager = DataStoreManager.getInstance(application);
  }
  
  @Override
  public void putBoolean(String boolean_key, boolean boolean_value){
    Log.println(Log.ERROR, "settings", "preference_data_store, put boolean " + as_str(boolean_value));
    dataStoreManager.writeBoolean(boolean_key, boolean_value);
  }
  @Override
  public void putInt(String int_key, int int_value){
    Log.println(Log.ERROR, "settings", "preference_data_store, put integer" + as_str(int_value));
    dataStoreManager.writeInteger(int_key, int_value);
  }
  @Override
  public void putLong(String long_key, long long_value){
    Log.println(Log.ERROR, "settings", "preference_data_store, put long" + as_str(long_value));
    dataStoreManager.writeLong(long_key, long_value);
  }
  @Override
  public void putFloat(String float_key, float float_value){
    Log.println(Log.ERROR, "settings", "preference_data_store, put float" + as_str((float_value)));
    dataStoreManager.writeFloat(float_key, float_value);
  }
  @Override
  public void putString(String string_key, String string_value){
    Log.println(Log.ERROR, "settings", "preference_data_store, put string" + as_str(string_key));
    dataStoreManager.writeString(string_key, string_value);
  }
  
  /*** WRITINGS METHODS *************************************/
  
  @Override
  public boolean getBoolean(String boolean_key, boolean default_value){
    boolean value =  dataStoreManager.readBoolean(boolean_key, default_value);
    Log.println(Log.ERROR, "settings", "preference_data_store, get boolean " + as_str(value));
    return value;
  }
  @Override
  public int getInt(String int_key, int default_value){
    int value =  dataStoreManager.readInt(int_key, default_value);
    Log.println(Log.ERROR, "settings", "preference_data_store, get integer " + as_str(value));
    return value;
  }
  @Override
  public long getLong(String long_key, long default_value){
    long value = dataStoreManager.readLong(long_key, default_value);
    Log.println(Log.ERROR, "settings", "preference_data_store, get long " + as_str(value));
    return value;
  }
  @Override
  public float getFloat(String float_key, float default_value){
    float value = dataStoreManager.readFloat(float_key, default_value);
    Log.println(Log.ERROR, "settings", "preference_data_store, get float " + as_str(value));
    return value;
  }
  @Override
  public String getString(String string_key, String default_value){
    String value = dataStoreManager.readString(string_key, default_value);
    Log.println(Log.ERROR, "settings", "preference_data_store, get string " + as_str(value));
    return value;
  }
  
  
  private String as_str(Object val){
    return val.toString();
  }
}
