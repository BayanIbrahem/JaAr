package com.example.android.studio.ja_ar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class SavingActivity extends AppCompatActivity{
  
  private EditText et_word;
  private EditText et_meaning;
  private EditText et_description;
  private TextView tv_spinnerHint;
  private Spinner spinner_type;
  private RecyclerView rv_words;
  
  @Override
  protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_saving);
    initUi();
  }
  
  private void initUi(){
    et_word = findViewById(R.id.save_et_word);
    et_meaning = findViewById(R.id.save_et_meaning);
    et_description = findViewById(R.id.save_et_description);
    tv_spinnerHint = findViewById(R.id.save_tv_spinnerHint);
    spinner_type = findViewById(R.id.save_spinner_type);
    rv_words = findViewById(R.id.save_rv_words);
  }
}