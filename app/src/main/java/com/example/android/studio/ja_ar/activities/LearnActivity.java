package com.example.android.studio.ja_ar.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.android.studio.ja_ar.R;
import com.example.android.studio.ja_ar.databinding.ActivityLearnBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class LearnActivity extends AppCompatActivity{
  
  ActivityLearnBinding binding;
  
  @Override
  protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_learn);
    initUi();
  }
  
  private void initUi(){
    binding = ActivityLearnBinding.inflate(getLayoutInflater());
    View root = binding.getRoot();
    setContentView(root);
  }
}