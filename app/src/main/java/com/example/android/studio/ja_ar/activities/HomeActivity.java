package com.example.android.studio.ja_ar.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.android.studio.ja_ar.R;
import com.example.android.studio.ja_ar.databinding.ActivityHomeBinding;
import com.example.android.studio.ja_ar.fragments.SettingsFragment;
import com.example.android.studio.ja_ar.storage.data_store.SettingsDataStorePreference;

public class HomeActivity extends AppCompatActivity{
  
  ActivityHomeBinding binding;
  
  @Override
  protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);
    initUi();
    set_button_clickListener();
  }
  
  @Override
  public boolean onCreateOptionsMenu(Menu menu){
    getMenuInflater().inflate(R.menu.home_app_bar, menu);
    return true;
  }
  @Override
  public boolean onOptionsItemSelected(MenuItem item){
    switch(item.getItemId()){
      case R.id.menu_home_settings:
        goToSettings();
    }
    return true;
  }
  
  private void initUi(){
    binding = ActivityHomeBinding.inflate(getLayoutInflater());
    View root = binding.getRoot();
    setContentView(root);
  }
  private void set_button_clickListener(){
    View.OnClickListener buttonLearnClickListener =
        new View.OnClickListener(){
          @Override
          public void onClick(View view){
            Intent intentToLearn = new Intent(HomeActivity.this, LearnActivity.class);
            startActivity(intentToLearn);
          }
        };
    View.OnClickListener buttonSetValueClickListener =
        new View.OnClickListener(){
          @Override
          public void onClick(View view){
            Intent intentToSave = new Intent(HomeActivity.this, SavingActivity.class);
            startActivity(intentToSave);
          }
        };
    binding.homeButtonLearn.setOnClickListener(buttonLearnClickListener);
    binding.homeButtonSaving.setOnClickListener(buttonSetValueClickListener);
  }
  private void goToSettings(){
    Intent settingsIntent = new Intent(this, SettingsActivity.class);
    startActivity(settingsIntent);
  }
}