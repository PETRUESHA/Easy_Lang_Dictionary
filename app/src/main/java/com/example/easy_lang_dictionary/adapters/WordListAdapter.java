package com.example.easy_lang_dictionary.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easy_lang_dictionary.R;
import com.example.easy_lang_dictionary.Word_list;

import java.util.ArrayList;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordPattern> {
    private ArrayList<String> words;
    private ArrayList<String> translates;

    public WordListAdapter(ArrayList<String> wds, ArrayList<String> trts) {
        this.words = wds;
        this.translates = trts;
    }

    @NonNull
    @Override
    public WordPattern onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.word_pattern, parent, false);
        return new WordPattern(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WordPattern holder, int position) {
        String str_word = words.get(position);
        String str_translate = translates.get(position);
        holder.original.setText(str_word);
        holder.result.setText(str_translate);
    }

    @Override
    public int getItemCount() {
        return translates.size();
    }

    public class WordPattern extends RecyclerView.ViewHolder {
        TextView original, result;

        public WordPattern(@NonNull View itemView) {
            super(itemView);
            original = itemView.findViewById(R.id.textView13);
            result = itemView.findViewById(R.id.textView14);
        }
    }
}
