package com.example.android.studio.ja_ar.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.android.studio.ja_ar.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class LearnActivity extends AppCompatActivity{
  
  private Button button_submit;
  private EditText et_answer;
  private EditText et_time;
  private FloatingActionButton fbtn_stopTest;
  private ImageButton imgbtn_startTest;
  private ImageButton imgbtn_swapLang;
  private Spinner spinner_filter;
  private Spinner spinner_firstLang;
  private Spinner spinner_secondLang;
  private Spinner spinner_timeUnit;
  private TextView tv_counter;
  private TextView tv_filterHint;
  private TextView tv_testWord;
  private TextView tv_timeHint;
  private TextView tv_wordNumber;
  
  @Override
  protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_learn);
    initUi();
  }
  
  private void initUi(){
    button_submit = findViewById(R.id.learn_button_submit);
    et_answer = findViewById(R.id.learn_et_answer);
    et_time = findViewById(R.id.learn_et_time);
    fbtn_stopTest = findViewById(R.id.learn_fbtn_stop);
    imgbtn_startTest = findViewById(R.id.learn_imgbtn_start);
    imgbtn_swapLang = findViewById(R.id.learn_imgbtn_swap);
    spinner_filter = findViewById(R.id.learn_spinner_typeFilter);
    spinner_firstLang = findViewById(R.id.learn_spinner_firstLang);
    spinner_secondLang = findViewById(R.id.learn_spinner_secondLang);
    spinner_timeUnit = findViewById(R.id.learn_spinner_unit);
    tv_counter = findViewById(R.id.learn_tv_counter);
    tv_filterHint = findViewById(R.id.learn_tv_filterHint);
    tv_testWord = findViewById(R.id.learn_tv_testWord);
    tv_timeHint = findViewById(R.id.learn_tv_timeHint);
    tv_wordNumber = findViewById(R.id.learn_tv_wordNumber);
  }
}