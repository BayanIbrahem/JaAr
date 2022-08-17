package com.example.android.studio.ja_ar.database.dao;

import android.util.Pair;

import androidx.room.*;

import com.example.android.studio.ja_ar.database.entity.*;

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
public interface DescriptionDao{
  public static final String WORD_ID_EQUAL = "word_id = :word_id";
  public static final String LANG_ID_EQUAL = "lang_id = :lang_id";
  public static final String UPDATE_DESCRIPTION = "UPDATE descriptions SET descriptions = :new_description WHERE " + WORD_ID_EQUAL + " AND " + LANG_ID_EQUAL;
  public static final String DELETE_ALL = "DELETE FROM descriptions";
  public static final String DELETE_DESCRIPTION = DELETE_ALL + " WHERE " + WORD_ID_EQUAL + " AND " + LANG_ID_EQUAL;
  public static final String DELETE_WORD = DELETE_ALL + " WHERE " + WORD_ID_EQUAL;
  public static final String DELETE_LANG = DELETE_ALL + " WHERE " + LANG_ID_EQUAL;
  public static final String SELECT_ALL = "SELECT * FROM descriptions";
  public static final String SELECT_WORD = SELECT_ALL + " WHERE " + WORD_ID_EQUAL;
  public static final String SELECT_LANG = SELECT_ALL + " WHERE " + LANG_ID_EQUAL;
  public static final String SELECT_DESCRIPTION = "SELECT * FROM descriptions WHERE " + WORD_ID_EQUAL + " AND " + LANG_ID_EQUAL;
  //insert
  @Insert
  public void insert(Descriptions... new_word_features);
  
  @Update
  public void update(Descriptions... edited_word_features);
  @Query(UPDATE_DESCRIPTION)
  public void update(long word_id, long lang_id, String new_description);
  
  @Delete
  public void delete(Descriptions... deleted_word_features);
  @Query(DELETE_ALL)
  public void delete_all();
  @Query(DELETE_DESCRIPTION)
  public void delete(long word_id, long lang_id);
  @Query(DELETE_WORD)
  public void delete_word(long word_id);
  @Query(DELETE_LANG)
  public void delete_lang(long lang_id);
  
  @Query(SELECT_ALL)
  public List<Descriptions> get_all();
  @Query(SELECT_WORD)
  public List<Descriptions> get_word_descriptions(long word_id);
  @Query(SELECT_LANG)
  public List<Descriptions> get_lang_descriptions(long lang_id);
  @Query(SELECT_DESCRIPTION)
  public String get_word_description_in_lang(long word_id, long lang_id);
}
