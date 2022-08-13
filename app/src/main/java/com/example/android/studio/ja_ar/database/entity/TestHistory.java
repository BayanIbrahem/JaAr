package com.example.android.studio.ja_ar.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "test_history")
public class TestHistory{
  @PrimaryKey(autoGenerate = true)
  public long history_id;
  
  @ColumnInfo(name = "date")
  public long date;
  
  @ColumnInfo(name="word")
  public long word;
  
  @ColumnInfo(name = "first_lang")
  public long first_lang;
  
  @ColumnInfo(name = "second_lang")
  public long second_lang;
  
  @ColumnInfo(name = "succeeded")
  public boolean succeeded;
}
