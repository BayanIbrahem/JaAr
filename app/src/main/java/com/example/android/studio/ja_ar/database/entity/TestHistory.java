package com.example.android.studio.ja_ar.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "test_history")
public class TestHistory{
  @PrimaryKey(autoGenerate = true)
  public long history_id;
  
  @ColumnInfo(name = "date")
  //number of seconds form 1970 (not milli seconds)
  public long date;
  
  @ColumnInfo(name="word_id")
  public long word_id;
  
  @ColumnInfo(name = "first_lang")
  public long first_lang;
  
  @ColumnInfo(name = "second_lang")
  public long second_lang;
  
  @ColumnInfo(name = "succeeded")
  public boolean succeeded;
}
