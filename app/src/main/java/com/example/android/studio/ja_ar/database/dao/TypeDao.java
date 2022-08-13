package com.example.android.studio.ja_ar.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.android.studio.ja_ar.database.entity.Category;
import com.example.android.studio.ja_ar.database.entity.Type;

import java.util.List;

@Dao
public interface TypeDao{
  
  //insert
  @Insert
  public long insert(Type type);
  @Insert
  public long insert(String type_name);
  
  //update
  @Update
  public long update(Type type);
  @Query("UPDATE types SET type_name = :type_name WHERE type_id = :type_id")
  public long update(String type_name, long type_id);
  
  //delete
  @Delete
  public long delete(Type type);
  @Query("DELETE FROM types WHERE type_id = :type_id")
  public long delete(long type_id);
  @Query("DELETE FROM types WHERE type_name = :type_name")
  public long delete(String type_name);
  
  //select
  @Query("SELECT * FROM types")
  public List<Type> get_type();
  @Query("SELECT type_name FROM types")
  public List<String> get_type_names();
  @Query("SELECT * FROM types WHERE type_id = :type_id")
  public Type get_type(long type_id);
  @Query("SELECT type_name FROM types WHERE type_id = :type_id")
  public String get_type_name(long type_id);
}
