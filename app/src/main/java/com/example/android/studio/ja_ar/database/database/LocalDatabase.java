package com.example.android.studio.ja_ar.database.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.android.studio.ja_ar.database.dao.DescriptionsDao;
import com.example.android.studio.ja_ar.database.dao.TestHistoryDao;
import com.example.android.studio.ja_ar.database.dao.WordDao;
import com.example.android.studio.ja_ar.database.dao.WordsMeaningsDao;
import com.example.android.studio.ja_ar.database.entity.Descriptions;
import com.example.android.studio.ja_ar.database.entity.TestHistory;
import com.example.android.studio.ja_ar.database.entity.Word;
import com.example.android.studio.ja_ar.database.entity.WordsMeanings;

@Database(
    entities = {Descriptions.class, TestHistory.class, Word.class, WordsMeanings.class},
    version = 4
)
public abstract class LocalDatabase extends RoomDatabase{
  public abstract DescriptionsDao getDescriptionsDao();
  public abstract TestHistoryDao getTestHistoryDao();
  public abstract WordDao getWordDao();
  public abstract WordsMeaningsDao getWordsMeaningsDao();
}
