package com.example.android.studio.ja_ar.storage.data_store;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.datastore.preferences.core.MutablePreferences;
import androidx.datastore.preferences.core.Preferences;
import androidx.datastore.preferences.core.PreferencesKeys;
import androidx.datastore.preferences.rxjava2.RxPreferenceDataStoreBuilder;
import androidx.datastore.rxjava2.RxDataStore;

import com.example.android.studio.ja_ar.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.reactivex.Flowable;
import io.reactivex.Single;
import kotlinx.coroutines.flow.Flow;

public class DataStoreManager{
  private static RxDataStore<Preferences> dataStore;
  private static DataStoreManager instance;
  private static ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
  
  private DataStoreManager(Application application){
    Context context = application.getApplicationContext();
    String data_store_tag = context.getString(R.string.tag_settings_dataStore);
    this.dataStore = new RxPreferenceDataStoreBuilder(context, data_store_tag).build();
  }
  public static DataStoreManager getInstance(Application application){
    if(instance == null){
      instance = new DataStoreManager(application);
    }
    return instance;
  }
  
  public boolean readBoolean(final String KEY_NAME, final boolean DEFAULT_VALUE){
    final Preferences.Key<Boolean> BOOLEAN_KEY = PreferencesKeys.booleanKey(KEY_NAME);
    Flowable<Boolean> flowable = dataStore.data().map((preferences -> {
      Boolean value = preferences.get(BOOLEAN_KEY);
      return (value==null)?DEFAULT_VALUE:value;
    }));
    boolean value = flowable.blockingFirst();
    Log.println(Log.ERROR, "settings", "data_store_manager, read boolean key, " + KEY_NAME + " - " + as_str(value));
    return value;
  }
  public int readInt(final String KEY_NAME, final int DEFAULT_VALUE){
    final Preferences.Key<Integer> INT_KEY = PreferencesKeys.intKey(KEY_NAME);
    Flowable<Integer> flowable = dataStore.data().map((preferences -> {
      Integer value = preferences.get(INT_KEY);
      return (value==null)?DEFAULT_VALUE:value;
    }));
    int value = flowable.blockingFirst();
    Log.println(Log.ERROR, "settings", "data_store_manager, read integer key, " + KEY_NAME + " - " + as_str(value));
    return value;
  }
  public long readLong(final String KEY_NAME, final long DEFAULT_VALUE){
    final Preferences.Key<Long> INT_KEY = PreferencesKeys.longKey(KEY_NAME);
    Flowable<Long> flowable = dataStore.data().map((preferences -> {
      Long value = preferences.get(INT_KEY);
      return (value==null)?DEFAULT_VALUE:value;
    }));
    Long value = flowable.blockingFirst();
    Log.println(Log.ERROR, "settings", "data_store_manager, read long key, " + KEY_NAME + " - " + as_str(value));
    return value;
  }
  public float readFloat(final String KEY_NAME, final float DEFAULT_VALUE){
    final Preferences.Key<Float> FLOAT_KEY = PreferencesKeys.floatKey(KEY_NAME);
    Flowable<Float> flowable = dataStore.data().map((preferences -> {
      Float value = preferences.get(FLOAT_KEY);
      return (value==null)?DEFAULT_VALUE:value;
    }));
    float value = flowable.blockingFirst();
    Log.println(Log.ERROR, "settings", "data_store_manager, read float key, " + KEY_NAME + " - " + as_str(value));
    return value;
  }
  public String readString(final String KEY_NAME, final String DEFAULT_VALUE){
    final Preferences.Key<String> STRING_KEY = PreferencesKeys.stringKey(KEY_NAME);
    Flowable<String> flowable = dataStore.data().map((preferences -> {
      String value = preferences.get(STRING_KEY);
      return (value==null)?DEFAULT_VALUE:value;
    }));
    String value = flowable.blockingFirst();
    Log.println(Log.ERROR, "settings", "data_store_manager, read string key, " + KEY_NAME + " - " + as_str(value));
    return value;
  }
  
  /*** WRITINGS METHODS *************************************/
  
  public void writeBoolean(String BOOL_KEY, Boolean bool_value){
    executorService.submit(()-> writingBooleanSteps(BOOL_KEY, bool_value));
    Log.println(Log.ERROR, "settings", "data_store_manager, write boolean, pushed in another thread key, " + BOOL_KEY + " - " + as_str(bool_value));
  }
  private void writingBooleanSteps(String BOOL_KEY, Boolean bool_value){
    Preferences.Key<Boolean> key = PreferencesKeys.booleanKey(BOOL_KEY);
    Single<Preferences> updateResult = dataStore.updateDataAsync(inputPreferences->{
      MutablePreferences mutablePreferences = inputPreferences.toMutablePreferences();
      mutablePreferences.set(key, bool_value);//TODO: set default value
      return Single.just(mutablePreferences);
    });
    updateResult.subscribe();
    Log.println(Log.ERROR, "settings", "data_store_manager, write boolean, saving Single ended key, " + BOOL_KEY + " - " + as_str(bool_value));
  }
  
  public void writeInteger(String INT_KEY, Integer int_value){
    executorService.submit(()-> writingIntegerSteps(INT_KEY, int_value));
    Log.println(Log.ERROR, "settings", "data_store_manager, write int, pushed in another thread key, " + INT_KEY + " - " + as_str(int_value));
  }
  private void writingIntegerSteps(String INT_KEY, Integer int_value){
    Preferences.Key<Integer> key = PreferencesKeys.intKey(INT_KEY);
    Single<Preferences> updateResult = dataStore.updateDataAsync(inputPreferences->{
      MutablePreferences mutablePreferences = inputPreferences.toMutablePreferences();
      mutablePreferences.set(key, int_value);//TODO: set default value
      return Single.just(mutablePreferences);
    });
    updateResult.subscribe();
    Log.println(Log.ERROR, "settings", "data_store_manager, write integer, saving Single ended key, " + INT_KEY + " - " + as_str(int_value));
  }
  
  public void writeLong(String LONG_KEY, Long long_value){
    executorService.submit(()-> writingLongSteps(LONG_KEY, long_value));
    Log.println(Log.ERROR, "settings", "data_store_manager, write long, pushed in another thread key, " + LONG_KEY + " - " + as_str(long_value));
  }
  private void writingLongSteps(String LONG_KEY, Long long_value){
    Preferences.Key<Long> key = PreferencesKeys.longKey(LONG_KEY);
    Single<Preferences> updateResult = dataStore.updateDataAsync(inputPreferences->{
      MutablePreferences mutablePreferences = inputPreferences.toMutablePreferences();
      mutablePreferences.set(key, long_value);//TODO: set default value
      return Single.just(mutablePreferences);
    });
    updateResult.subscribe();
    Log.println(Log.ERROR, "settings", "data_store_manager, write long, saving Single ended key, " + LONG_KEY + " - " + as_str(long_value));
  }
  
  public void writeFloat(String FLOAT_KEY, float float_value){
    executorService.submit(()-> writingFloatSteps(FLOAT_KEY, float_value));
    Log.println(Log.ERROR, "settings", "data_store_manager, write float, pushed in another thread key, " + FLOAT_KEY + " - " + as_str(float_value));
  }
  private void writingFloatSteps(String FLOAT_KEY, float float_value){
    Preferences.Key<Float> key = PreferencesKeys.floatKey(FLOAT_KEY);
    Single<Preferences> updateResult = dataStore.updateDataAsync(inputPreferences->{
      MutablePreferences mutablePreferences = inputPreferences.toMutablePreferences();
      mutablePreferences.set(key, float_value);//TODO: set default value
      return Single.just(mutablePreferences);
    });
    updateResult.subscribe();
    Log.println(Log.ERROR, "settings", "data_store_manager, write float, saving Single ended key, " + FLOAT_KEY + " - " + as_str(float_value));
  }
  
  public void writeString(String STRING_KEY, String string_value){
    executorService.submit(()-> writingStringSteps(STRING_KEY, string_value));
    Log.println(Log.ERROR, "settings", "data_store_manager, write string, pushed in another thread key, " + STRING_KEY + " - " + as_str(string_value));
  }
  private void writingStringSteps(String STRING_KEY, String string_value){
    Preferences.Key<String> key = PreferencesKeys.stringKey(STRING_KEY);
    Single<Preferences> updateResult = dataStore.updateDataAsync(inputPreferences->{
      MutablePreferences mutablePreferences = inputPreferences.toMutablePreferences();
      mutablePreferences.set(key, string_value);//TODO: set default value
      return Single.just(mutablePreferences);
    });
    updateResult.subscribe();
    Log.println(Log.ERROR, "settings", "data_store_manager, write string, saving Single ended key, " + STRING_KEY + " - " + as_str(string_value));
  }
  
  @Override
  protected void finalize() throws Throwable{
    if(executorService != null && !executorService.isShutdown()){
      executorService.shutdown();
    }
    super.finalize();
  }
  
  private String as_str(Object val){
    return val.toString();
  }
}
