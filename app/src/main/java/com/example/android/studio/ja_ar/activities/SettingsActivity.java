package com.example.android.studio.ja_ar.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import android.os.Bundle;

import com.example.android.studio.ja_ar.R;
import com.example.android.studio.ja_ar.fragments.SettingsFragment;

public class SettingsActivity extends AppCompatActivity implements
    PreferenceFragmentCompat.OnPreferenceStartFragmentCallback{
  @Override
  protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_settings);
    if (savedInstanceState == null) {
      getSupportFragmentManager().beginTransaction()
          .setReorderingAllowed(true)
          .add(R.id.settings_activity_container, SettingsFragment.class, null)
          .commit();
    }
  }
  @Override
  public boolean onPreferenceStartFragment(PreferenceFragmentCompat caller, Preference pref) {
    // Instantiate the new Fragment
    final Bundle args = pref.getExtras();
    final Fragment fragment = getSupportFragmentManager().getFragmentFactory().instantiate(
        getClassLoader(),
        pref.getFragment());
    fragment.setArguments(args);
    // Replace the existing Fragment with the new Fragment
    getSupportFragmentManager().beginTransaction()
        .replace(R.id.settings_activity_container, fragment)
        .addToBackStack(null)
        .commit();
    return true;
  }
}