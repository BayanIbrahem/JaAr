package com.example.android.studio.ja_ar.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Pair;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.android.studio.ja_ar.R;
import com.example.android.studio.ja_ar.enums.lang.Languages;
import com.example.android.studio.ja_ar.recycler_view.adapter.WordListAdapter;
import com.example.android.studio.ja_ar.units.word.Word;

public class SavingActivity extends AppCompatActivity{
  
  private EditText et_word;
  private EditText et_meaning;
  private EditText et_description;
  private TextView tv_spinnerHint;
  private Spinner spinner_type;
  private RecyclerView rv_words;
  
  private WordListAdapter wordsAdapter;
  
  @Override
  protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_saving);
    initUi();
    setRecyclerView();
  }
  
  private void initUi(){
    et_word = findViewById(R.id.save_et_word);
    et_meaning = findViewById(R.id.save_et_meaning);
    et_description = findViewById(R.id.save_et_description);
    tv_spinnerHint = findViewById(R.id.save_tv_spinnerHint);
    spinner_type = findViewById(R.id.save_spinner_type);
    rv_words = findViewById(R.id.save_rv_words);
  }
  private void setRecyclerView(){
    Word dummy_words[] = new Word[10];
    for(int i=0; i<dummy_words.length; i++){
      Pair<Languages, Languages> lang = new Pair<>(Languages.ar, Languages.en);
      String[] main_meaning = new String[10];
      String[] second_meaning = new String[10];
      for(int j = 0; j<10; j++){
        main_meaning[j] = "main meaning #"+i+", "+j;
        second_meaning[j] = "second meaning #"+i+", "+j;
      }
      Pair<String[], String[]> mean = new Pair<>(main_meaning, second_meaning);
      Pair<String, String> desc = new Pair<>("main description #"+i, "second description #"+i);
      dummy_words[i] = Word.getInstance(i, "type #"+i, lang, mean, desc, "category #"+i);
    }
    wordsAdapter = new WordListAdapter(dummy_words);
    LinearLayoutManager rvLayoutManager = new LinearLayoutManager(this);
    rv_words.setLayoutManager(rvLayoutManager);
    rv_words.setHasFixedSize(false);
    rv_words.setAdapter(wordsAdapter);
  }
}