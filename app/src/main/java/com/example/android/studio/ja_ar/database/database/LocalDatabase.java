package com.example.android.studio.ja_ar.database.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.android.studio.ja_ar.database.dao.CategoryDao;
import com.example.android.studio.ja_ar.database.dao.TestHistoryDao;
import com.example.android.studio.ja_ar.database.dao.WordDao;
import com.example.android.studio.ja_ar.database.dao.WordsMeaningsDao;
import com.example.android.studio.ja_ar.database.entity.Category;
import com.example.android.studio.ja_ar.database.entity.TestHistory;
import com.example.android.studio.ja_ar.database.entity.Word;
import com.example.android.studio.ja_ar.database.entity.WordsMeanings;

@Database(
    entities = {Category.class, TestHistory.class, Word.class, WordsMeanings.class},
    version = 1
)
public abstract class LocalDatabase extends RoomDatabase{
  public abstract CategoryDao getCategoryDao();
  public abstract TestHistoryDao getTestHistoryDao();
  public abstract WordDao getWordDao();
  public abstract WordsMeaningsDao getWordsMeaningsDao();
}
