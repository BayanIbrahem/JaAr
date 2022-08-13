package com.example.android.studio.ja_ar.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "types")
public class Type{
  @PrimaryKey(autoGenerate = true)
  public long type_id;
  public String type_name;
}
