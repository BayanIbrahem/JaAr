package com.example.android.studio.ja_ar.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.android.studio.ja_ar.database.entity.TestHistory;

import java.util.List;


/**
 * STRINGS NAMES,
 * FIRST EVERY STRING REPRESENTS WHAT IT CONTAINS,
 * IN QUERIES THERE ARE SOME SUB STRINGS DUPLICATED SO I MAKE THEM IN SEPARATED STRING VARIABLE
 * TO KNOW IF THE STRING IS QUERY OR SUB STRING WE SEE THE FIRST WORD
 * IF THE FIRST WORD IS (INSERT, UPDATE, DELETE, SELECT) THEN THE STRING IS A SINGLE QUERY
 * BUT IF THE FIRST WORD IS(PUT, CHANGE, REMOVE, GET) THEN IT IS A SUB STRING USED ONLY FOR ONE SELECTED QUERY TYPE
 * FINALLY FOR OTHER STRING NAMES THIS MEAN THAT IT IS SUB STRING
 * */
@Dao
public interface TestHistoryDao{
  public static final String DELETE_ALL_HISTORIES = "DELETE FROM test_history";
  public static final String DELETE_HISTORY = DELETE_ALL_HISTORIES + " WHERE history_id = :history_id";
  public static final String DELETE_HISTORY_OLDER_THAN = DELETE_ALL_HISTORIES + " WHERE date < :date";
  public static final String DATE_IN_RANGE = "date BETWEEN MIN(:first_date, :last_date) AND MAX(:first_date, :last_date)";
  public static final String DELETE_HISTORY_IN_DATE_RANGE = DELETE_ALL_HISTORIES + " WHERE " + DATE_IN_RANGE;
  public static final String LANG_IN_ROW = ":lang_id IN(first_lang, second_lang)";
  public static final String DELETE_LANG_HISTORY = DELETE_ALL_HISTORIES + " WHERE " + LANG_IN_ROW;
  public static final String DELETE_WORD_HISTORY = DELETE_ALL_HISTORIES + " WHERE word_id = :word_id";
  public static final String SELECT_ALL = "SELECT * FROM test_history";
  public static final String SELECT_HISTORY_FOR_WORD = SELECT_ALL + " WHERE word_id = :word_id";
  public static final String SELECT_HISTORY_FOR_LANG = SELECT_ALL + " WHERE " + LANG_IN_ROW;
  public static final String SELECT_HISTORY_FOR_DATE = SELECT_ALL + " WHERE " + DATE_IN_RANGE;
  public static final String SELECT_SUCCESS_HISTORY = SELECT_ALL + " WHERE succeeded";
  public static final String SELECT_FAILURE_HISTORY = SELECT_ALL + " WHERE NOT succeeded";
  @Insert
  public void insert(TestHistory new_history);
  
  @Delete
  public void delete(TestHistory deleted_history);
  @Query(DELETE_ALL_HISTORIES)
  public void clear_history();
  @Query(DELETE_HISTORY)
  public void delete(long history_id);
  @Query(DELETE_HISTORY_OLDER_THAN)
  public void clear_history_older_than(long date);
  @Query(DELETE_HISTORY_IN_DATE_RANGE)
  public void clear_history_in_date_range(long first_date, long last_date);
  @Query(DELETE_LANG_HISTORY)
  public void clear_history_related_by_lang(long lang_id);
  @Query(DELETE_WORD_HISTORY)
  public void clear_history_for_word(long word_id);
  
  @Query(SELECT_ALL)
  public List<TestHistory> get_all();
  @Query(SELECT_HISTORY_FOR_WORD)
  public List<TestHistory> get_history_for_word(long word_id);
  @Query(SELECT_HISTORY_FOR_LANG)
  public List<TestHistory> get_history_for_lang(long lang_id);
  @Query(SELECT_HISTORY_FOR_DATE)
  public List<TestHistory> get_history_for_date(long first_date, long last_date);
  @Query(SELECT_SUCCESS_HISTORY)
  public List<TestHistory> get_success_history();
  @Query(SELECT_FAILURE_HISTORY)
  public List<TestHistory> get_failed_history();
}
