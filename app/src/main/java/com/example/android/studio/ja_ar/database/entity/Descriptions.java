package com.example.android.studio.ja_ar.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(
    tableName = "descriptions",
    primaryKeys = {"word_id", "lang_id"}
)
public class Descriptions{
  @ColumnInfo(name = "word_id")
  public long word_id;
  @ColumnInfo(name = "lang_id")
  public long lang_id;
  @ColumnInfo(name = "descriptions")
  public String descriptions;
}
