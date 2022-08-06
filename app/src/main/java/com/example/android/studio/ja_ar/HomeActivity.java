package com.example.android.studio.ja_ar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity{
  
  Button home_button_learn;
  Button home_button_setNewValues;
  @Override
  protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);
  }
  private void initUi(){
    home_button_learn = findViewById(R.id.home_button_learn);
    home_button_setNewValues = findViewById(R.id.home_button_setNewValues);
  }
}