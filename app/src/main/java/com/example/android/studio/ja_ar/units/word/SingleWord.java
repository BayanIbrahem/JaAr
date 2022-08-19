package com.example.android.studio.ja_ar.units.word;

import android.util.Pair;

import androidx.annotation.NonNull;

import com.example.android.studio.ja_ar.enums.lang.Languages;
import com.example.android.studio.ja_ar.helpers.log.PrintLog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * THIS CLASS REPRESENTS A SINGLE WORD
 * EACH WORD HAS MORE THAN ONE MEANING IN EACH LANGUAGE
 * THERE MUST BE TWO LANGUAGES AT LEAST TO CREATE THE WORD.
 * NO MEANING OR DESCRIPTION WILL BE ADDED IF THERE NO LANGUAGE FOR IT.
 * SET ID CAN BE CALLED ONLY ONCE.
 * BUT YOU CAN CALL 'correct_id'.
 * ALSO I ADDED CATEGORIES FOR WORDS.
 * */
public class SingleWord{
  private final String WORD_LOG_TAG;
  private static int instance_created = 0;
  private long id; //unique num.
  private boolean true_id; // true id from database.
  private List<Boolean> languages; //list of languages with key of each one.
  private List<List<String>> meanings; //list of meanings for each language.
  private List<String> types; //Type of word (must be single value for each language)
  private List<String> descriptions; //description for each language
  private int testSuccess; //how many i tested
  private int testFailure; //this word.
  private List<String> categories; //make categories for words to make learning easier.
  private boolean favorite;
  public SingleWord(long id,
                    Pair<Long, Long> languages,
                    Pair<String, String> types,
                    Pair<String, String> categories,
                    Pair<String[], String[]> meanings,
                    Pair<String, String> description
  ){
    
    this.id=id;
    this.WORD_LOG_TAG = "word #" + id;
  
    int first_lang_id = Math.toIntExact(languages.first);
    int second_lang_id = Math.toIntExact(languages.second);
  
    this.languages = new ArrayList<>(2);
    this.languages.set(first_lang_id, true);
    this.languages.set(second_lang_id, true);
  
    this.types = new ArrayList<>(2);
    this.types.set(first_lang_id, types.first);
    this.types.set(second_lang_id, types.second);
  
    this.categories = new ArrayList<>(2);
    this.categories.set(first_lang_id, categories.first);
    this.categories.set(second_lang_id, categories.second);
  
    this.descriptions = new ArrayList<String>(2);
    this.descriptions.set(first_lang_id, description.first.trim().toLowerCase());
    this.descriptions.set(second_lang_id, description.second.trim().toLowerCase());
  
    this.meanings = new ArrayList<List<String>>(2);
    trimmedLowerCase(meanings.first);
    trimmedLowerCase(meanings.second);
    List<String> first_lang_meanings = Arrays.asList(meanings.first);
    List<String> second_lang_meanings = Arrays.asList(meanings.second);
    this.meanings.set(first_lang_id, first_lang_meanings);
    this.meanings.set(second_lang_id, second_lang_meanings);
    
    this.favorite = false;
    
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
  
  public List<Boolean> getLanguages(){
    return languages;
  }
  public boolean hasLanguage(final long lang_id){
    if(isInvalidLangId(lang_id)){
      return false;
    }
    return this.languages.get((int) lang_id);
  }
  public boolean addLanguage(long lang_id){
    if(lang_id < 0){
      return false;
    }
    //this if check if the language is new of not
    this.languages.set((int) lang_id, true);
    return true;
  }
  
  public List<String> getTypes(){
    return types;
  }
  public String getLangType(long lang_id){
    if(isInvalidLangId(lang_id)){
      return null;
    }
    return this.types.get((int) lang_id);
  }
  public boolean addNewTypeMeaning(long lang_id, String type){
    if(isInvalidLangId(lang_id)){
      return false;
    }
    if(this.languages.get((int)lang_id)){
      if(this.types.get((int)lang_id) == null){
        this.types.set((int)lang_id, type);
        return true;
      }
    }
    PrintLog.error(WORD_LOG_TAG, "fail to add type non existed lang");
    return false;
  }
  public boolean addOrReplaceTypeMeaning(long lang_id, String type){
    if(isInvalidLangId(lang_id)){
      return false;
    }
    if(this.languages.get((int)lang_id)){
      this.types.set((int)lang_id, type);
      return true;
    }
    PrintLog.error(WORD_LOG_TAG, "fail to add type non existed lang");
    return false;
  }
  
  public List<String> getCategories(){
    return categories;
  }
  public String getLangCategory(long lang_id){
    if(isInvalidLangId(lang_id)){
      return null;
    }
    return this.categories.get((int) lang_id);
  }
  public boolean addNewCategoryMeaning(long lang_id, String type){
    if(isInvalidLangId(lang_id)){
      return false;
    }
    if(this.languages.get((int)lang_id)){
      if(this.types.get((int)lang_id) == null){
        this.types.set((int)lang_id, type);
        return true;
      }
    }
    PrintLog.error(WORD_LOG_TAG, "fail to add category non existed lang");
    return false;
  }
  public boolean addOrReplaceCategoryMeaning(long lang_id, String type){
    if(isInvalidLangId(lang_id)){
      return false;
    }
    if(this.languages.get((int)lang_id)){
      this.types.set((int)lang_id, type);
      return true;
    }
    PrintLog.error(WORD_LOG_TAG, "fail to add category non existed lang");
    return false;
  }
  
  
  public List<List<String>> getAllMeanings(){
    return meanings;
  }
  public List<String> getWordMeanings(final long lang_id){
    if(isInvalidLangId(lang_id)){
      PrintLog.error(WORD_LOG_TAG, "fail to get meaning non existed lang");
      return null;
    }
    List<String> lang_meanings = meanings.get((int)lang_id);
    return lang_meanings==null?new ArrayList<String>():lang_meanings;
  }
  public boolean addLangMeanings(long lang_id, String... meanings){
    if(!checkLangId(lang_id)){
      PrintLog.error(WORD_LOG_TAG, "fail to add meaning non existed lang");
      return false;
    }
    for(int i=0; i<meanings.length; i++){
      meanings[i] = meanings[i].toLowerCase().trim();
    }
    List<String> lang_meanings = this.meanings.get((int)lang_id);
    if(lang_meanings == null){
      lang_meanings = new ArrayList<>();
    }
    lang_meanings.addAll(Arrays.asList(meanings));
    
    return true;
  }
  
  public List<String> getDescriptions(){
    return descriptions;
  }
  public String getDescription(long lang_id){
    if(isInvalidLangId(lang_id)){
      return null;
    }
    return this.descriptions.get((int)lang_id);
  }
  public boolean addNewDescriptionMeaning(long lang_id, String description){
    if(isInvalidLangId(lang_id)){
      return false;
    }
    if(this.descriptions.get((int)lang_id) != null){
      return false;
    }
    this.descriptions.set((int)lang_id, description);
    return true;
  }
  public boolean addOrReplaceDescriptionMeaning(long lang_id, String description){
    if(isInvalidLangId(lang_id)){
      return false;
    }
    this.descriptions.set((int)lang_id, description);
    return true;
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
  
  //helper methods
  private boolean checkLangId(long lang_id){
    return !(lang_id < 0 || !this.languages.get((int)lang_id));
  }
  private boolean isInvalidLangId(long lang_id){
    if(!checkLangId(lang_id)){
      PrintLog.error("word #"+id, "invalid language");
      return true;
    }
    return false;
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
  
  
  public boolean isFavorite(){
    return favorite;
  }
  
  public void setFavorite(boolean favorite){
    this.favorite = favorite;
  }
  @Override
  public String toString(){
    StringBuilder builder = new StringBuilder();
    builder.append('\n');
    builder.append(WORD_LOG_TAG);
    builder.append('\n');
    builder.append("id: ");
    builder.append(id);
    builder.append('\n');
    builder.append("languages id: ");
    builder.append(this.languages);
    builder.append('\n');
    builder.append("types: ");
    builder.append(this.types);
    builder.append('\n');
    builder.append("categories:");
    builder.append(this.categories);
    builder.append('\n');
    builder.append("meanings: ");
    for(int i=0; i<this.meanings.size(); i++){
      builder.append("lang #");
      builder.append(i);
      builder.append(": ");
      List<String> m = this.meanings.get(i);
      builder.append(m==null?"null meaning":m);
    }
    builder.append('\n');
    builder.append("descriptions: ");
    builder.append(this.descriptions);
    builder.append('\n');
    return builder.toString();
  }
}
