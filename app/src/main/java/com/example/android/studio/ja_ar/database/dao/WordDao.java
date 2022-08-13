package com.example.android.studio.ja_ar.database.dao;

import android.app.SharedElementCallback;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.android.studio.ja_ar.database.entity.Word;

import java.util.List;

@Dao
public interface WordDao{
  
  //return IDs of the inserted words.
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  public long[] insert_or_replace(Word... words);
  
  @Insert(onConflict = OnConflictStrategy.IGNORE)
  public long[] insert_or_ignore(Word... words);
  
  //return the number of updated rows.
  @Update
  public long update(Word new_word);
  //TODO: make advanced updates as needed.
  
  //delete the word with the delete_word id
  @Delete
  public long delete(Word deleted_word);
  
  @Query("SELECT * FROM words")
  public List<Word> get_all();
  
  @Query("SELECT * FROM words WHERE word_id BETWEEN :first_id AND :last_id")
  public List<Word> get_between(long first_id, long last_id);
  
  @Query("SELECT * FROM words WHERE favorite")
  public List<Word> get_favorite();
  
  @Query("SELECT DISTINCT " +
      "word_id, meanings, descriptions, type, " +
      "category, favorite, passed_tests_num, failed_tests_num " +
      "FROM words WHERE lang_id IN (:first_lang, :second_lang)")
  public List<Word> get_in_lang(long first_lang, long second_lang);
  //TODO make other select queries...
  
}
