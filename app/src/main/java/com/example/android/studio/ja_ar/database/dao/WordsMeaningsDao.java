package com.example.android.studio.ja_ar.database.dao;

import androidx.room.*;

import com.example.android.studio.ja_ar.database.entity.WordsMeanings;

import java.util.List;

/**
 * STRINGS NAMES:
 * FIRST EVERY STRING REPRESENTS WHAT IT CONTAINS,
 * IN QUERIES THERE ARE SOME SUB STRINGS DUPLICATED SO I MAKE THEM IN SEPARATED STRING VARIABLE
 * TO KNOW IF THE STRING IS QUERY OR SUB STRING WE SEE THE FIRST WORD
 * IF THE FIRST WORD IS (INSERT, UPDATE, DELETE, SELECT) THEN THE STRING IS A SINGLE QUERY
 * BUT IF THE FIRST WORD IS(PUT, CHANGE, REMOVE, GET) THEN IT IS A SUB STRING USED ONLY FOR ONE SELECTED QUERY TYPE
 * FINALLY FOR OTHER STRING NAMES THIS MEAN THAT IT IS SUB STRING
 * */
@Dao
public interface WordsMeaningsDao{
  public static final String PUT_WORD_MEANING_1 = "INSERT INTO words_meanings (word_id, lang_id, single_meaning, usage_count";
  public static final String INSERT_TYPE = PUT_WORD_MEANING_1 + ", is_type) VALUES (:word_id, :lang_id, :type_name, null, \"true\")";
  public static final String INSERT_CATEGORY = PUT_WORD_MEANING_1 + ") VALUES (:word_id, :lang_id, :category_name, null)";
  public static final String CHANGE_WORD_MEANINGS = "UPDATE words_meanings set single_meaning =";
  public static final String WORD_ID_IS_EQUAL = "word_id = :word_id";
  public static final String LANG_ID_IS_EQUAL = "lang_id = :lang_id";
  public static final String UPDATE_TYPE = CHANGE_WORD_MEANINGS + " :type_name WHERE " + WORD_ID_IS_EQUAL + " AND " + LANG_ID_IS_EQUAL;
  public static final String UPDATE_CATEGORY = CHANGE_WORD_MEANINGS + " :category_name WHERE " + WORD_ID_IS_EQUAL + " AND " + LANG_ID_IS_EQUAL;
  public static final String REMOVE_ALL = "DELETE FROM words_meanings";
  public static final String DELETE_WORD = REMOVE_ALL + " WHERE word_id = :word_id";
  public static final String DELETE_LANG = REMOVE_ALL + " WHERE lang_id = :lang_id";
  public static final String DELETE_MEANING = REMOVE_ALL + " WHERE single_meaning = :single_meaning";
  public static final String SELECT_ALL = "SELECT * FROM words_meanings";
  public static final String LANG_IS_FROM_SET = "lang_id IN (:lang_id_s)";
  public static final String SELECT_WORDS_MEANINGS_IN_LANGS = SELECT_ALL + " WHERE word_id IN (:word_id_s) AND "+ LANG_IS_FROM_SET;
  public static final String SELECT_MEANINGS_IN_LANGS = SELECT_ALL + " WHERE " + LANG_IS_FROM_SET;
  public static final String SELECT_LANG_MEANINGS = SELECT_ALL + " WHERE " + LANG_ID_IS_EQUAL;
  public static final String SELECT_WORD_MEANINGS = SELECT_ALL + " WHERE " + WORD_ID_IS_EQUAL;
  public static final String SELECT_WORD_MEANINGS_IN_OPEN_RANGE = SELECT_ALL + " WHERE word_id BETWEEN :first_word_id AND :last_word_id";
  public static final String SELECT_LANG_MEANINGS_IN_OPEN_RANGE = SELECT_ALL + " WHERE lang_id BETWEEN :first_lang_id AND :last_lang_id";
  public static final String SUBSTRING_OFFSET_6 = "SUBSTR(single_meaning, 6)";
  public static final String MEANING_LIKE_TYPE = "single_meaning LIKE \"type-%\"";
  public static final String MEANING_LIKE_CATEGORY = "single_meaning LIKE \"category-%\"";
  public static final String SELECT_TYPES = "SELECT " + SUBSTRING_OFFSET_6 + " FROM words_meanings WHERE " + MEANING_LIKE_TYPE;
  public static final String SELECT_CATEGORIES = "SELECT * FROM words_meanings WHERE " + MEANING_LIKE_CATEGORY;
  public static final String SELECT_TYPE_IN_LANG = "SELECT " + SUBSTRING_OFFSET_6 + " FROM words_meanings WHERE " + WORD_ID_IS_EQUAL + " AND " + LANG_ID_IS_EQUAL + " AND single_meaning LIKE \"type-%\"";
  public static final String SELECT_CATEGORY_IN_LANG = SELECT_ALL + " WHERE " + WORD_ID_IS_EQUAL + " AND " + LANG_ID_IS_EQUAL + " AND single_meaning LIKE \"category-%\"";
  public static final String SELECT_WORD_TYPES = "SELECT " + SUBSTRING_OFFSET_6 + " FROM words_meanings WHERE " + WORD_ID_IS_EQUAL + " AND " + MEANING_LIKE_TYPE;
  public static final String SELECT_WORD_CATEGORIES = SELECT_ALL + " WHERE " + WORD_ID_IS_EQUAL + " AND single_meaning LIKE \"category-%\"";
  public static final String SELECT_LANG_TYPES = "SELECT " + SUBSTRING_OFFSET_6 + " FROM words_meanings WHERE " + LANG_ID_IS_EQUAL + " AND " + MEANING_LIKE_CATEGORY;
  public static final String SELECT_LANG_CATEGORIES = SELECT_ALL + " WHERE " + LANG_ID_IS_EQUAL + " AND single_meaning LIKE \"category-%\"";
  
  
  @Insert
  public void insert_word(WordsMeanings new_word_meaning);
  @Query(INSERT_TYPE)
  public void insert_type(long word_id, long lang_id, String type_name);
  @Query(INSERT_CATEGORY)
  public void insert_category(long word_id, long lang_id, String category_name);
  
  @Update
  public void update(WordsMeanings edited_word_meaning);
  @Query(UPDATE_TYPE)
  public void update_type(long word_id, long lang_id, String type_name);
  @Query(UPDATE_CATEGORY)
  public void update_category(long word_id, long lang_id, String category_name);
  
  @Delete
  public void delete(WordsMeanings deleted_meaning);
  @Query(DELETE_WORD)
  public void delete_word(long word_id);
  @Query(DELETE_LANG)
  public void delete_lang(long lang_id);
  @Query(DELETE_MEANING)
  public void delete_meaning(String single_meaning);
  
  @Query(SELECT_ALL)
  public List<WordsMeanings> get_all();
  @Query(SELECT_LANG_MEANINGS)
  public List<WordsMeanings> get_lang_meanings(long lang_id);
  @Query(SELECT_WORDS_MEANINGS_IN_LANGS)
  public List<WordsMeanings> get_words_meanings_in_langs(long[] word_id_s, long[]lang_id_s);
  @Query(SELECT_MEANINGS_IN_LANGS)
  public List<WordsMeanings> get_meanings_in_langs(long[]lang_id_s);
  @Query(SELECT_WORD_MEANINGS)
  public List<WordsMeanings> get_word_meanings(long word_id);
  @Query(SELECT_WORD_MEANINGS_IN_OPEN_RANGE)
  public List<WordsMeanings> get_words_meanings_in_open_range(long first_word_id, long last_word_id);
  @Query(SELECT_LANG_MEANINGS_IN_OPEN_RANGE)
  public List<WordsMeanings> get_langs_meanings_in_open_range(long first_lang_id, long last_lang_id);
  @Query(SELECT_TYPES)
  public List<String> get_types();
  @Query(SELECT_CATEGORIES)
  public List<WordsMeanings> get_categories();
  @Query(SELECT_TYPE_IN_LANG)
  public String get_type_in_lang(long word_id, long lang_id);
  @Query(SELECT_CATEGORY_IN_LANG)
  public WordsMeanings get_category_in_lang(long word_id, long lang_id);
  @Query(SELECT_WORD_TYPES)
  public String get_word_types(long word_id);
  @Query(SELECT_WORD_CATEGORIES)
  public WordsMeanings get_word_categories(long word_id);
  @Query(SELECT_LANG_TYPES)
  public String get_lang_types(long lang_id);
  @Query(SELECT_LANG_CATEGORIES)
  public WordsMeanings get_lang_categories(long lang_id);
}
