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

import com.example.android.studio.ja_ar.R;
import com.example.android.studio.ja_ar.storage.data_store.SettingsDataStorePreference;

public class SettingsFragment extends PreferenceFragmentCompat{
  @Override
  public void onCreatePreferences(Bundle savedInstanceState, String rootKey){
    setPreferencesFromResource(R.xml.header_preferences, rootKey);
    
    PreferenceManager preferenceManager = getPreferenceManager();
    Application application = getActivity().getApplication();
    
    SettingsDataStorePreference dataStorePreference = new SettingsDataStorePreference(application);
    
//    preferenceManager.setPreferenceDataStore(dataStorePreference);
  }
//  @Override
//  public void onPause(){
//    super.onPause();
//    PreferenceDataStore dataStore = getPreferenceManager().getPreferenceDataStore();
//
//    EditTextPreference signature = findPreference("signature");
//    ListPreference reply = findPreference("reply");
//    SwitchPreferenceCompat sync = findPreference("sync");
//    SwitchPreferenceCompat attachment = findPreference("attachment");
//
//    dataStore.putString("signature", signature.getText());
//    dataStore.putString("reply", reply.getValue());
//    dataStore.putBoolean("sync", sync.isChecked());
//    dataStore.putBoolean("attachment", attachment.isChecked());
//  }
//  @Override
//  public void onResume(){
//    super.onResume();
//    PreferenceDataStore dataStore = getPreferenceManager().getPreferenceDataStore();
//
//    EditTextPreference signature = findPreference("signature");
//    ListPreference reply = findPreference("reply");
//    SwitchPreferenceCompat sync = findPreference("sync");
//    SwitchPreferenceCompat attachment = findPreference("attachment");
//
//    signature.setText(dataStore.getString("signature", "no value"));
//    reply.setValue(dataStore.getString("reply", "reply"));
//    sync.setChecked(dataStore.getBoolean("sync", false));
//    attachment.setChecked(dataStore.getBoolean("attachment", false));
//  }
}
