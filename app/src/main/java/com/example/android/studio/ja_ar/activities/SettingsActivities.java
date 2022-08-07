package com.example.android.studio.ja_ar.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import android.os.Bundle;

import com.example.android.studio.ja_ar.R;
import com.example.android.studio.ja_ar.fragments.SettingsFragment;

public class SettingsActivities extends AppCompatActivity implements
    PreferenceFragmentCompat.OnPreferenceStartFragmentCallback{
  
  
  @Override
  protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_settings);
  }
  
  @Override
  public boolean onPreferenceStartFragment(PreferenceFragmentCompat caller, Preference pref) {
    // Instantiate the new Fragment
    final Bundle args = pref.getExtras();
    final Fragment fragment = getSupportFragmentManager().getFragmentFactory().instantiate(
        getClassLoader(),
        pref.getFragment());
    fragment.setArguments(args);
    fragment.setTargetFragment(caller, 0);
    // Replace the existing Fragment with the new Fragment
    getSupportFragmentManager().beginTransaction()
        .replace(R.id.settings_activity_container, fragment)
        .addToBackStack(null)
        .commit();
    return true;
  }

}