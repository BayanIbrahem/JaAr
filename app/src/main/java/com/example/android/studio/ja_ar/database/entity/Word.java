package com.example.android.studio.ja_ar.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//TODO: add full text search extension.
@Entity(tableName = "words")
public class Word{
  
  @PrimaryKey(autoGenerate = true)
  public long word_id;/*unique id for each word*/
  
  @ColumnInfo(name = "type_id")
  /*reference a word_id in the same table
  * equals "null" if the word is type*/
  public long type_id;
  
  @ColumnInfo(name = "category_id")
  /*reference a word_id in the same table
  * type of category is "null" if the word is category*/
  public long category_id;
  
  @ColumnInfo(name = "is_favorite")
  public boolean is_favorite;
  
  /* the next two columns will be null
  *  if the word is type or category
  * */
  @ColumnInfo(name = "passed_tests_num")
  public int passed_tests_num;
  @ColumnInfo(name = "failed_tests_num")
  public int failed_tests_num;
}
