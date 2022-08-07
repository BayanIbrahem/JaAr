package com.example.android.studio.ja_ar.units.word;

import android.util.Pair;

import androidx.annotation.NonNull;

import com.example.android.studio.ja_ar.enums.lang.Languages;
import com.example.android.studio.ja_ar.helpers.log.PrintLog;
import com.example.android.studio.ja_ar.units.word_manager.WordManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.UnaryOperator;

/**
 * THIS CLASS REPRESENTS A SINGLE WORD
 * EACH WORD HAS MORE THAN ONE MEANING IN EACH LANGUAGE
 * THERE MUST BE TWO LANGUAGES AT LEAST TO CREATE THE WORD.
 * NO MEANING OR DESCRIPTION WILL BE ADDED IF THERE NO LANGUAGE FOR IT.
 * SET ID CAN BE CALLED ONLY ONCE.
 * BUT YOU CAN CALL 'correct_id'.
 * ALSO I ADDED CATEGORIES FOR WORDS.
 * */
public class Word{
  private static int instance_created = 0;
  private long id; //unique num.
  private boolean true_id; // true id from database.
  private List<Languages> languages; //list of languages with key of each one.
  private List<List<String>> meanings; //list of meanings for each language.
  private String type; //type of word (must be single value)
  private List<String> descriptions; //description for each language;
  private int testSuccess; //how many i tested
  private int testFailure; //this word.
  private String category; //make categories for words to make learning easier.
  
  public static Word getInstance(long id,
                          String type,
                          Pair<Languages, Languages> languages,
                          Pair<String[], String[]> meanings,
                          Pair<String, String> description,
                          String category){
    boolean is_valid_instance = true;
    is_valid_instance =
            id>=0
            && !isEmpty(type)
            && !isEmpty(meanings.first)
            && !isEmpty(meanings.second)
            && !isEmpty(category)
            && !isEmpty(description.first)
            && !isEmpty(description.second)
            && !Arrays.equals(meanings.second, meanings.first)
            && !isPalindrome(description)
            && !isPalindrome(languages);
    if(is_valid_instance){
      return new Word(id, type, languages, meanings, description, category);
    }
    return null;
  }
  private Word(long id,
               String type,
               Pair<Languages, Languages> languages,
               Pair<String[], String[]> meanings,
               Pair<String, String> description, String category){
    this.id=id;
    this.type = type;
    
    this.languages = new ArrayList<>(2);
    this.languages.add(languages.first);
    this.languages.add(languages.second);
    
    this.meanings = new ArrayList<List<String>>(2);
    trimmedLowerCase(meanings.first);
    trimmedLowerCase(meanings.second);
    List<String> first_lang_meanings = Arrays.asList(meanings.first);
    List<String> second_lang_meanings = Arrays.asList(meanings.second);
    this.meanings.add(first_lang_meanings);
    this.meanings.add(second_lang_meanings);
    this.descriptions = new ArrayList<String>(2);
    this.descriptions.add(description.first.trim().toLowerCase());
    this.descriptions.add(description.second.trim().toLowerCase());
    this.category = category;
    
    clearHistory();
  }
  
  public long getId(){
    return id;
  }
  public void setId(long id){
    if(isTrue_id()){
      return;
    }
    this.id = id;
    this.true_id = true;
  }
  public void correct_id(long id){
    this.id = id;
    this.true_id = true;
  }
  public boolean isTrue_id(){
    return true_id;
  }
  
  public List<Languages> getLanguages(){
    return languages;
  }
  public Languages getLanguage(final long lang_id){
    if(!checkLangId(lang_id)){
      return Languages.nan;
    }
    return languages.get((int)lang_id);
  }
  public void addLanguage(Languages lang_name){
    if(isNewLang(lang_name)){
      int index = (int)lang_name.lang_id;
      if(!hasEnoughSize(this.languages, index)){
        this.languages.add(index, lang_name);
        this.meanings.add(index, new ArrayList<String>());
        this.descriptions.add(index, "no description");
      }
      else{
        this.languages.set(index, lang_name);
        this.meanings.set(index, new ArrayList<String>());
        this.descriptions.set(index, "no description");
      }
    }
  }
  
  public List<List<String>> getMeanings(){
    return meanings;
  }
  public List<String> getMeaning(final long lang_id){
    if(!checkLangId(lang_id)){
      return new ArrayList<String>();
    }
    return meanings.get((int)lang_id);
  }
  public void addMeaning(String meaning, long lang_id){
    if(!checkLangId(lang_id)){
      PrintLog.error("word #"+id, "fail to add meaning (invalid language)");
      return;
    }
    meaning = meaning.toLowerCase().trim();
    this.meanings.get((int)lang_id).add(meaning);
  }
  
  public String getType(){
    return type;
  }
  public void setType(String type){
    this.type = type;
  }
  
  public List<String> getDescriptions(){
    return descriptions;
  }
  public String getDescription(long lang_id){
    if(!checkLangId(lang_id)){
      return "nan_description";
    }
    return this.descriptions.get((int)lang_id);
  }
  public void addDescription(long lang_id, String description){
    if(!checkLangId(lang_id)){
      PrintLog.error("word #"+id, "fail to add meaning (invalid language)");
      return;
    }
    this.descriptions.add(description);
  }
  
  public int getTestSuccess(){
    return testSuccess;
  }
  public int getTestFailure(){
    return testFailure;
  }
  public void addTestResult(boolean success){
    if(success){
      this.testSuccess++;
    }
    else{
      this.testFailure++;
    }
  }
  public void clearHistory(){
    this.testSuccess = this.testFailure = 0;
  }
  
  public String getCategory(){
    return category;
  }
  public void setCategory(String category){
    this.category = category;
  }
  
  public String getDefaultLanguageName(){
    int index = this.languages.indexOf(WordManager.first_lang);
    if(index < 0){
      return this.meanings.get(0).get(0);
    }
    else{
      return this.meanings.get(index).get(0);
    }
  }
  public String getSecondLanguageName(){
    int index = this.languages.indexOf(WordManager.second_lang);
    if(index < 0){
      return String.join(", ", this.meanings.get(1));
    }
    else{//TODO make the delimiter different for each lang.
      return String.join(", ", this.meanings.get(index));
    }
  }
  public String getDefaultDescription(){
    int index = this.languages.indexOf(WordManager.first_lang);
    if(index < 0){
      return this.descriptions.get(0);
    }
    else{
      return this.descriptions.get(index);
    }
  }
  //helper methods
  private boolean checkLangId(long lang_id){
    return !(lang_id < 0 || lang_id >= languages.size());
  }
  private void trimmedLowerCase(@NonNull String[] array){
    for(int i=0; i<array.length; i++){
      array[i] = array[i].toLowerCase().trim();
    }
  }
  private boolean isEmpty(@NonNull List<String> list){
    return list.size()==0;
  }
  private static boolean isEmpty(@NonNull String[] array){
    return array.length==0;
  }
  private static boolean isEmpty(@NonNull String s){
    return s.trim().toLowerCase().equals("");
  }
  private static boolean isPalindrome(@NonNull Pair<?, ?> pair){
    return pair.first.equals(pair.second);
  }
  private boolean isNewLang(Languages lang){
    if(languages.indexOf(lang)>0){
      return false;
    }
    return true;
  }
  private boolean hasEnoughSize(@NonNull List<?> list, int required_size){
      return list.size() > required_size;
  }
  
  

}
