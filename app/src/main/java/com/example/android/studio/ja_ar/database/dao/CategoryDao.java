package com.example.android.studio.ja_ar.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.android.studio.ja_ar.database.entity.Category;

import java.util.List;

@Dao
public interface CategoryDao{
  //insert
  @Insert
  public long insert(Category category);
  @Insert
  public long insert(String category_name);
  
  //update
  @Update
  public long update(Category category);
  @Query("UPDATE categories SET category_name = :category_name WHERE category_id = :category_id")
  public long update(String category_name, long category_id);
  
  //delete
  @Delete
  public long delete(Category category);
  @Query("DELETE FROM categories WHERE category_id = :category_id")
  public long delete(long category_id);
  @Query("DELETE FROM categories WHERE category_name = :category_name")
  public long delete(String category_name);
  
  //select
  @Query("SELECT * FROM categories")
  public List<Category> get_category();
  @Query("SELECT category_name FROM categories")
  public List<String> get_category_names();
  @Query("SELECT * FROM categories WHERE category_id = :category_id")
  public Category get_category(long category_id);
  @Query("SELECT category_name FROM categories WHERE category_id = :category_id")
  public String get_category_name(long category_id);
}
