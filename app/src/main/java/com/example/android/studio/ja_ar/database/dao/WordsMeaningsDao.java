package com.example.android.studio.ja_ar.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.android.studio.ja_ar.database.entity.Word;
import com.example.android.studio.ja_ar.database.entity.WordsMeanings;

import java.util.List;

@Dao
public interface WordsMeaningsDao{
  @Insert
  public void insert(WordsMeanings new_word_meaning);
  
  @Update
  public void update(WordsMeanings edited_word_meaning);
  
  @Delete
  public void delete(WordsMeanings deleted_meaning);
  @Query("DELETE FROM words_meanings WHERE word_id = :word_id")
  public void delete_word(long word_id);
  @Query("DELETE FROM words_meanings WHERE lang_id = :lang_id")
  public void delete_lang(long lang_id);
  @Query("DELETE FROM words_meanings WHERE single_meaning = :single_meaning")
  public void delete_meaning(String single_meaning);
  
  @Query("SELECT * FROM words_meanings")
  public List<WordsMeanings> get_all();
  
  @Query("SELECT * FROM words_meanings WHERE word_id IN (:word_id_s) AND lang_id IN (:lang_id_s)")
  public List<WordsMeanings> get_words_meanings_in_langs(long[] word_id_s, long[]lang_id_s);
  
  @Query("SELECT * FROM words_meanings WHERE word_id = :word_id")
  public List<WordsMeanings> get_word_meanings(long word_id);
  
  @Query("SELECT * FROM words_meanings WHERE lang_id = :lang_id")
  public List<WordsMeanings> get_lang_meanings(long lang_id);
  
  @Query("SELECT * FROM words_meanings WHERE word_id BETWEEN :first_word_id AND :last_word_id")
  public List<WordsMeanings> get_words_meanings_in_open_range(long first_word_id, long last_word_id);
  
  @Query("SELECT * FROM words_meanings WHERE lang_id BETWEEN :first_lang_id AND :last_lang_id")
  public List<WordsMeanings> get_langs_meanings_in_open_range(long first_lang_id, long last_lang_id);
}
