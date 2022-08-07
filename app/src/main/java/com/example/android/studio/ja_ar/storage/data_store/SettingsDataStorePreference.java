package com.example.android.studio.ja_ar.storage.data_store;

import android.app.Application;
import android.content.Context;

import androidx.datastore.preferences.core.Preferences;
import androidx.datastore.preferences.rxjava2.RxPreferenceDataStoreBuilder;
import androidx.datastore.rxjava2.RxDataStore;
import androidx.preference.PreferenceDataStore;

public class SettingsDataStorePreference extends PreferenceDataStore{
  
  private RxDataStore<Preferences> dataStore;
  
  public SettingsDataStorePreference(Application application){
    Context context = application.getApplicationContext();
    this.dataStore = new RxPreferenceDataStoreBuilder(context, "settings_dataStore").build();
  }
  
  @Override
  public void putString(String string_key, String string_value){
    //TODO: make this method
  }
  @Override
  public void putInt(String int_key, int int_value){
    //TODO: make this method
  }
  @Override
  public void putFloat(String double_key, float float_value){
    //TODO: make this method
  }
  @Override
  public void putLong(String long_key, long long_value){
    //TODO: make this method
  }
  @Override
  public void putBoolean(String boolean_key, boolean boolean_value){
    //TODO: make this method
  }
  
  
  @Override
  public String getString(String string_key, String default_value){
    //TODO: make this method
    return "";
  }
  @Override
  public int getInt(String int_key, int default_value){
    //TODO: make this method
    return 0;
  }
  @Override
  public float getFloat(String double_key, float default_value){
    //TODO: make this method
    return 0f;
  }
  @Override
  public long getLong(String long_key, long default_value){
    //TODO: make this method
    return 0l;
  }
  @Override
  public boolean getBoolean(String boolean_key, boolean default_value){
    //TODO: make this method
    return false;
  }
}
