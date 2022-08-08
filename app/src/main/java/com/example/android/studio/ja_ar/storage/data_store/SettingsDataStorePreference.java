package com.example.android.studio.ja_ar.storage.data_store;

import android.app.Application;

import androidx.preference.PreferenceDataStore;

public class SettingsDataStorePreference extends PreferenceDataStore{
  
  DataStoreManager dataStoreManager;
  
  public SettingsDataStorePreference(Application application){
    dataStoreManager = DataStoreManager.getInstance(application);
  }
  
  @Override
  public void putBoolean(String boolean_key, boolean boolean_value){
    dataStoreManager.writeBoolean(boolean_key, boolean_value);
  }
  @Override
  public void putInt(String int_key, int int_value){
    dataStoreManager.writeInteger(int_key, int_value);
  }
  @Override
  public void putLong(String long_key, long long_value){
    dataStoreManager.writeLong(long_key, long_value);
  }
  @Override
  public void putFloat(String float_key, float float_value){
    dataStoreManager.writeFloat(float_key, float_value);
  }
  @Override
  public void putString(String string_key, String string_value){
    dataStoreManager.writeString(string_key, string_value);
  }
  
  /*** WRITINGS METHODS *************************************/
  
  @Override
  public boolean getBoolean(String boolean_key, boolean default_value){
    return dataStoreManager.readBoolean(boolean_key, default_value);
  }
  @Override
  public int getInt(String int_key, int default_value){
    return dataStoreManager.readInt(int_key, default_value);
  }
  @Override
  public long getLong(String long_key, long default_value){
    return dataStoreManager.readLong(long_key, default_value);
  }
  @Override
  public float getFloat(String float_key, float default_value){
    return dataStoreManager.readFloat(float_key, default_value);
  }
  @Override
  public String getString(String string_key, String default_value){
    return dataStoreManager.readString(string_key, default_value);
  }

}
