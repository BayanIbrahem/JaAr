package com.example.android.studio.ja_ar.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(
    tableName = "words_meanings",
    primaryKeys = {"word_id", "lang_id"}
)
public class WordsMeanings{
  
  public long word_id;
  public long lang_id;
  
  @ColumnInfo(name = "single_meaning")
  public String single_meaning;
  
  @ColumnInfo(name = "usage_count")
  public long usage_count;
  
  @ColumnInfo(name = "descriptions")
  public String descriptions;
}
