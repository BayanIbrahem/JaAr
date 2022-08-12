package com.example.android.studio.ja_ar.fragments;

import android.app.Application;
import android.os.Bundle;

import androidx.preference.EditTextPreference;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceDataStore;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.preference.SwitchPreference;
import androidx.preference.SwitchPreferenceCompat;
import androidx.vectordrawable.graphics.drawable.AnimationUtilsCompat;

import com.example.android.studio.ja_ar.R;
import com.example.android.studio.ja_ar.storage.data_store.SettingsDataStorePreference;

public class SettingsFragment extends PreferenceFragmentCompat{
 private static final String SIGNATURE_TAG = "signature";
 //final String REPLY_TAG = "reply";
 private static final String SYNC_TAG = "sync";
 private static final String ATTACHMENT_TAG = "attachment";
 private static final String SUBSCRIBE_TAG = "subscribe";
 private static final String AUTO_SUBSCRIBE_TAG = "auto_subscribe";
 
 private EditTextPreference signature;
 //ListPreference reply = findPreference(REPLY_TAG);
 private SwitchPreferenceCompat sync;
 private SwitchPreferenceCompat attachment;
 private SwitchPreferenceCompat subscribe;
 private SwitchPreferenceCompat auto_subscribe;
 
  @Override
  public void onCreatePreferences(Bundle savedInstanceState, String rootKey){
    setPreferencesFromResource(R.xml.header_preferences, rootKey);
    /*-------------------------------------------------------*/
    PreferenceManager preferenceManager = getPreferenceManager();
    Application application = getActivity().getApplication();
    
    SettingsDataStorePreference dataStorePreference = new SettingsDataStorePreference(application);
    
    preferenceManager.setPreferenceDataStore(dataStorePreference);
    
    SwitchPreferenceCompat sync = findPreference("sync");
    //TODO: data_store is saving by it self but not writing yet.
    initPreferences();
    loadPreferences();
  }
  
//  @Override
//  public void onPause(){ loadPreferences(); super.onPause(); }
//  @Override
//  public void onResume(){
//    if(signature == null || sync == null || attachment == null || subscribe == null || auto_subscribe == null){
//      initPreferences();
//    }
//    savePreferences();
//    super.onResume();
//  }
  
  private void initPreferences(){
    signature = findPreference(SIGNATURE_TAG);
    sync = findPreference(SYNC_TAG);
    attachment = findPreference(ATTACHMENT_TAG);
    subscribe = findPreference(SUBSCRIBE_TAG);
    auto_subscribe = findPreference(AUTO_SUBSCRIBE_TAG);
  }
  
  private void loadPreferences(){
    //instance is SettingsDataStorePreference
    PreferenceDataStore dataStorePreference = getPreferenceManager().getPreferenceDataStore();
    
    final String SIGNATURE_VALUE = dataStorePreference.getString(SIGNATURE_TAG, "no signature");
    //final int REPLY_VALUE;
    final boolean SYNC_VALUE = dataStorePreference.getBoolean(SYNC_TAG, true);
    final boolean ATTACHMENT_VALUE = dataStorePreference.getBoolean(ATTACHMENT_TAG, false);
    final boolean SUBSCRIBE_VALUE = dataStorePreference.getBoolean(SUBSCRIBE_TAG, true);
    final boolean AUTO_SUBSCRIBE_VALUE = dataStorePreference.getBoolean(AUTO_SUBSCRIBE_TAG, false);
    
    signature.setText(SIGNATURE_VALUE);
    sync.setChecked(SYNC_VALUE);
    attachment.setChecked(ATTACHMENT_VALUE);
    subscribe.setChecked(SUBSCRIBE_VALUE);
    auto_subscribe.setChecked(AUTO_SUBSCRIBE_VALUE);
  }
  private void savePreferences(){
    //instance is SettingsDataStorePreference
    PreferenceDataStore dataStorePreference = getPreferenceManager().getPreferenceDataStore();
  
    final String SIGNATURE_VALUE = signature.getText();
    //final int REPLY_VALUE =
    final boolean SYNC_VALUE = sync.isChecked();
    final boolean ATTACHMENT_VALUE = attachment.isChecked();
    final boolean SUBSCRIBE_VALUE = subscribe.isChecked();
    final boolean AUTO_SUBSCRIBE_VALUE = auto_subscribe.isChecked();
  
    dataStorePreference.putString(SIGNATURE_TAG, SIGNATURE_VALUE!=null? SIGNATURE_VALUE:"null signature");
    dataStorePreference.putBoolean(SYNC_TAG, SYNC_VALUE);
    dataStorePreference.putBoolean(ATTACHMENT_TAG, ATTACHMENT_VALUE);
    dataStorePreference.putBoolean(SUBSCRIBE_TAG, SUBSCRIBE_VALUE);
    dataStorePreference.putBoolean(AUTO_SUBSCRIBE_TAG, AUTO_SUBSCRIBE_VALUE);
  }
}
