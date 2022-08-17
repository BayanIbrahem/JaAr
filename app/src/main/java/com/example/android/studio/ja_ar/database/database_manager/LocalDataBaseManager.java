package com.example.android.studio.ja_ar.database.database_manager;

import android.app.Application;
import android.content.Context;

import androidx.room.Query;
import androidx.room.Room;

import com.example.android.studio.ja_ar.database.dao.*;
import com.example.android.studio.ja_ar.database.database.LocalDatabase;
import com.example.android.studio.ja_ar.database.entity.*;
import com.example.android.studio.ja_ar.threads.ThreadManager;

import java.util.List;

/**
 * STRINGS NAMES FOR Dao Interfaces:
 * FIRST EVERY STRING REPRESENTS WHAT IT CONTAINS,
 * IN QUERIES THERE ARE SOME SUB STRINGS DUPLICATED SO I MAKE THEM IN SEPARATED STRING VARIABLE
 * TO KNOW IF THE STRING IS QUERY OR SUB STRING WE SEE THE FIRST WORD
 * IF THE FIRST WORD IS (INSERT, UPDATE, DELETE, SELECT) THEN THE STRING IS A SINGLE QUERY
 * BUT IF THE FIRST WORD IS(PUT, CHANGE, REMOVE, GET) THEN IT IS A SUB STRING USED ONLY FOR ONE SELECTED QUERY TYPE
 * FINALLY FOR OTHER STRING NAMES THIS MEAN THAT IT IS SUB STRING
 * */
public class LocalDataBaseManager{
  public static final String DATABASE_NAME = "words_db";
  
  private static LocalDatabase database;
  
  private final DescriptionsDao descriptionsDao;
  private final TestHistoryDao testHistoryDao;
  private final WordDao wordDao;
  private final WordsMeaningsDao wordsMeaningsDao;
  
  public LocalDataBaseManager(Application application){
    Context context = application.getApplicationContext();
  
    if(database == null){
      database = Room.databaseBuilder(context, LocalDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
    }
    
    this.descriptionsDao = database.getDescriptionsDao();
    this.testHistoryDao = database.getTestHistoryDao();
    this.wordDao = database.getWordDao();
    this.wordsMeaningsDao = database.getWordsMeaningsDao();
  }
  
  /**
   * DESCRIPTIONS
   **/
  
  //descriptions insert:
  public void insert_description(Descriptions... new_word_descriptions){
    ThreadManager.taskInThread(() -> this.descriptionsDao.insert(new_word_descriptions));
    
  }
  
  //descriptions update:
  public void update_descriptions(Descriptions... edited_word_descriptions){
    ThreadManager.taskInThread(() -> this.descriptionsDao.update(edited_word_descriptions));
  }
  public void update_description(long word_id, long lang_id, String new_description){
    ThreadManager.taskInThread(() -> this.descriptionsDao.update(word_id, lang_id, new_description));
  }
  
  //descriptions delete:
  public void delete_descriptions(Descriptions... deleted_word_descriptions){
    ThreadManager.taskInThread(() -> this.descriptionsDao.delete(deleted_word_descriptions));
  }
  public void delete_all_descriptions(){
    ThreadManager.taskInThread(() -> this.descriptionsDao.delete_all());
  }
  public void delete_description(long word_id, long lang_id){
    ThreadManager.taskInThread(() -> this.descriptionsDao.delete(word_id, lang_id));
  }
  public void delete_word_descriptions(long word_id){
    ThreadManager.taskInThread(() -> this.descriptionsDao.delete_word(word_id));
  }
  public void delete_lang_descriptions(long lang_id){
    ThreadManager.taskInThread(() -> this.descriptionsDao.delete_lang(lang_id));
  }
  
  //descriptions retrieve:
  public List<Descriptions> get_all_descriptions(){
    return this.descriptionsDao.get_all();
  }
  public List<Descriptions> get_word_descriptions(long word_id){
    return this.descriptionsDao.get_word_descriptions(word_id);
  }
  public List<Descriptions> get_lang_descriptions(long lang_id){
    return this.descriptionsDao.get_lang_descriptions(lang_id);
  }
  public String get_word_description_in_lang(long word_id, long lang_id){
    return this.descriptionsDao.get_word_description_in_lang(word_id, lang_id);
  }
  
  /**
   * TEST HISTORY
   **/
  //test history insert:
  public void insert_history(TestHistory new_history){
    ThreadManager.taskInThread(() -> this.testHistoryDao.insert(new_history));
  }
  
  //test history update:
  
  //test history delete:
  //i used clear to represent that more than one row will be deleted.
  public void delete_history(TestHistory deleted_history){
    ThreadManager.taskInThread(() -> this.testHistoryDao.delete(deleted_history));
  }
  public void clear_history(){
    ThreadManager.taskInThread(() -> this.testHistoryDao.clear_history());
  }
  public void delete_history(long history_id){
    ThreadManager.taskInThread(() -> this.testHistoryDao.delete(history_id));
  }
  public void clear_history_older_than(long date){
    ThreadManager.taskInThread(() -> this.testHistoryDao.clear_history_older_than(date));
  }
  public void clear_history_in_date_range(long first_date, long last_date){
    ThreadManager.taskInThread(() -> this.testHistoryDao.clear_history_in_date_range(first_date, last_date));
  }
  public void clear_history_related_by_lang(long lang_id){
    ThreadManager.taskInThread(() -> this.testHistoryDao.clear_history_related_by_lang(lang_id));
  }
  public void clear_history_for_word(long word_id){
    ThreadManager.taskInThread(() -> this.testHistoryDao.clear_history_for_word(word_id));
  }
  
  //test history retrieve:
  public List<TestHistory> get_all_history(){
    return this.testHistoryDao.get_all();
  }
  public List<TestHistory> get_history_for_word(long word_id){
    return this.testHistoryDao.get_history_for_word(word_id);
  }
  public List<TestHistory> get_history_for_lang(long lang_id){
    return this.testHistoryDao.get_history_for_lang(lang_id);
  }
  public List<TestHistory> get_history_for_date(long first_date, long last_date){
    return this.testHistoryDao.get_history_for_date(first_date, last_date);
  }
  public List<TestHistory> get_success_history(){
    return this.testHistoryDao.get_success_history();
  }
  public List<TestHistory> get_failed_history(){
    return this.testHistoryDao.get_failed_history();
  }
  
  /**
   * WORDS
   **/
  //words insert:
  public void insert_or_replace_word(Word... words){
    ThreadManager.taskInThread(() -> this.wordDao.insert_or_replace(words));
  }
  public void insert_or_ignore_word(Word... words){
    ThreadManager.taskInThread(() -> this.wordDao.insert_or_ignore(words));
  }
  public void insert_new_type(long type_id, long category_id, boolean is_favorite){
    ThreadManager.taskInThread(() -> this.wordDao.insert_type(type_id, category_id, is_favorite));
  }
  public void insert_new_category(long type_id, long category_id, boolean is_favorite){
    ThreadManager.taskInThread(() -> this.wordDao.insert_category(type_id, category_id, is_favorite));
  }
  
  //words update:
  public void update_word(Word new_word){
    ThreadManager.taskInThread(() -> this.wordDao.update_word(new_word));
  }
  public void set_word_favorite(long word_id, boolean is_favorite){
    ThreadManager.taskInThread(() -> this.wordDao.set_word_favorite(word_id, is_favorite));
  }
  public void add_passed_test_try(long word_id){
    ThreadManager.taskInThread(() -> this.wordDao.add_passed_test_try(word_id));
  }
  public void add_failed_test_try(long word_id){
    ThreadManager.taskInThread(() -> this.wordDao.add_failed_test_try(word_id));
  }
  
  //words delete:
  public void delete_word(Word deleted_word){
    ThreadManager.taskInThread(() -> this.wordDao.delete(deleted_word));
  }
  //this method to make sure that we don't delete a type or a category
  public void delete_word(long word_id){
    ThreadManager.taskInThread(() -> this.wordDao.delete_word(word_id));
  }
  //this method to make sure that we don't delete a word or a category
  public void delete_type(long type_id){
    ThreadManager.taskInThread(() -> this.wordDao.delete_type(type_id));
  }
  //this method to make sure that we don't delete a type or a word
  public void delete_category(long category_id){
    ThreadManager.taskInThread(() -> this.wordDao.delete_category(category_id));
  }
  
  //words retrieve:
  public List<Word> get_all_words(){
    return this.wordDao.get_all();
  }
  public List<Word> get_words_between(long first_id, long last_id){
    return this.wordDao.get_between(first_id, last_id);
  }
  public List<Word> get_favorite_words(){
    return this.wordDao.get_favorite();
  }
  public List<Long> get_type_id_s(){
    return this.wordDao.get_type_id_s();
  }
  public List<Long> get_category_id_s(){
    return this.wordDao.get_category_id_s();
  }
  public boolean is_type(long word_id){
    return this.wordDao.is_type(word_id);
  }
  public boolean is_category(long word_id){
    return this.wordDao.is_category(word_id);
  }
  public boolean is_word(long word_id){
    return this.wordDao.is_word(word_id);
  }
  public int get_passed_tests_num_for_word(long word_id){
    return this.wordDao.get_passed_tests_num_for_word(word_id);
  }
  public int get_failed_tests_num_for_word(long word_id){
    return this.wordDao.get_failed_tests_num_for_word(word_id);
  }
  public int get_all_tests_num_for_word(long word_id){
    return this.wordDao.get_all_tests_num_for_word(word_id);
  }
  public int get_passed_tests_sum(){
    return this.wordDao.get_passed_tests_sum();
  }
  public int get_failed_tests_sum(){
    return this.wordDao.get_failed_tests_sum();
  }
  public int get_all_tests_sum(){
    return this.wordDao.get_all_tests_sum();
  }
  
  /** WORDS MEANINGS **/
  //word meanings insert:
  public void insert_word_meaning(WordsMeanings new_word_meaning){
    ThreadManager.taskInThread(() -> this.wordsMeaningsDao.insert_word(new_word_meaning));
  }
  public void insert_type_meaning(long word_id, long lang_id, String type_name){
    ThreadManager.taskInThread(() -> this.wordsMeaningsDao.insert_type(word_id, lang_id, type_name));
  }
  public void insert_category_meaning(long word_id, long lang_id, String category_name){
    ThreadManager.taskInThread(() -> this.wordsMeaningsDao.insert_category(word_id, lang_id, category_name));
  }
  
  //word meanings update:
  public void update_word_meaning(WordsMeanings edited_word_meaning){
    ThreadManager.taskInThread(() -> this.wordsMeaningsDao.update(edited_word_meaning));
  }
  //do nothing if the word is not type
  public void update_type_meaning(long word_id, long lang_id, String type_name){
    ThreadManager.taskInThread(() -> this.wordsMeaningsDao.update_type(word_id, lang_id, type_name));
  }
  //do nothing if the word is not category
  public void update_category_meaning(long word_id, long lang_id, String category_name){
    ThreadManager.taskInThread(() -> this.wordsMeaningsDao.update_category(word_id, lang_id, category_name));
  }
  
  //word meanings delete:
  public void delete_word_meanings(WordsMeanings deleted_meaning){
    ThreadManager.taskInThread(() -> this.wordsMeaningsDao.delete(deleted_meaning));
  }
  public void delete_word_meanings(long word_id){
    ThreadManager.taskInThread(() -> this.wordsMeaningsDao.delete_word(word_id));
  }
  public void delete_lang_meanings(long lang_id){
    ThreadManager.taskInThread(() -> this.wordsMeaningsDao.delete_lang(lang_id));
  }
  public void delete_single_meaning(String single_meaning){
    ThreadManager.taskInThread(() -> this.wordsMeaningsDao.delete_meaning(single_meaning));
  }
  
  //word meanings retrieve:
  public List<WordsMeanings> get_all_meanings(){
    return this.wordsMeaningsDao.get_all();
  }
  public List<WordsMeanings> get_lang_meanings(long lang_id){
    return this.wordsMeaningsDao.get_lang_meanings(lang_id);
  }
  public List<WordsMeanings> get_words_meanings_in_langs(long[] word_id_s, long[] lang_id_s){
    return this.wordsMeaningsDao.get_words_meanings_in_langs(word_id_s, lang_id_s);
  }
  public List<WordsMeanings> get_meanings_in_langs(long[] lang_id_s){
    return this.wordsMeaningsDao.get_meanings_in_langs(lang_id_s);
  }
  public List<WordsMeanings> get_word_meanings(long word_id){
    return this.wordsMeaningsDao.get_word_meanings(word_id);
  }
  public List<WordsMeanings> get_words_meanings_in_open_range(long first_word_id, long last_word_id){
    return this.wordsMeaningsDao.get_words_meanings_in_open_range(first_word_id, last_word_id);
  }
  public List<WordsMeanings> get_words_meanings_in_closed_range(long first_word_id, long last_word_id){
    long f = Math.min(first_word_id, last_word_id) - 1;
    long l = Math.max(first_word_id, last_word_id) + 1;
    return this.wordsMeaningsDao.get_words_meanings_in_open_range(f, l);
  }
  public List<WordsMeanings> get_langs_meanings_in_open_range(long first_lang_id, long last_lang_id){
    return this.wordsMeaningsDao.get_langs_meanings_in_open_range(first_lang_id, last_lang_id);
  }
  public List<WordsMeanings> get_langs_meanings_in_closed_range(long first_lang_id, long last_lang_id){
    long f = Math.min(first_lang_id, last_lang_id) - 1;
    long l = Math.max(first_lang_id, last_lang_id) + 1;
    return this.wordsMeaningsDao.get_langs_meanings_in_open_range(f, l);
  }
  public List<String> get_types_meanings(){
    return this.wordsMeaningsDao.get_types();
  }
  public List<WordsMeanings> get_categories_categories(){
    return this.wordsMeaningsDao.get_categories();
  }
  public String get_type_in_lang_meaning(long word_id, long lang_id){
    return this.wordsMeaningsDao.get_type_in_lang(word_id, lang_id);
  }
  public WordsMeanings get_category_in_lang_meaning(long word_id, long lang_id){
    return this.wordsMeaningsDao.get_category_in_lang(word_id, lang_id);
  }
  public List<String> get_word_types_meanings(long word_id){
    return this.wordsMeaningsDao.get_word_types(word_id);
  }
  public List<WordsMeanings> get_word_categories_meanings(long word_id){
    return this.wordsMeaningsDao.get_word_categories(word_id);
  }
  public List<String> get_lang_types_meanings(long lang_id){
    return this.wordsMeaningsDao.get_lang_types(lang_id);
  }
  public List<WordsMeanings> get_lang_categories_meanings(long lang_id){
    return wordsMeaningsDao.get_lang_categories(lang_id);
  }
  
}
