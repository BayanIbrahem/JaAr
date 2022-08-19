package com.example.android.studio.ja_ar.recycler_view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.studio.ja_ar.R;
import com.example.android.studio.ja_ar.units.word.SingleWord;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.ViewHolder>{
  
  SingleWord[] singleWords;
  
  public WordListAdapter(SingleWord... singleWords){
    this.singleWords = singleWords;
  }
  
  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
    View layout =
        LayoutInflater.from(
            parent.getContext()
        ).inflate(R.layout.collapsed_list_item_layout, parent, false);
    return new ViewHolder(layout);
  }
  
  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position){
    holder.bind(singleWords[position]);
  }
  
  @Override
  public int getItemCount(){
    return singleWords.length;
  }
  
  public class ViewHolder extends RecyclerView.ViewHolder{
    TextView tv_wordName;
    TextView tv_wordType;
    TextView tv_description;
    TextView tv_wordMeaning;
  
    public ViewHolder(@NonNull View itemView){
      super(itemView);
      tv_wordName = itemView.findViewById(R.id.list_tv_wordName);
      tv_wordType = itemView.findViewById(R.id.list_tv_type);
      tv_description = itemView.findViewById(R.id.list_tv_description);
      tv_wordMeaning = itemView.findViewById(R.id.list_tv_meaning);
    }
    void bind(SingleWord singleWord){
    
    }
  }
}
