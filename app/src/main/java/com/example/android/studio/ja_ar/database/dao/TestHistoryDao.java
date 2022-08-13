package com.example.android.studio.ja_ar.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.android.studio.ja_ar.activities.HomeActivity;
import com.example.android.studio.ja_ar.database.entity.TestHistory;

import java.util.List;

@Dao
public interface TestHistoryDao{
  @Insert
  public void insert(TestHistory new_history);
  
  @Delete
  public void delete(TestHistory deleted_history);
  @Query("DELETE FROM test_history")
  public void clear_history();
  
  @Query("SELECT * FROM test_history")
  public List<TestHistory> get_history();
  @Query("SELECT * FROM test_history WHERE word = :word_id")
  public List<TestHistory> get_history_of_word(long word_id);
  @Query("SELECT * FROM test_history WHERE first_lang = :language_id OR second_lang = :language_id")
  public List<TestHistory> get_history_of_lang(long language_id);
  
}
