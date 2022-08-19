package com.example.android.studio.ja_ar.helpers.editing;

import android.util.Pair;

import java.util.Arrays;
import java.util.Stack;

public class UndoRedo {
  private static boolean KEEP_ALL_STEPS = true;
  private Stack<Pair<Integer, String>> mainStepsStack;
  private Stack<Pair<Integer, String>> redoStack;
  private Pair<Integer, String> initValue;
  
  public UndoRedo(){
    mainStepsStack = new Stack<>();
    redoStack = new Stack<>();
    initValue = null;
  }
  public UndoRedo(Pair<Integer, String> intiValue){
    this();
    mainStepsStack.push(intiValue);
    this.initValue = intiValue;
  }
  //1-2-3-4-5-6-7-8            //            :write
  //1-2-3-4-5-6-7              //8           :undo
  //1-2-3-4-5-6                //8-7         :undo
  //1-2-3-4-5                  //8-7-6       :undo
  //to write 9:                              :write
  //1-2-3-4-5-6-7-8-8-7-6-9 //               :write
  //old main+|redo |redo|new
  //  stack  |from |from|val
  //         |head |tail|
  public void write(Pair<Integer, String> value){
    if(!redoStack.empty() && !isEqual(value, redoStack.peek())){
      if(KEEP_ALL_STEPS){
        Pair<Integer, String>[] redoArray = (Pair<Integer, String>[]) redoStack.toArray();//head is the last
        Pair<Integer, String>[] reversedRedoArray = Arrays.copyOf(redoArray, redoArray.length);
        reverseArray(reversedRedoArray);
        mainStepsStack.addAll(Arrays.asList(reversedRedoArray));
        mainStepsStack.addAll(Arrays.asList(redoArray));
      }
      redoStack.clear();
    }
    mainStepsStack.push(value);
  }
  public Pair<Integer, String> read(){
    return mainStepsStack.peek();
  }
  
  public Pair<Integer, String> undo(){
    if(mainStepsStack.empty()){
      return initValue;
    }
    Pair<Integer, String> value = mainStepsStack.pop();
    redoStack.push(value);
    return value;
  }
  public Pair<Integer, String> redo(){
    if(redoStack.empty()){
      return mainStepsStack.peek();
    }
    Pair<Integer, String> value = redoStack.pop();
    mainStepsStack.push(value);
    return value;
  }
  
  private void reverseArray(Pair<Integer, String>[] reversed_array){
    //Stack.toArray returns the head as the last index.
    int len = reversed_array.length;
    for(int i=0; i<(len/2); i++){
      Pair<Integer, String> temp = reversed_array[i];
      reversed_array[i] = reversed_array[len-i-1];
      reversed_array[len-i-1] = temp;
    }
  }
  public void clear(){
    mainStepsStack.clear();
    redoStack.clear();
    initValue = null;
  }
  private boolean isEqual(Pair<Integer, String> first, Pair<Integer, String> second){
    return (first.first == second.first) && (first.second.equals(second.second));
  }
}

