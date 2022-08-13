package com.example.android.studio.ja_ar.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "categories")
public class Category{
  @PrimaryKey(autoGenerate = true)
  public long category_id;
  @ColumnInfo(name = "category_name")
  public String category_name;
}
