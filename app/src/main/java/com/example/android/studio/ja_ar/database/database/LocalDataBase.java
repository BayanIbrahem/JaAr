package com.example.android.studio.ja_ar.database.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.android.studio.ja_ar.database.dao.CategoryDao;
import com.example.android.studio.ja_ar.database.dao.TestHistoryDao;
import com.example.android.studio.ja_ar.database.dao.TypeDao;
import com.example.android.studio.ja_ar.database.dao.WordDao;
import com.example.android.studio.ja_ar.database.entity.Category;
import com.example.android.studio.ja_ar.database.entity.TestHistory;
import com.example.android.studio.ja_ar.database.entity.Type;
import com.example.android.studio.ja_ar.database.entity.Word;

@Database(
    entities = {Category.class, TestHistory.class, Type.class, Word.class},
    version = 1
)
public abstract class LocalDataBase extends RoomDatabase{
  public abstract CategoryDao categoryDao();
  public abstract TestHistoryDao testHistoryDao();
  public abstract TypeDao typeDao();
  public abstract WordDao wordDao();
}
