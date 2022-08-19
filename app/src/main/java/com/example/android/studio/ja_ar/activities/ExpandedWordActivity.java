package com.example.android.studio.ja_ar.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.util.Pair;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.studio.ja_ar.R;
import com.example.android.studio.ja_ar.database.database_manager.LocalDataBaseManager;
import com.example.android.studio.ja_ar.databinding.ActivityExpandedWordBinding;
import com.example.android.studio.ja_ar.helpers.editing.UndoRedo;
import com.example.android.studio.ja_ar.helpers.log.PrintLog;

public class ExpandedWordActivity extends AppCompatActivity{
  private final String WORD_ID = getString(R.string.id);
  private final String LANG_ID = getString(R.string.lang_id);
  private final String MAIN_NAME = getString(R.string.word_main_word);
  private final String OTHER_NAMES = getString(R.string.word_other_names);
  private final String TYPE = getString(R.string.word_type);
  private final String MEANINGS = getString(R.string.word_meanings);
  private final String DESCRIPTION = getString(R.string.word_description);
  
  private MenuItem menuNormalModeGroup;
  private MenuItem menuEditModeGroup;
  private boolean editMode = false;
  
  private String last_saved_state_main_name;
  private String last_saved_state_other_names;
  private String last_saved_state_type;
  private String last_saved_state_meanings;
  private String last_saved_state_description;
  
  //this id will be stored for the history
  private final int id_of_main_name = 1;
  private final int id_of_other_names = 2;
  private final int id_of_type = 3;
  private final int id_of_meanings = 4;
  private final int id_of_description = 5;
  private UndoRedo undoRedoHistory;
  
  private Handler threadHandler;
  
  ActivityExpandedWordBinding binding;
  @Override
  protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_expanded_word);
    binding = ActivityExpandedWordBinding.inflate(getLayoutInflater());
    getIntentBundleValuesToUI();
    setLastSavedStateFromUi();
    threadHandler = new Handler();
    setEditTextListeners();
  }
  @Override
  protected void onPause(){
    saveInDatabase();
    super.onPause();
  }
  //override methods:
  @Override
  public boolean onCreateOptionsMenu(Menu menu){
    getMenuInflater().inflate(R.menu.expanded_word_app_bar, menu);
    menuNormalModeGroup = menu.findItem(R.id.menu_group_expanded_word_normal_mode);
    menuEditModeGroup = menu.findItem(R.id.menu_group_expanded_word_editing_mode);
    return true;
  }
  @Override
  public boolean onOptionsItemSelected(MenuItem item){
    switch(item.getItemId()){
      case R.id.menu_expanded_word_edit:
        onSelectEditOption();
        break;
      case R.id.menu_expanded_word_save:
        onSelectSaveOption();
        break;
      case R.id.menu_expanded_word_cancel:
        onSelectCancelOption();
        break;
      case R.id.menu_expanded_word_undo:
        onSelectUndoOption();
        break;
      case R.id.menu_expanded_word_redo:
        onSelectRedoOption();
        break;
      default:
    }
    return true;
  }
  
  //private methods:
  private void setEditTextListeners(){
    binding.expandedWordEtMainName.setOnEditorActionListener(new EditListener(id_of_main_name));
    binding.expandedWordEtOtherNames.setOnEditorActionListener(new EditListener(id_of_other_names));
    binding.expandedWordEtType.setOnEditorActionListener(new EditListener(id_of_type));
    binding.expandedWordEtMeanings.setOnEditorActionListener(new EditListener(id_of_meanings));
    binding.expandedWordEtDescription.setOnEditorActionListener(new EditListener(id_of_description));
  }
  
  private void onSelectEditOption(){
    showEditMode(true);
    if(undoRedoHistory == null){
      undoRedoHistory = new UndoRedo();
    }
  }
  private void onSelectSaveOption(){
    showEditMode(false);
    setLastSavedStateFromUi();
    clearHistoryStack();
  }
  private void onSelectCancelOption(){
    showEditMode(false);
    setUiFromLastSavedState();
    clearHistoryStack();
  }
  private void onSelectUndoOption(){
    setValueBasedOfId(undoRedoHistory.undo());
  }
  private void onSelectRedoOption(){
    setValueBasedOfId(undoRedoHistory.redo());
  }
  
  private void showEditMode(boolean edit_mode){
    menuNormalModeGroup.setVisible(!edit_mode);
    menuEditModeGroup.setVisible(edit_mode);
    editMode = edit_mode;
    allowEditing(edit_mode);
  }
  private void setLastSavedStateFromUi(){
    last_saved_state_main_name = binding.expandedWordEtMainName.getText().toString();
    last_saved_state_other_names = binding.expandedWordEtOtherNames.getText().toString();
    last_saved_state_type = binding.expandedWordEtType.getText().toString();;
    last_saved_state_meanings = binding.expandedWordEtMeanings.getText().toString();;
    last_saved_state_description = binding.expandedWordEtDescription.getText().toString();;
  }
  private void setUiFromLastSavedState(){
    binding.expandedWordEtMainName.setText(last_saved_state_main_name);
    binding.expandedWordEtOtherNames.setText(last_saved_state_other_names);
    binding.expandedWordEtType.setText(last_saved_state_type);
    binding.expandedWordEtMeanings.setText(last_saved_state_meanings);
    binding.expandedWordEtDescription.setText(last_saved_state_description);
  }
  private void allowEditing(boolean is_allowed){
    int input_type = -1;
    if(is_allowed){
      input_type = InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL;
    }
    else{
      input_type = InputType.TYPE_NULL;
    }
    binding.expandedWordEtMainName.setInputType(input_type);
    binding.expandedWordEtOtherNames.setInputType(input_type);
    binding.expandedWordEtType.setInputType(input_type);
    binding.expandedWordEtMeanings.setInputType(input_type);
    binding.expandedWordEtDescription.setInputType(input_type);
  }
  private void clearHistoryStack(){
    if(undoRedoHistory!=null){
      undoRedoHistory.clear();
    }
  }
  
  
  
  private void saveInDatabase(){
   //todo: make saving in database
  }
  
  
  
  private void getIntentBundleValuesToUI(){
    Intent receivedIntent = getIntent();
    Bundle extras = receivedIntent.getExtras();
    if(checkIfValidIntent(receivedIntent)){
      
      final String INTENT_VALUE_MAIN_NAME = extras.getString(MAIN_NAME);
      final String INTENT_VALUE_OTHER_NAMES = extras.getString(OTHER_NAMES);
      final String INTENT_VALUE_TYPE = extras.getString(TYPE);
      final String INTENT_VALUE_MEANINGS = extras.getString(MEANINGS);
      final String INTENT_VALUE_DESCRIPTION = extras.getString(DESCRIPTION);
      
      binding.expandedWordEtMainName.setText(INTENT_VALUE_MAIN_NAME);
      binding.expandedWordEtOtherNames.setText(INTENT_VALUE_OTHER_NAMES);
      binding.expandedWordEtType.setText(INTENT_VALUE_TYPE);
      binding.expandedWordEtMeanings.setText(INTENT_VALUE_MEANINGS);
      binding.expandedWordEtDescription.setText(INTENT_VALUE_DESCRIPTION);
    }
    else {
      endActivity();
    }
  }
  private boolean checkIfValidIntent(Intent intent){
    Bundle extras = intent.getExtras();
    if(!extras.containsKey(MAIN_NAME)){
      return false;
    }
    if(!extras.containsKey(OTHER_NAMES)){
      return false;
    }
    if(!extras.containsKey(TYPE)){
      return false;
    }
    if(!extras.containsKey(MEANINGS)){
      return false;
    }
    if(!extras.containsKey(DESCRIPTION)){
      return false;
    }
    return true;
  }
  private void endActivity(){
    Toast.makeText(this, "invalid word", Toast.LENGTH_LONG).show();
    finish();
  }
  
  public class EditListener implements TextView.OnEditorActionListener{
    /**
     * this inner class for the edit text,
     * the runnable passed in the constructor is the actual action.
     * when the 'onEditorAction' called it check if there
     * an progress that waits to be executed.
     * (until the cursor blinks)
     *
     * */
    boolean action_in_progress = false;
    private Runnable task;
    private int id;
    private final int CURSOR_BLINK_DURATION = 1000; //milliseconds
    public EditListener(int id){
      this.id = id;
      this.task = () -> {
        undoRedoHistory.write(new Pair<>(id, getValueBasedOfId(id)));
        action_in_progress = false;
      };
    }
    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent){
      if(!action_in_progress){
        threadHandler.postDelayed(task, CURSOR_BLINK_DURATION);
        action_in_progress = true;
      }
      return false;
    }
  }
  private String getValueBasedOfId(int id){
    String value = null;
    switch(id){
      case id_of_main_name:
        value = binding.expandedWordEtMainName.getText().toString();
        break;
      case id_of_other_names:
        value = binding.expandedWordEtOtherNames.getText().toString();
        break;
      case id_of_type:
        value = binding.expandedWordEtType.getText().toString();
        break;
      case id_of_meanings:
        value = binding.expandedWordEtMeanings.getText().toString();
        break;
      case id_of_description:
        value = binding.expandedWordEtDescription.getText().toString();
        break;
      default:
        PrintLog.error("save in history", "can not save in history" +
            " because of the id is invalid, no thing done");
    }
    return value;
  }
  private void setValueBasedOfId(Pair<Integer, String> entry){
    switch(entry.first){
      case id_of_main_name:
        binding.expandedWordEtMainName.setText(entry.second);
        break;
      case id_of_other_names:
        binding.expandedWordEtOtherNames.setText(entry.second);
        break;
      case id_of_type:
        binding.expandedWordEtType.setText(entry.second);
        break;
      case id_of_meanings:
        binding.expandedWordEtMeanings.setText(entry.second);
        break;
      case id_of_description:
        binding.expandedWordEtDescription.setText(entry.second);
        break;
      default:
       PrintLog.error("retrieve from history", "can not retrieve in history" +
            " because of the id is invalid, no thing done");
    }
  }
}