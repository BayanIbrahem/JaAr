package com.example.android.studio.ja_ar.fragments;

import android.app.Application;
import android.os.Bundle;

import androidx.preference.EditTextPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import com.example.android.studio.ja_ar.R;
import com.example.android.studio.ja_ar.storage.data_store.SettingsDataStorePreference;

public class SettingsFragment extends PreferenceFragmentCompat{
  @Override
  public void onCreatePreferences(Bundle savedInstanceState, String rootKey){
    setPreferencesFromResource(R.xml.header_preferences, rootKey);
    EditTextPreference signature = findPreference("signature");
    PreferenceManager preferenceManager = getPreferenceManager();
    Application application = getActivity().getApplication();
    SettingsDataStorePreference dataStorePreference = new SettingsDataStorePreference(application);
    preferenceManager.setPreferenceDataStore(dataStorePreference);
  }
  @Override
  public void onPause(){
    super.onPause();
    //TODO: implement method
  }
  @Override
  public void onResume(){
    super.onResume();
    //TODO: implement method
  }
}
