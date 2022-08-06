package com.example.android.studio.ja_ar.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.android.studio.ja_ar.R;

public class HomeActivity extends AppCompatActivity{
  
  Button button_learn;
  Button button_saving;
  @Override
  protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);
    initUi();
    set_button_clickListener();
  }
  private void initUi(){
    button_learn = findViewById(R.id.home_button_learn);
    button_saving = findViewById(R.id.home_button_saving);
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
    button_learn.setOnClickListener(buttonLearnClickListener);    
    button_saving.setOnClickListener(buttonSetValueClickListener);
  }
}