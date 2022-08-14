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
  public void insert_or_replace(Word... words);
  
  @Insert(onConflict = OnConflictStrategy.IGNORE)
  public void insert_or_ignore(Word... words);
  
  //return the number of updated rows.
  @Update
  public void update(Word new_word);
  //TODO: make advanced updates as needed.
  
  //delete the word with the delete_word id
  @Delete
  public void delete(Word deleted_word);
  
  @Query("SELECT * FROM words")
  public List<Word> get_all();
  
  @Query("SELECT * FROM words WHERE word_id BETWEEN :first_id AND :last_id")
  public List<Word> get_between(long first_id, long last_id);
  
  @Query("SELECT * FROM words WHERE favorite")
  public List<Word> get_favorite();
  
}
