package com.example.android.studio.ja_ar.database.dao;

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
@Dao public interface WordDao{
  public static final String INSERT_TYPE_OR_CATEGORY = "INSERT INTO words (type_id, category_id, is_favorite, passed_tests_num, failed_tests_num) VALUES (:type_id, :category_id, :is_favorite, null, null)";
  public static final String WORD_ID_IS_EQUALS = "word_id = :word_id";
  public static final String IS_TYPE_ID = "word_id IN (SELECT word_id FROM words_meanings WHERE single_meaning LIKE \"type-%\")";
  public static final String IS_CATEGORY_ID = "word_id IN (SELECT word_id FROM words_meanings WHERE single_meaning LIKE \"category-%\")";
  public static final String IS_TYPE_OR_CATEGORY_ID = "word_id IN (SELECT word_id FROM words_meanings WHERE single_meaning LIKE \"category-%\" OR single_meaning LIKE \"type-%\")";
  public static final String SET_WORD_IS_FAVORITE = "UPDATE words SET is_favorite = :is_favorite";
  public static final String UPDATE_SET_TYPE_FAVORITE = SET_WORD_IS_FAVORITE + " WHERE " + WORD_ID_IS_EQUALS + " AND " + IS_TYPE_ID;
  public static final String UPDATE_SET_CATEGORY_FAVORITE = SET_WORD_IS_FAVORITE + " WHERE " + WORD_ID_IS_EQUALS + " AND " + IS_CATEGORY_ID;
  public static final String UPDATE_ADD_PASSED_TEST_TRY = "UPDATE words SET passed_tests_num = 1 + (SELECT passed_tests_num FROM words WHERE " + WORD_ID_IS_EQUALS + ") WHERE " + WORD_ID_IS_EQUALS + " AND passed_tests_num IS NOT null";
  public static final String UPDATE_ADD_FAILED_TEST_TRY = "UPDATE words SET failed_tests_num = 1 + (SELECT failed_tests_num FROM words WHERE " + WORD_ID_IS_EQUALS + ") WHERE " + WORD_ID_IS_EQUALS + " AND failed_tests_num IS NOT null";
  public static final String WORD_CLEAR_TEST_HISTORY = "UPDATE words SET passed_tests_num = 0, failed_tests_num = 0";
  public static final String UPDATE_CLEAR_HISTORY = WORD_CLEAR_TEST_HISTORY + " WHERE NOT " + IS_TYPE_OR_CATEGORY_ID;
  public static final String UPDATE_CLEAR_HISTORY_FOR_WORD = WORD_CLEAR_TEST_HISTORY + " WHERE " + WORD_ID_IS_EQUALS + " AND NOT " + IS_TYPE_OR_CATEGORY_ID;
  public static final String REMOVE_ALL_WORDS = "DELETE FROM words";
  public static final String DELETE_TYPE = REMOVE_ALL_WORDS + " WHERE word_id = :type_id AND " + IS_TYPE_ID;
  public static final String DELETE_CATEGORY = REMOVE_ALL_WORDS + " WHERE word_id = :category_id AND " + IS_CATEGORY_ID;
  public static final String SELECT_ALL = "SELECT * FROM words";
  public static final String SELECT_BETWEEN = SELECT_ALL + " WHERE word_id BETWEEN :first_id AND :last_id";
  public static final String SELECT_FAVORITE = SELECT_ALL + " WHERE is_favorite";
  public static final String GET_WORD_ID = "SELECT word_id FROM words ";
  public static final String SELECT_TYPES_ID = GET_WORD_ID + " WHERE " + IS_TYPE_ID;
  public static final String SELECT_CATEGORIES_ID = GET_WORD_ID + " WHERE " + IS_CATEGORY_ID;
  public static final String SELECT_IS_TYPE = "SELECT " + IS_TYPE_ID + " FROM words WHERE " + WORD_ID_IS_EQUALS;
  public static final String SELECT_IS_CATEGORY = "SELECT " + IS_CATEGORY_ID + " FROM words WHERE " + WORD_ID_IS_EQUALS;
  public static final String SELECT_IS_WORD = "SELECT NOT " + IS_TYPE_OR_CATEGORY_ID + " FROM words WHERE " + WORD_ID_IS_EQUALS;
  public static final String SELECT_PASSED_TESTS_NUM_FOR_WORD = "SELECT passed_tests_num FROM words WHERE " + WORD_ID_IS_EQUALS;
  public static final String SELECT_FAILED_TESTS_NUM_FOR_WORD = "SELECT failed_tests_num FROM words WHERE " + WORD_ID_IS_EQUALS;
  public static final String SELECT_ALL_TESTS_NUM_FOR_WORD = "SELECT passed_tests_num + failed_tests_num FROM words WHERE " + WORD_ID_IS_EQUALS;
  
  //insert
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  public void insert_or_replace(Word... words);
  @Insert(onConflict = OnConflictStrategy.IGNORE)
  public void insert_or_ignore(Word... words);
  //insert type
  @Query(INSERT_TYPE_OR_CATEGORY)
  public void insert_type(long type_id, long category_id, boolean is_favorite);
  @Query(INSERT_TYPE_OR_CATEGORY)
  public void insert_category(long type_id, long category_id, boolean is_favorite);
  
  
  //update
  @Update
  public void update_word(Word new_word);
  //update type
  @Query(UPDATE_SET_TYPE_FAVORITE)
  public void set_type_favorite(long word_id, boolean is_favorite);
  //update category
  @Query(UPDATE_SET_CATEGORY_FAVORITE)
  public void set_category_favorite(long word_id, boolean is_favorite);
  //update test history:
  @Query(UPDATE_ADD_PASSED_TEST_TRY)
  public void add_passed_test_try(long word_id);
  @Query(UPDATE_ADD_FAILED_TEST_TRY)
  public void add_failed_test_try(long word_id);
  @Query(UPDATE_CLEAR_HISTORY)
  public void clear_history();
  @Query(UPDATE_CLEAR_HISTORY_FOR_WORD)
  public void clear_history_for_word(long word_id);
  
  //delete
  @Delete
  public void delete(Word deleted_word);
  //delete type:
  @Query(DELETE_TYPE)
  public void delete_type(long type_id);
  //delete category:
  @Query(DELETE_CATEGORY)
  public void delete_category(long category_id);
  
  //select:
  @Query(SELECT_ALL)
  public List<Word> get_all();
  @Query(SELECT_BETWEEN)
  public List<Word> get_between(long first_id, long last_id);
  @Query(SELECT_FAVORITE)
  public List<Word> get_favorite();
  @Query(SELECT_TYPES_ID)
  public List<Long> get_types_id();
  @Query(SELECT_CATEGORIES_ID)
  public List<Long> get_categories_id();
  @Query(SELECT_IS_TYPE)
  public boolean is_type(long word_id);
  @Query(SELECT_IS_CATEGORY)
  public boolean is_category(long word_id);
  @Query(SELECT_IS_WORD)
  public  boolean is_word(long word_id);
  @Query(SELECT_PASSED_TESTS_NUM_FOR_WORD)
  public int get_passed_tests_num_for_word(long word_id);
  @Query(SELECT_FAILED_TESTS_NUM_FOR_WORD)
  public int get_failed_tests_num_for_word(long word_id);
  @Query(SELECT_ALL_TESTS_NUM_FOR_WORD)
  public int get_all_tests_num_for_word(long word_id);
}
