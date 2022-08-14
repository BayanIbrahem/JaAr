package com.example.android.studio.ja_ar.database.database_manager;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.example.android.studio.ja_ar.database.dao.*;
import com.example.android.studio.ja_ar.database.database.LocalDatabase;
import com.example.android.studio.ja_ar.database.entity.*;

import java.util.List;

/**
 * singleton type
 * */
public class LocalDataBaseManager{
  public static final String DATABASE_NAME = "words_db";
  
  private static LocalDataBaseManager instance;
  
  private LocalDatabase dataBase;
  
  private CategoryDao categoryDao;
  private TestHistoryDao testHistoryDao;
  private WordDao wordDao;
  private WordsMeaningsDao wordsMeaningsDao;
  
  private LocalDataBaseManager(
      LocalDatabase dataBase,
      CategoryDao categoryDao,
      TestHistoryDao testHistoryDao,
      WordDao wordDao,
      WordsMeaningsDao wordsMeaningsDao
  ){
    this.dataBase = dataBase;
    this.categoryDao = categoryDao;
    this.testHistoryDao = testHistoryDao;
    this.wordDao = wordDao;
    this.wordsMeaningsDao = wordsMeaningsDao;
  }
  public static LocalDataBaseManager getInstance(Application application){
    if(instance == null){
      Context context = application.getApplicationContext();
      
      LocalDatabase database = Room.databaseBuilder(context, LocalDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
      
      CategoryDao categoryDao = database.getCategoryDao();
      TestHistoryDao testHistoryDao = database.getTestHistoryDao();
      WordDao wordDao = database.getWordDao();
      WordsMeaningsDao wordsMeaningsDao = database.getWordsMeaningsDao();
      
      instance = new LocalDataBaseManager(database, categoryDao, testHistoryDao, wordDao, wordsMeaningsDao);
    }
    return instance;
  }
  //todo: implement all methods.
  
  
  /** CATEGORIES **/
  /** TEST HISTORY **/
  /**
   * WORDS
   **/
  //return IDs of the inserted words.
  public void insert_or_replace_word(Word... words){
    this.wordDao.insert_or_replace(words);
  }
  
  //return the number of updated rows.
  public void update_word(Word new_word){
    this.wordDao.update(new_word);
  }
  //TODO: make advanced updates as needed.
  
  //delete the word with the delete_word id
  public void delete_word(Word deleted_word){
    this.wordDao.delete(deleted_word);
  }
  
  public List<Word> get_all_words(){
    return this.wordDao.get_all();
  }
  
  /** WORDS MEANINGS **/
  public void insert_word_meaning(WordsMeanings new_word_meaning){
    this.wordsMeaningsDao.insert(new_word_meaning);
  }
  
  public void update_word_meaning(WordsMeanings edited_word_meaning){
    this.wordsMeaningsDao.update(edited_word_meaning);
  }
  
  public void delete_word_meanings(WordsMeanings deleted_meaning){
    this.wordsMeaningsDao.delete(deleted_meaning);
  }
  
  public void delete_word_meanings(long word_id){
    this.wordsMeaningsDao.delete_word(word_id);
  }
  
  public void delete_lang_meanings(long lang_id){
    this.wordsMeaningsDao.delete_lang(lang_id);
  }
  
  public void delete_single_meaning(String single_meaning){
    this.wordsMeaningsDao.delete_meaning(single_meaning);
  }
  
  public List<WordsMeanings> get_all(){
    return this.wordsMeaningsDao.get_all();
  }
  
  public List<WordsMeanings> get_words_meanings_in_langs(long[] word_id_s, long[] lang_id_s){
    return this.wordsMeaningsDao.get_words_meanings_in_langs(word_id_s, lang_id_s);
  }
  
  public List<WordsMeanings> get_word_meanings(long word_id){
    return this.wordsMeaningsDao.get_word_meanings(word_id);
  }
  
  public List<WordsMeanings> get_lang_meanings(long lang_id){
    return this.wordsMeaningsDao.get_lang_meanings(lang_id);
  }
  
  public List<WordsMeanings> get_words_meanings_in_open_range(long first_word_id, long last_word_id){
    return this.wordsMeaningsDao.get_words_meanings_in_open_range(first_word_id, last_word_id);
  }
  
  public List<WordsMeanings> get_langs_meanings_in_open_range(long first_lang_id, long last_lang_id){
    return this.wordsMeaningsDao.get_langs_meanings_in_open_range(first_lang_id, last_lang_id);
  }
}
