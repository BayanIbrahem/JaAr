package com.example.android.studio.ja_ar.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//TODO: add full text search extension.
@Entity(tableName = "words", primaryKeys = {"word_id", "lang_id"})
public class Word{
  @PrimaryKey(autoGenerate = true)
  public long word_id;/*unique id for each word*/
  @PrimaryKey(autoGenerate = true)
  public long lang_id;/* is the same in language enum*/
  @ColumnInfo(name = "meanings")
  public String meanings;/*multiple values*/
  @ColumnInfo(name = "descriptions")
  public String descriptions;
  @ColumnInfo(name = "Type")
  public long type;/*reference to Type id in another table*/
  @ColumnInfo(name = "category")
  public long category;/*reference to category id in another table*/
  @ColumnInfo(name = "favorite")
  public boolean favorite;
  @ColumnInfo(name = "passed_tests_num")
  public long passed_tests_num;
  @ColumnInfo(name = "failed_tests_num")
  public long failed_tests_num;
}
