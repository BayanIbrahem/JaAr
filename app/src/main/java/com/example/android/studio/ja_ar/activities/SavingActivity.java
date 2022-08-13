package com.example.android.studio.ja_ar.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Pair;
import android.view.View;

import com.example.android.studio.ja_ar.R;
import com.example.android.studio.ja_ar.databinding.ActivitySavingBinding;
import com.example.android.studio.ja_ar.enums.lang.Languages;
import com.example.android.studio.ja_ar.recycler_view.adapter.WordListAdapter;
import com.example.android.studio.ja_ar.units.word.SingleWord;

public class SavingActivity extends AppCompatActivity{
  
  ActivitySavingBinding binding;
  
  private WordListAdapter wordsAdapter;
  
  @Override
  protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_saving);
    initUi();
    setRecyclerView();
  }
  
  private void initUi(){
    binding = ActivitySavingBinding.inflate(getLayoutInflater());
    View root = binding.getRoot();
    setContentView(root);
  }
  private void setRecyclerView(){
    SingleWord dummy_Single_words[] = new SingleWord[10];
    for(int i = 0; i< dummy_Single_words.length; i++){
      Pair<Languages, Languages> lang = new Pair<>(Languages.ar, Languages.en);
      String[] main_meaning = new String[10];
      String[] second_meaning = new String[10];
      for(int j = 0; j<10; j++){
        main_meaning[j] = "main meaning #"+i+", "+j;
        second_meaning[j] = "second meaning #"+i+", "+j;
      }
      Pair<String[], String[]> mean = new Pair<>(main_meaning, second_meaning);
      Pair<String, String> desc = new Pair<>("main description #"+i, "second description #"+i);
      dummy_Single_words[i] = SingleWord.getInstance(i, "Type #"+i, lang, mean, desc, "category #"+i);
    }
    wordsAdapter = new WordListAdapter(dummy_Single_words);
    LinearLayoutManager rvLayoutManager = new LinearLayoutManager(this);
    binding.saveRvWords.setLayoutManager(rvLayoutManager);
    binding.saveRvWords.setHasFixedSize(false);
    binding.saveRvWords.setAdapter(wordsAdapter);
  }
}